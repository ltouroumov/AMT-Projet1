package ch.ltouroumov.heig.amt.project1.router;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;

/**
 * The route will forward to another path.
 *
 * @author ldavid
 * Created: 10/19/16
 */
public class ForwardHandler implements IRouteHandler {

    private final String path;

    public ForwardHandler(String path) {
        this.path = path;
    }

    @Override
    public RequestDispatcher setup(ServletRequest request) {
        return request.getRequestDispatcher(path);
    }

}
