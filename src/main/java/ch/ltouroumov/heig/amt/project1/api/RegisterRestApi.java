package ch.ltouroumov.heig.amt.project1.api;


import ch.ltouroumov.heig.amt.project1.api.dto.GetUserDTO;
import ch.ltouroumov.heig.amt.project1.api.dto.PostUpdatePasswordDTO;
import ch.ltouroumov.heig.amt.project1.api.dto.PostUpdateUserDTO;
import ch.ltouroumov.heig.amt.project1.api.dto.PostUserDTO;
import ch.ltouroumov.heig.amt.project1.model.manager.IUserManager;
import ch.ltouroumov.heig.amt.project1.model.entities.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Path("/users")
public class RegisterRestApi {

    @EJB(beanName = "JdbcUserManager")
    private IUserManager userManager;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GetUserDTO> getUsers(@QueryParam(value = "byName") String byName) {

        List<User> users = userManager.findAll();
        return users.stream().filter(p -> byName == null || p.getLastname().equalsIgnoreCase(byName))
                .map(this::toGetUserDTO)
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(PostUserDTO postUserDTO) {

        String username;
        User user = fromPostUserDTO(postUserDTO);
        try {
            userManager.create(user);
            username = postUserDTO.getUsername();

            URI href = uriInfo
                    .getBaseUriBuilder()
                    .path(RegisterRestApi.class)
                    .path(RegisterRestApi.class, "getUser")
                    .build(username);

            return Response
                    .created(href)
                    .build();

        } catch (Exception ex) {
            return Response.notModified("User already exists...")
                    .build();
        }
    }


    @Path("/update/password/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(@PathParam(value = "id") String id, PostUpdatePasswordDTO dto){
        User user = userStore.findUser(id);
        user.setPassword(DigestUtils.sha1Hex(dto.getPassword()));
        return Response.accepted("User password updated successfully!").build();
    }

    @Path("/update/user/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInfoUser(@PathParam(value = "id") String id, PostUpdateUserDTO dto){
        User user = userStore.findUser(id);
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setEmail(dto.getEmail());
        return Response.accepted("User info updated successfully!").build();
    }


    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetUserDTO getUser(@PathParam(value = "id") String id) {
        User user = userManager.findOne(id);
        return toGetUserDTO(user);
    }


    public User fromPostUserDTO(PostUserDTO postUserDTO){
        User user = new User(postUserDTO.getUsername());
        user.setFirstname(postUserDTO.getFirstname());
        user.setLastname(postUserDTO.getLastname());
        user.setPassword(DigestUtils.sha1Hex(postUserDTO.getPassword()));
        user.setEmail(postUserDTO.getEmail());
        return user;
    }


    public GetUserDTO toGetUserDTO(User user) {
        GetUserDTO getUserDTO = new GetUserDTO(user.getUsername());
        getUserDTO.setFirstname(user.getFirstname());
        getUserDTO.setLastname(user.getLastname());
        getUserDTO.setEmail(user.getEmail());
        return getUserDTO;
    }
}
