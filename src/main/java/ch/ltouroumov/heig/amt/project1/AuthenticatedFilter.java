package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.model.entities.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ldavid on 10/3/16.
 */
public class AuthenticatedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse)response;

        User user = (User)httpRequest.getSession().getAttribute("user");
        if (user == null) {
            httpResponse.sendRedirect(request.getServletContext().getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }

}
