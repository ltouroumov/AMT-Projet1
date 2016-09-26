package ch.ltouroumov.heig.amt.project1;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by ldavid on 9/26/16.
 */
@WebFilter(urlPatterns = "/*", filterName = "AssetsFilter")
public class AssetsFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String path = req.getRequestURI().substring(req.getContextPath().length());
        if (path.startsWith("/assets")) {
            chain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/controllers" + path).forward(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
