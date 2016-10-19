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
 * Handles user registration
 *
 * @author ldavid
 * Created: 9/28/16
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @EJB(beanName = "JdbcUserManager")
    private IUserManager userManager;

    @EJB
    private IPasswordEncoder encoder;

    /**
     * Handles user registration
     *
     * Redirects to profile page on success or re-displays the profile on failure
     *
     * {@inheritDoc}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String plainPassword = request.getParameter("password");
        String plainPasswordConfirm = request.getParameter("password-confirm");

        User user = new User(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        if (!plainPassword.equals(plainPasswordConfirm)) {
            request.setAttribute("error", "Passwords do not match");
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        user.setPassword(encoder.encode(plainPassword));

        try {
            userManager.create(user);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getServletContext().getContextPath() + "/profile");
        } catch(Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.setAttribute("user", user);
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        }

    }

    /**
     * Displays register form
     *
     * {@inheritDoc}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", null);
        request.setAttribute("user", null);
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }
}
