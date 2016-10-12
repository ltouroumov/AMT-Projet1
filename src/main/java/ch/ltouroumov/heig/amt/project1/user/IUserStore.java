package ch.ltouroumov.heig.amt.project1.user;

import ch.ltouroumov.heig.amt.project1.model.entities.User;

import java.util.List;

/**
 * Created by ldavid on 10/3/16.
 */
public interface IUserStore {

    boolean addUser(User user);

    User findUser(String username);
    List<User> getUsers();

}
