package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.Impl.CategoryDaoImpl;
import vn.iotstar.dao.Impl.UserDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 15 // 15MB
)
@WebServlet(urlPatterns = {
        "/admin/home",
        "/admin/insert-categories", "/admin/edit-categories", "/admin/delete-categories",
        "/admin/insert-users", "/admin/edit-users", "/admin/delete-users"
})
public class HomeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    CategoryDao cateDao = new CategoryDaoImpl();
    UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.endsWith("/admin/home")) {
            req.setAttribute("listcate", cateDao.findAll());
            req.setAttribute("listuser", userDao.findAll());
            req.getRequestDispatcher("/views/admin/home.jsp").forward(req, resp);

        } else if (uri.endsWith("/admin/insert-categories")) {
            req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

        } else if (uri.endsWith("/admin/edit-categories")) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("cate", cateDao.findById(id));
            req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

        } else if (uri.endsWith("/admin/delete-categories")) {
            int id = Integer.parseInt(req.getParameter("id"));
            cateDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/home");

        } else if (uri.endsWith("/admin/insert-users")) {
            req.getRequestDispatcher("/views/admin/user-add.jsp").forward(req, resp);

        } else if (uri.endsWith("/admin/edit-users")) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("userEdit", userDao.findById(id));
            req.getRequestDispatcher("/views/admin/user-edit.jsp").forward(req, resp);

        } else if (uri.endsWith("/admin/delete-users")) {
            int id = Integer.parseInt(req.getParameter("id"));
            userDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        User sessionUser = (User) req.getSession().getAttribute("user");

        // ===== Category CRUD =====
        if (uri.endsWith("/admin/insert-categories")) {
            String name = req.getParameter("categoryname");
            Part iconPart = req.getPart("icon");

            String fileName = Paths.get(iconPart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            iconPart.write(uploadPath + File.separator + fileName);

            Category c = new Category();
            c.setCategoryname(name);
            c.setIcon("uploads/" + fileName);
            c.setUser(sessionUser);

            cateDao.create(c);
            resp.sendRedirect(req.getContextPath() + "/admin/home");

        } else if (uri.endsWith("/admin/edit-categories")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("categoryname");

            Category c = cateDao.findById(id);
            if (c != null) {
                c.setCategoryname(name);

                Part iconPart = req.getPart("icon");
                if (iconPart != null && iconPart.getSize() > 0) {
                    String fileName = Paths.get(iconPart.getSubmittedFileName()).getFileName().toString();
                    String uploadPath = req.getServletContext().getRealPath("/uploads");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
                    iconPart.write(uploadPath + File.separator + fileName);

                    c.setIcon("uploads/" + fileName);
                }
                cateDao.update(c);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/home");

        // ===== User CRUD =====
        } else if (uri.endsWith("/admin/insert-users")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            int roleid = Integer.parseInt(req.getParameter("roleid"));

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setRoleid(roleid);

            userDao.create(newUser);
            resp.sendRedirect(req.getContextPath() + "/admin/home");

        } else if (uri.endsWith("/admin/edit-users")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            int roleid = Integer.parseInt(req.getParameter("roleid"));

            User editUser = userDao.findById(id);
            if (editUser != null) {
                editUser.setUsername(username);
                editUser.setPassword(password);
                editUser.setRoleid(roleid);
                userDao.update(editUser);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        }
    }
}

