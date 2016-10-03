package ch.ltouroumov.heig.amt.project1.user;

import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ldavid on 10/3/16.
 */
@Singleton
public class MemoryUserStore implements IUserStore {

    private Map<String, User> users = new HashMap<>();

    public MemoryUserStore() {
        User admin = new User("admin");
        admin.setEmail("admin@localhost");
        admin.setPassword(DigestUtils.sha1Hex("admin"));
        addUser(admin);
    }

    @Override
    public synchronized boolean addUser(User user) {
        if (users.containsKey(user.getUsername())) {
            return false;
        } else {
            users.put(user.getUsername(), user);
            return true;
        }
    }

    @Override
    public synchronized User findUser(String username) {
        return users.get(username);
    }
}