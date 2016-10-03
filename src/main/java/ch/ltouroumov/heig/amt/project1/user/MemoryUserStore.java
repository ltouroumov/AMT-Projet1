package ch.ltouroumov.heig.amt.project1.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ldavid on 10/3/16.
 */
public class MemoryUserStore implements IUserStore {

    private static MemoryUserStore _instance;

    public static synchronized MemoryUserStore instance() {
        if (_instance == null) {
            _instance = new MemoryUserStore();
        }
        return _instance;
    }

    private Map<String, User> users = new HashMap<>();

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
