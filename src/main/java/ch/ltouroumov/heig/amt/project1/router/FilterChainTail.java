package ch.ltouroumov.heig.amt.project1.router;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

/**
 * End of a filter chain
 *
 * @author ldavid
 * Created: 10/3/16
 */
public class FilterChainTail implements FilterChain {

    private RequestDispatcher handler;

    public FilterChainTail(RequestDispatcher handler) {
        this.handler = handler;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
        handler.forward(request, response);
    }

}
