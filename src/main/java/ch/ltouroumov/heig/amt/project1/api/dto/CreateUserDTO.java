package ch.ltouroumov.heig.amt.project1.api.dto;


public class CreateUserDTO extends UserDTO {

    private String password;

    public CreateUserDTO(){
        super();
    }

    public CreateUserDTO(String username){
        super(username);
    }

    public String getPassword() { return this.password; }
    public void setPassword(String password) { this.password = password; }
}
