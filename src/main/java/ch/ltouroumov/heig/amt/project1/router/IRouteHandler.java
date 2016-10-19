package ch.ltouroumov.heig.amt.project1.router;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;

/**
 * Defines a handler for a route
 *
 * @author ldavid
 * Created: 10/19/16
 */
public interface IRouteHandler {

    /**
     * Obtain the request dispatcher for the handler.
     *
     * @param request Current request
     */
    RequestDispatcher setup(ServletRequest request);

}
