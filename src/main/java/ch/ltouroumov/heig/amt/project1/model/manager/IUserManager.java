package ch.ltouroumov.heig.amt.project1.model.manager;

import ch.ltouroumov.heig.amt.project1.model.entities.User;

import java.util.List;

/**
 * @author ldavid
 * Created: 10/12/16
 */
public interface IUserManager {

    List<User> findAll();

    User findOne(String username);

    void create(User user);

    void update(User user);

}
