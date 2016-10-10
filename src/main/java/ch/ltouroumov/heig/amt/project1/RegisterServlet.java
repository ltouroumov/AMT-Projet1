package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.user.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ldavid on 9/28/16.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {

    @EJB
    private IUserStore userStore;

    @EJB
    private IPasswordEncoder encoder;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String plainPassword = request.getParameter("password");
        String plainPasswordConfirm = request.getParameter("password-confirm");

        if (!plainPassword.equals(plainPasswordConfirm)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        User user = new User(username);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        user.setPassword(encoder.encode(plainPassword));

        if (userStore.addUser(user)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getServletContext().getContextPath() + "/profile");
        } else {
            request.setAttribute("error", "Username not available");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", null);
        request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
    }
}
