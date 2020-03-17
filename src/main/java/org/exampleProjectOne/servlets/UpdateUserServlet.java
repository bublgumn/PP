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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {

    private static final Service service = UserService.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        Long age = Long.parseLong(req.getParameter("age"));
        User updateUser = new User(id, name, password, age);
        service.updateClient(updateUser);
        resp.sendRedirect(req.getContextPath() + "/ListUsersServlet");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        try {
            User user = service.getUserById(id);
            req.setAttribute("user", user);
            req.getRequestDispatcher("editUser.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
