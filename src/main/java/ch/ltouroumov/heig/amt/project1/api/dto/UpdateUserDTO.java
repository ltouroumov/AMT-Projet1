package ch.ltouroumov.heig.amt.project1.api.dto;

/**
 * Created by Frederic on 12.10.16.
 */
public class UpdateUserDTO {

    private String firstname;
    private String lastname;
    private String email;

    public UpdateUserDTO(){

    }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public void setEmail(String email) { this.email = email; }

    public String getFirstname() { return firstname; }

    public String getLastname() { return lastname; }

    public String getEmail() { return email; }
}
