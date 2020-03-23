package org.exampleProjectOne.servlets;

import org.exampleProjectOne.model.User;
import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/userPage")
public class UserPage extends HttpServlet {

    private static final Service service = UserService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userRole = req.getSession().getAttribute("userRole").toString();
        if (userRole.equals("admin")) {
            resp.sendRedirect(req.getContextPath() + "/admin");
        } else if (userRole.equals("user")) {
            resp.sendRedirect(req.getContextPath() + "/user");
        }
    }
}
