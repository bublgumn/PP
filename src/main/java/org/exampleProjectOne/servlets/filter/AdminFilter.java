package org.exampleProjectOne.servlets.filter;


import org.exampleProjectOne.model.User;
import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/admin")
public class AdminFilter implements Filter {
    private static final Service service = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String name = req.getSession().getAttribute("name").toString();
        String password = req.getSession().getAttribute("password").toString();
        System.out.println(name);
        System.out.println(password);

        User user = service.getUserByName(name);

        if (user.getRole().equals("admin")) {
            List<User> userList = service.getAllUser();
            req.setAttribute("users", userList);
            req.getRequestDispatcher("usersList.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }

    }

    @Override
    public void destroy() {

    }
}
