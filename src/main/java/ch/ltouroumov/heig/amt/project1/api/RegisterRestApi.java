package ch.ltouroumov.heig.amt.project1.api;


import ch.ltouroumov.heig.amt.project1.api.dto.GetUserDTO;
import ch.ltouroumov.heig.amt.project1.api.dto.PostUserDTO;
import ch.ltouroumov.heig.amt.project1.user.IUserStore;
import ch.ltouroumov.heig.amt.project1.user.User;
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

    @EJB
    private IUserStore userStore;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GetUserDTO> getUsers(@QueryParam(value = "byName") String byName) {

        List<User> users = userStore.getUsers();
        return users.stream().filter(p -> byName == null || p.getLastname().equalsIgnoreCase(byName))
                .map(p -> toGetUserDTO(p))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(PostUserDTO postUserDTO) {

        String username;
        User user = fromPostUserDTO(postUserDTO);

        if(userStore.addUser(user)) {
            username = postUserDTO.getUsername();

            URI href = uriInfo
                    .getBaseUriBuilder()
                    .path(RegisterRestApi.class)
                    .path(RegisterRestApi.class, "getUser")
                    .build(username);

            return Response
                    .created(href)
                    .build();

        }
        return Response.notModified("User already exists...")
                .build();
    }


    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetUserDTO getUser(@PathParam(value = "id") String id) {
        User user = userStore.findUser(id);
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
