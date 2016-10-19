package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.model.entities.User;
import ch.ltouroumov.heig.amt.project1.model.manager.IUserManager;
import ch.ltouroumov.heig.amt.project1.security.IPasswordEncoder;

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
@WebServlet(name = "ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @EJB(beanName = "JdbcUserManager")
    private IUserManager userManager;

    @EJB
    private IPasswordEncoder encoder;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        String password = req.getParameter("password");
        String passwordConfirm = req.getParameter("password-confirm");

        User user = (User)req.getSession().getAttribute("user");
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        if (!password.isEmpty() && !passwordConfirm.isEmpty() && password.equals(passwordConfirm)) {
            user.setPassword(encoder.encode(password));
        } else {
            req.setAttribute("error", "Passwords do not match");
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
            return;
        }

        try {
            userManager.update(user);

            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getServletContext().getContextPath() + "/profile");
        } catch (IOException e) {
            req.setAttribute("error", e.getMessage());
            req.setAttribute("user", user);
            req.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }

}