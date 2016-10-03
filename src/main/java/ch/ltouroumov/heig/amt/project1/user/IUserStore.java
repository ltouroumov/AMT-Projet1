package ch.ltouroumov.heig.amt.project1.user;

/**
 * Created by ldavid on 10/3/16.
 */
public interface IUserStore {

    boolean addUser(User user);

    User findUser(String username);

}
