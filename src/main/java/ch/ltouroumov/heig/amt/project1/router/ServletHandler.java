package ch.ltouroumov.heig.amt.project1.router;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;

/**
 * Allows a route to be handled by a servlet
 *
 * @author ldavid
 * Created: 10/19/16
 */
public class ServletHandler implements IRouteHandler {

    private final String servletName;

    public ServletHandler(String servletName) {
        this.servletName = servletName;
    }

    @Override
    public RequestDispatcher setup(ServletRequest request) {
        return request.getServletContext().getNamedDispatcher(servletName);
    }

}
