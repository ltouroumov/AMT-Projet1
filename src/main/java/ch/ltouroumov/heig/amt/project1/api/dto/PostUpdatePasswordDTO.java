package ch.ltouroumov.heig.amt.project1.api.dto;

/**
 * Created by Frederic on 12.10.16.
 */
public class PostUpdatePasswordDTO {

    private String password;

    public PostUpdatePasswordDTO() {

    }

    public void setPassword(String password) { this.password = password; }

    public String getPassword() { return password; }
}
