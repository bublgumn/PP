package org.exampleProjectOne.servlets;

import org.exampleProjectOne.model.User;
import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginIn")
public class Login extends HttpServlet {

    private static final Service service = UserService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = service.getUserByNameAndPassword(name, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("userRole", user.getRole());
            if (user.getRole().equals("admin")) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else if (user.getRole().equals("user")) {
                resp.sendRedirect(req.getContextPath() + "/user");
            }
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
