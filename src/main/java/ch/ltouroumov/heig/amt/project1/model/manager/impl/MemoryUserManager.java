package ch.ltouroumov.heig.amt.project1.model.manager.impl;

import ch.ltouroumov.heig.amt.project1.model.entities.User;
import ch.ltouroumov.heig.amt.project1.model.manager.IUserManager;
import org.apache.commons.codec.digest.DigestUtils;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manages users in memory
 * @author ldavid
 * Created: 10/12/16
 */
@Singleton
public class MemoryUserManager implements IUserManager {

    private Map<String, User> users = new HashMap<>();

    public MemoryUserManager() {
        User admin = new User("admin");
        admin.setEmail("admin@localhost");
        admin.setPassword(DigestUtils.sha1Hex("admin"));
        create(admin);
    }

    @Override
    public List<User> findAll() {
        return users.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public User findOne(String username) {
        return users.get(username);
    }

    @Override
    public void create(User user) {
        if (!users.containsKey(user.getUsername())) {
            users.put(user.getUsername(), user);
        }
    }

    @Override
    public void update(User user) {
        // Do nothing
    }

}
