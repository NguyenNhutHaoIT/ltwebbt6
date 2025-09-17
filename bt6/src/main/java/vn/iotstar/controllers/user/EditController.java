package vn.iotstar.controllers.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.iotstar.entity.User;
import vn.iotstar.services.UserService;
import vn.iotstar.services.Impl.UserServiceImpl;

@WebServlet(urlPatterns = {"/user/edit", "/user/update"})
public class EditController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Truyền user vào request để edit.jsp hiển thị
        req.setAttribute("user", user);
        req.getRequestDispatcher("/views/user/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String path = req.getServletPath();

        if (path.equals("/user/update")) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            if (user != null) {
                // Chỉ cập nhật thông tin cho phép
                String newPassword = req.getParameter("password");
                String roleidStr = req.getParameter("roleid");

                if (newPassword != null && !newPassword.isEmpty()) {
                    user.setPassword(newPassword);
                }

                if (roleidStr != null) {
                    try {
                        int roleid = Integer.parseInt(roleidStr);
                        user.setRoleid(roleid);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }

                // Update DB
                userService.update(user);

                // Update session
                session.setAttribute("user", user);
            }

            resp.sendRedirect(req.getContextPath() + "/user/home");
        }
    }
}