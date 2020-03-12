package org.exampleProjectOne.servlets;

import org.exampleProjectOne.factory.UserDaoFactory;
import org.exampleProjectOne.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/ListUsersServlet")
public class ListUsersServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        /*UserDaoFactory userDaoFactory = new UserDaoFactory();
        System.out.println(userDaoFactory.getDb());*/
        List<User> userList = null;
        /*SessionFactory sessionFactory = createSessionFactory();
        User userOne = new User("Admin", "admin", 1L);
        User userTwo = new User("AdminTwo", "adminTwo", 2L);
        try {
            Session sessionOne = sessionFactory.openSession();
            addUser(userOne, sessionOne);
            sessionOne.close();
            Session sessionTwo = sessionFactory.openSession();
            addUser(userTwo, sessionTwo);
            sessionTwo.close();
            Session sessionThree = sessionFactory.openSession();
            userList = getAllUser(sessionThree);
            sessionThree.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        try {
            userList = new UserDaoFactory().instanceDao().getAllUser();
        } catch (Exception e) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        request.setAttribute("users", userList);
        request.getRequestDispatcher("usersList.jsp").forward(request, resp);

    }
}
