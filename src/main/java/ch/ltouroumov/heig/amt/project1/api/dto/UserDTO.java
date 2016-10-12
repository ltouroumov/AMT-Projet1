package ch.ltouroumov.heig.amt.project1.api.dto;

/**
 * Created by Frederic on 05.10.16.
 */
public class UserDTO {

    private String username;
    private String firstname;
    private String lastname;
    private String email;

    public UserDTO() {}

    public UserDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
