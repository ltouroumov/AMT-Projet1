package ch.ltouroumov.heig.amt.project1.api;


import ch.ltouroumov.heig.amt.project1.api.dto.UserDTO;
import ch.ltouroumov.heig.amt.project1.user.IUserStore;
import ch.ltouroumov.heig.amt.project1.user.User;

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
@Path("/user")
public class RegisterRestApi {

    @EJB
    private IUserStore userStore;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserDTO> getUsers(@QueryParam(value = "byName") String byName) {

        List<User> users = userStore.getUsers();
        return users.stream().filter(p -> byName == null || p.getLastname().equalsIgnoreCase(byName))
                .map(p -> toUserDTO(p))
                .collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(UserDTO userDTO) {

        String username;
        User user = fromUserDTO(userDTO);

        if(userStore.addUser(user)) {
            username = userDTO.getUsername();

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
    public UserDTO getUser(@PathParam(value = "id") String id) {
        User user = userStore.findUser(id);
        return toUserDTO(user);
    }


    public User fromUserDTO(UserDTO userDTO){
        User user = new User(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getUsername() + "_1234");
        return user;
    }


    public UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
