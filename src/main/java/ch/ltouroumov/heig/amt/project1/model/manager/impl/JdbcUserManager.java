package ch.ltouroumov.heig.amt.project1.model.manager.impl;

import ch.ltouroumov.heig.amt.project1.model.entities.User;
import ch.ltouroumov.heig.amt.project1.model.manager.IUserManager;
import ch.ltouroumov.heig.amt.project1.model.manager.ManagerException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages users with a JDBC connection
 *
 * @author ldavid
 * Created: 10/12/16
 */
@Stateless
public class JdbcUserManager implements IUserManager {

    @Resource(lookup = "java:/app/db")
    private DataSource dataSource;

    @Override
    public List<User> findAll() {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("SELECT * FROM users");
            ResultSet result = query.executeQuery();

            List<User> users = new ArrayList<>();
            while (result.next()) {
                User user = new User(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setFirstname(result.getString("first_name"));
                user.setLastname(result.getString("last_name"));

                users.add(user);
            }

            return users;
        } catch (SQLException ex) {
            throw new ManagerException("Error querying users", ex);
        }
    }

    @Override
    public User findOne(String username) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
            query.setString(1, username);
            ResultSet result = query.executeQuery();

            if (result.next()) {
                User user = new User(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
                user.setFirstname(result.getString("first_name"));
                user.setLastname(result.getString("last_name"));

                return user;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            throw new ManagerException("Error querying user", ex);
        }
    }

    @Override
    public void create(User user) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("INSERT INTO users(username, password, email, first_name, last_name) VALUES(?, ?, ?, ?, ?)");
            query.setString(1, user.getUsername());
            query.setString(2, user.getPassword());
            query.setString(3, user.getEmail());
            query.setString(4, user.getFirstname());
            query.setString(5, user.getLastname());
            query.execute();
        } catch (SQLException ex) {
            throw new ManagerException("Failed to create user", ex);
        }
    }

    @Override
    public void update(User user) {
        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement query = conn.prepareStatement("UPDATE users SET password = ?, email = ?, first_name = ?, last_name = ? WHERE username = ?");
            query.setString(1, user.getPassword());
            query.setString(2, user.getEmail());
            query.setString(3, user.getFirstname());
            query.setString(4, user.getLastname());
            query.setString(5, user.getUsername());
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new ManagerException("Failed to create user", ex);
        }
    }
}
