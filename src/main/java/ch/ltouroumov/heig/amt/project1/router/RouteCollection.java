package ch.ltouroumov.heig.amt.project1.router;

import java.util.ArrayList;

/**
 * Created by ldavid on 9/28/16.
 */
public class RouteCollection extends Route {

    private ArrayList<Route> routes = new ArrayList<>();

    public RouteCollection() {
        super(null);
    }

    public RouteCollection(String pattern) {
        super(pattern);
    }

    public Route map(String pattern) {
        Route route = new Route(pattern);
        routes.add(route);

        return route;
    }

    public RouteCollection mount(String prefix) {
        RouteCollection routeCollection = new RouteCollection(prefix);
        routes.add(routeCollection);
        return routeCollection;
    }

    @Override
    public boolean tryMatch(String path) {
        return pattern == null || path.startsWith(pattern);
    }

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

    private String getSubPath(String path) {
        if (pattern != null) {
            return path.substring(pattern.length());
        } else {
            return path;
        }
    }
}
