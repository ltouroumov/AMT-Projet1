package ch.ltouroumov.heig.amt.project1.router;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a route within the application
 *
 * @author ldavid
 * Created: 9/28/16
 */
public class Route {

    public static final IRouteHandler DEFAULT_HANDLER = new ServletHandler("default");

    /**
     * Pattern for the route
     */
    public String pattern;

    /**
     * Is the pattern a prefix?
     */
    public boolean isPrefix = false;

    /**
     * Handler for the route
     */
    public IRouteHandler handler = DEFAULT_HANDLER;

    /**
     * Filters to apply before forwarding to the handler
     */
    public List<Class<?>> filters = new ArrayList<>();

    public Route(String pattern) {
        this.pattern = pattern;
    }

    /**
     * Sets the route handler
     * @param handler Route handler
     * @return the route
     */
    public Route to(IRouteHandler handler) {
        this.handler = handler;
        return this;
    }

    /**
     * Attempt to match the route against a path
     * @param path Path to match against
     * @return Does the route match?
     */
    public boolean tryMatch(String path) {
        return isPrefix ? path.startsWith(pattern) : path.equals(pattern);
    }

    /**
     * Match the route against a path
     * @param path Path to match against
     * @return the route if it matches, null otherwise
     */
    public Route match(String path) {
        return tryMatch(path) ? this : null;
    }

    /**
     * Places the route in prefix mode
     * @return the route
     */
    public Route prefix() {
        this.isPrefix = true;
        return this;
    }

    /**
     * Adds a filter to the route
     * @param filter filter class
     * @param <T> filter type
     * @return the route
     */
    public <T extends Filter> Route filter(Class<T> filter) {
        filters.add(filter);
        return this;
    }
}
