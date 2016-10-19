package ch.ltouroumov.heig.amt.project1.router;

import java.util.ArrayList;

/**
 * Represents a collection of routes.
 *
 * @author ldavid
 * Created: 9/28/16
 */
public class RouteCollection extends Route {

    /**
     * sub-routes
     */
    private ArrayList<Route> routes = new ArrayList<>();

    /**
     * Creates a route collection without a prefix path.
     */
    public RouteCollection() {
        super(null);
    }

    /**
     * Creates a route collection with a prefix path.
     * @param pattern Prefix path.
     */
    public RouteCollection(String pattern) {
        super(pattern);
    }

    /**
     * Map a route to a pattern.
     * @param pattern Route pattern
     * @return the route
     */
    public Route map(String pattern) {
        Route route = new Route(pattern);
        routes.add(route);

        return route;
    }

    /**
     * Mount a route collection to a prefix.
     * @param prefix Collection prefix
     * @return the route collection
     */
    public RouteCollection mount(String prefix) {
        RouteCollection routeCollection = new RouteCollection(prefix);
        routes.add(routeCollection);
        return routeCollection;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean tryMatch(String path) {
        return pattern == null || path.startsWith(pattern);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Route match(String path) {
        Route match = null;

        if (tryMatch(path)) {
            String subPath = getSubPath(path);

            for (Route route : routes) {
                if (route.tryMatch(subPath)) {
                    match = route.match(subPath);
                }
            }
        }

        return match;
    }

    /**
     * Strip the prefix from the path
     * @param path Complete path
     * @return Stripped path
     */
    private String getSubPath(String path) {
        if (pattern != null) {
            return path.substring(pattern.length());
        } else {
            return path;
        }
    }
}
