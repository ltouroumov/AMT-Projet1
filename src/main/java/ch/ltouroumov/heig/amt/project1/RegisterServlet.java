package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.user.*;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String plainPassword = request.getParameter("password");
        String plainPasswordConfirm = request.getParameter("password-confirm");

        if (!plainPassword.equals(plainPasswordConfirm)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("/WEB-INF/pages/register.jsp").forward(request, response);
            return;
        }

        User user = new User(username);
        user.setEmail(email);

        IPasswordEncoder encoder = new HashPasswordEncoder();
        user.setPassword(encoder.encode(plainPassword));

        IUserStore userStore = MemoryUserStore.instance();
        if (userStore.register(user)) {
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
