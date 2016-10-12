package ch.ltouroumov.heig.amt.project1;

import ch.ltouroumov.heig.amt.project1.model.entities.User;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/pages/profile.jsp").forward(request, response);
    }

}