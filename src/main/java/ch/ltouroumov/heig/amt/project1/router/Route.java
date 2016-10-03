package ch.ltouroumov.heig.amt.project1.router;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldavid on 9/28/16.
 */
public class Route {

    public String pattern;
    public boolean isPrefix = false;
    public String handler = "default";
    public List<Class<?>> filters = new ArrayList<>();

    public Route(String pattern) {
        this.pattern = pattern;
    }

    public Route to(String handler) {
        this.handler = handler;
        return this;
    }

    public boolean tryMatch(String path) {
        return isPrefix ? path.startsWith(pattern) : path.equals(pattern);
    }

    public Route match(String path) {
        return tryMatch(path) ? this : null;
    }

    public Route prefix() {
        this.isPrefix = true;
        return this;
    }

    public <T extends Filter> Route filter(Class<T> filter) {
        filters.add(filter);
        return this;
    }
}
