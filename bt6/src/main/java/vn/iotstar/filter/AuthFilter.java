package vn.iotstar.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iotstar.entity.User;

@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String uri = req.getRequestURI();
        User u = (User) req.getSession().getAttribute("user");

        if (uri.endsWith("login") || uri.endsWith("login.jsp") || uri.endsWith("logout") 
                || uri.contains("css") || uri.contains("js")) {
            chain.doFilter(request, response);
            return;
        }

        if (u == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if (uri.contains("/user/") && u.getRoleid() != 1) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        if (uri.contains("/manager/") && u.getRoleid() != 2) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }
        if (uri.contains("/admin/") && u.getRoleid() != 3) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        chain.doFilter(request, response);
    }
}