package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.user.*;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String plainPassword = request.getParameter("password");

        IPasswordEncoder encoder = new HashPasswordEncoder();

        IUserStore userStore = MemoryUserStore.instance();
        User user = userStore.findUser(username);
        if (user != null && encoder.check(user.getPassword(), plainPassword)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getServletContext().getContextPath() + "/profile");
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
