package ch.ltouroumov.heig.amt.project1.api.dto;

/**
 * @author ldavid
 * Created: 10/17/16
 */
public class ExceptionDTO {

    private String type;
    private String message;

    public ExceptionDTO(Exception ex) {
        this.type = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
