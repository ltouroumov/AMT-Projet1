package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.model.entities.User;
import ch.ltouroumov.heig.amt.project1.user.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ldavid on 10/3/16.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    @EJB
    private IUserStore userStore;

    @EJB
    private IPasswordEncoder encoder;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");

        User user = userStore.findUser(username);
        if (user != null && encoder.check(user.getPassword(), plainPassword)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getServletContext().getContextPath() + "/");
        } else {
            request.setAttribute("error", "Failed to log in");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", null);
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
}
