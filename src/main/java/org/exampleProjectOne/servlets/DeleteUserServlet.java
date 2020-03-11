package org.exampleProjectOne.servlets;

import org.exampleProjectOne.factory.UserDaoFactory;
import org.exampleProjectOne.model.User;
import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserJdbcService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("name");
        if (email != null) {
            Service service = new UserDaoFactory().instanceDao();
            try {
                List<User> userList = service.getUserByName(email);
                if (userList.size() > 0) {
                    service.deleteUser(userList.get(0));
                }
                List<User> userLists = null;
                try {
                    userLists = service.getAllUser();
                } catch (Exception e) {
                    resp.setContentType("text/html;charset=utf-8");
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                }
                req.setAttribute("users", userLists);
                req.getRequestDispatcher("usersList.jsp").forward(req, resp);
            } catch (Exception e) {
                resp.setContentType("text/html;charset=utf-8");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

    }
}
