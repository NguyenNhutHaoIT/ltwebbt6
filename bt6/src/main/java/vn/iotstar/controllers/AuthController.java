package vn.iotstar.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.Impl.UserDaoImpl;
import vn.iotstar.entity.User;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class AuthController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        if (uri.endsWith("/logout")) {
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User u = userDao.login(username, password);
        if (u != null) {
            req.getSession().setAttribute("user", u);
            if (u.getRoleid() == 1)
                resp.sendRedirect(req.getContextPath() + "/user/home");
            else if (u.getRoleid() == 2)
                resp.sendRedirect(req.getContextPath() + "/manager/home");
            else if (u.getRoleid() == 3)
                resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else {
            req.setAttribute("error", "Sai tài khoản hoặc mật khẩu!");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}