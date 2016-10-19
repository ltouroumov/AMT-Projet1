package ch.ltouroumov.heig.amt.project1.model.manager;

import java.sql.SQLException;

/**
 * Runtime exception wrapping an error occuring during a manager operation
 *
 * @author ldavid
 * Created: 10/12/16
 */
public class ManagerException extends RuntimeException {

    /**
     * Exception with a message
     *
     * @param message Exception message
     */
    public ManagerException(String message) {
        super(message);
    }

    /**
     * Exception wrapping another exception
     * @param message Exception message
     * @param ex Exception to wrap
     */
    public ManagerException(String message, Exception ex) {
        super(message, ex);
    }
}
