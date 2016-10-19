package ch.ltouroumov.heig.amt.project1.api;


import ch.ltouroumov.heig.amt.project1.api.dto.*;
import ch.ltouroumov.heig.amt.project1.model.manager.IUserManager;
import ch.ltouroumov.heig.amt.project1.model.entities.User;
import ch.ltouroumov.heig.amt.project1.security.IPasswordEncoder;

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
public class UserRestApi {

    @EJB(beanName = "JdbcUserManager")
    private IUserManager userManager;

    @Context
    UriInfo uriInfo;

    @EJB
    private IPasswordEncoder encoder;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(@QueryParam(value = "byName") String byName) {
        try {
            List<User> users = userManager.findAll();
            return Response.ok(
                    users.stream().filter(p -> byName == null || p.getLastname().equalsIgnoreCase(byName))
                    .map(this::toGetUserDTO)
                    .collect(Collectors.toList())
            ).build();
        } catch (Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(CreateUserDTO createUserDTO) {

        String username;
        User user = fromPostUserDTO(createUserDTO);
        try {
            userManager.create(user);
            username = createUserDTO.getUsername();

            URI href = uriInfo
                    .getBaseUriBuilder()
                    .path(UserRestApi.class)
                    .path(UserRestApi.class, "getUser")
                    .build(username);

            return Response
                    .created(href)
                    .build();

        } catch (Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Path("/{id}/password")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePassword(@PathParam(value = "id") String id, UpdateUserPasswordDTO dto){
        try {
            User user = userManager.findOne(id);
            user.setPassword(encoder.encode(dto.getPassword()));
            return Response.accepted("User password updated successfully!").build();
        } catch (Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateInfoUser(@PathParam(value = "id") String id, UpdateUserDTO dto){
        try {
            User user = userManager.findOne(id);
            user.setFirstname(dto.getFirstname());
            user.setLastname(dto.getLastname());
            user.setEmail(dto.getEmail());
            userManager.update(user);
            return Response.accepted("User info updated successfully!").build();
        } catch (Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }


    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam(value = "id") String id) {
        try {
            User user = userManager.findOne(id);
            return Response.ok(toGetUserDTO(user)).build();
        } catch (Exception ex) {
            return Response.serverError()
                    .entity(new ExceptionDTO(ex))
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    public User fromPostUserDTO(CreateUserDTO createUserDTO){
        User user = new User(createUserDTO.getUsername());
        user.setFirstname(createUserDTO.getFirstname());
        user.setLastname(createUserDTO.getLastname());
        user.setPassword(encoder.encode(createUserDTO.getPassword()));
        user.setEmail(createUserDTO.getEmail());
        return user;
    }


    public UserDTO toGetUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
