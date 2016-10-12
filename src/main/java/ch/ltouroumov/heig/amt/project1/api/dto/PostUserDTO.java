package ch.ltouroumov.heig.amt.project1.api.dto;


public class PostUserDTO extends GetUserDTO {

    private String password;

    public PostUserDTO(){
        super();
    }

    public PostUserDTO(String username){
        super(username);
    }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }
}
