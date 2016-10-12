package ch.ltouroumov.heig.amt.project1.model.manager;

import java.sql.SQLException;

/**
 * @author ldavid
 *         Created: 10/12/16
 */
public class ManagerException extends RuntimeException {

    public ManagerException(String message) {
        super(message);
    }

    public ManagerException(String message, Exception ex) {
        super(message, ex);
    }
}
