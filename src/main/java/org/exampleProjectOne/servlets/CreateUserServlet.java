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
import java.util.List;


@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("name");
        String password = req.getParameter("password");
        Long age = Long.parseLong(req.getParameter("age"));
        User addUser = null;
        if (email != null && password != null && age != null) {
            addUser = new User(email, password, age);
        }
        Service service = UserService.getInstance();
        try {
            if (service.addUser(addUser)) {
                List<User> userList = service.getUserByName(addUser.getEmail());
                req.setAttribute("users", userList);
                req.getRequestDispatcher("showUser.jsp").forward(req, resp);
            } else {
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createUser.jsp").forward(request, response);
    }

}
