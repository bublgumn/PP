package org.exampleProjectOne.servlets.filter;

import org.exampleProjectOne.model.User;
import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter(urlPatterns = "/*")
public class AllFilter implements Filter {
    private static final Service service = UserService.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String userRole = (String) req.getSession().getAttribute("userRole");
        User user = (User) req.getSession().getAttribute("user");
        String url = req.getRequestURI().trim();

        boolean urlFirst = url.equalsIgnoreCase("/PP_war_exploded/");
        boolean urlLoginIn = url.equalsIgnoreCase("/PP_war_exploded/loginIn");
        boolean urlUserPage = url.equalsIgnoreCase("/PP_war_exploded/userPage");
        boolean urlAdmin = url.equalsIgnoreCase("/PP_war_exploded/admin");
        boolean urlUser = url.equalsIgnoreCase("/PP_war_exploded/user");
        boolean urlCreate = url.equalsIgnoreCase("/PP_war_exploded/CreateUserServlet");
        boolean urlUpdateGet = url.equalsIgnoreCase("/PP_war_exploded/UpdateUserServlet");
        boolean urlUpdatePost = url.equalsIgnoreCase("/PP_war_exploded/admin/UpdateUserServlet");
        boolean urlDelete = url.equalsIgnoreCase("/PP_war_exploded/admin/DeleteUserServlet");

        if (urlFirst) {
            filter.doFilter(req, resp);
        } else if (urlLoginIn) {
            String name = req.getParameter("name");
            String password = req.getParameter("password");
            if (name != null && password != null) {
                filter.doFilter(req, resp);
            }
        } else if (urlUserPage) {
            if (userRole != null && user != null) {
                filter.doFilter(req, resp);
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                filter.doFilter(req, resp);
            }
        } else if (urlUser) {
            if (userRole != null && user != null) {
                filter.doFilter(req, resp);
            } else {
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                filter.doFilter(req, resp);
            }
        } else if (urlAdmin) {
            filter.doFilter(req, resp);
        } else if (urlCreate) {
            filter.doFilter(req, resp);
        } else if (urlUpdateGet) {
            filter.doFilter(req, resp);
        } else if (urlUpdatePost) {
            filter.doFilter(req, resp);
        } else if (urlDelete) {
            filter.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}