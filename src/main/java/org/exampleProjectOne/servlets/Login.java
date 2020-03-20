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

@WebServlet(urlPatterns = "/newLoginIn")
public class Login extends HttpServlet {

    private static final Service service = UserService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = service.getUserByName(name);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                req.getSession().setAttribute("name", name);
                req.getSession().setAttribute("password", password);
                resp.sendRedirect(req.getContextPath() + "/userPage");
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
