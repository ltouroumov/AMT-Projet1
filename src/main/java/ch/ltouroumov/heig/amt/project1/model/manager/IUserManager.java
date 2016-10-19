package ch.ltouroumov.heig.amt.project1.model.manager;

import ch.ltouroumov.heig.amt.project1.model.entities.User;

import java.util.List;

/**
 * Interface to manage users
 *
 * @author ldavid
 * Created: 10/12/16
 */
public interface IUserManager {

    /**
     * Find all managed users
     * @return List of users
     */
    List<User> findAll();

    /**
     * Find a user by username
     * @param username Username
     * @return The user or null
     */
    User findOne(String username);

    /**
     * Add a user object to the manager
     * @param user User object
     */
    void create(User user);

    /**
     * Update a user object in the manager
     * @param user User object
     */
    void update(User user);

}
