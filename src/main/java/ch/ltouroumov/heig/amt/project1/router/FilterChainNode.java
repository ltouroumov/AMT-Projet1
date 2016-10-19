package ch.ltouroumov.heig.amt.project1.router;

import javax.servlet.*;
import java.io.IOException;

/**
 * Element of a filter chain
 *
 * @author ldavid
 * Created: 10/3/16
 */
public class FilterChainNode implements FilterChain {

    private Filter current;
    private FilterChain next;

    public FilterChainNode(Filter current, FilterChain next) {
        this.current = current;
        this.next = next;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        current.doFilter(request, response, next);
    }
}
