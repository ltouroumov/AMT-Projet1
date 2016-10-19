package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.model.entities.User;
import ch.ltouroumov.heig.amt.project1.model.manager.IUserManager;
import ch.ltouroumov.heig.amt.project1.security.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Display login form and authenticate user by establishing session
 *
 * @author ldavid
 * Created: 10/3/16
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    @EJB(beanName = "JdbcUserManager")
    private IUserManager userStore;

    @EJB
    private IPasswordEncoder encoder;

    /**
     * Authenticates the user and establishes the session
     *
     * {@inheritDoc}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");

        User user = userStore.findOne(username);
        if (user != null && encoder.check(user.getPassword(), plainPassword)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getServletContext().getContextPath() + "/");
        } else {
            request.setAttribute("error", "Failed to log in");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }

    }

    /**
     * Display login form
     *
     * {@inheritDoc}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", null);
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
}
