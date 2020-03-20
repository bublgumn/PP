package org.exampleProjectOne.servlets;

import org.exampleProjectOne.factory.UserDaoFactory;
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


@WebServlet("/admin")
public class ListUsersServlet extends HttpServlet {

    private static final Service service = UserService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String name = session.getAttribute("name").toString();
        String password = session.getAttribute("password").toString();

        User user = service.getUserByName(name);

        if (user != null && user.getPassword().equals(password)) {
            if (user.getRole().equals("admin")) {
                try {
                    List<User> userList = service.getAllUser();
                    req.setAttribute("users", userList);
                    req.getRequestDispatcher("usersList.jsp").forward(req, resp);
                } catch (Exception e) {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
