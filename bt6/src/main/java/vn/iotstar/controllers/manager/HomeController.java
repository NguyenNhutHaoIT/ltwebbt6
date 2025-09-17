package vn.iotstar.controllers.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.Impl.CategoryDaoImpl;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.User;
import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 15 // 15MB
)
@WebServlet(urlPatterns = { "/manager/home", "/manager/insert", "/manager/edit", "/manager/delete" })
public class HomeController extends HttpServlet {
	CategoryDao cateDao = new CategoryDaoImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String uri = req.getRequestURI();
		User u = (User) req.getSession().getAttribute("user");

		if (uri.endsWith("/manager/home")) {
			req.setAttribute("listcate", cateDao.findByUserId(u.getId()));
//		if (uri.endsWith("/manager/home")) {
//			req.setAttribute("listcate", cateDao.findAll());
			req.getRequestDispatcher("/views/manager/home.jsp").forward(req, resp);
		} else if (uri.endsWith("/manager/insert")) {
			req.getRequestDispatcher("/views/manager/category-add.jsp").forward(req, resp);
		} else if (uri.endsWith("/manager/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("cate", cateDao.findById(id));
			req.getRequestDispatcher("/views/manager/category-edit.jsp").forward(req, resp);
		} else if (uri.endsWith("/manager/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			cateDao.delete(id);
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		User u = (User) req.getSession().getAttribute("user");

		if (uri.endsWith("/manager/insert")) {
			String name = req.getParameter("categoryname");
			Part iconPart = req.getPart("icon");
			String fileName = Paths.get(iconPart.getSubmittedFileName()).getFileName().toString();
			String uploadPath = req.getServletContext().getRealPath("/uploads");
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists())
				uploadDir.mkdirs();
			iconPart.write(uploadPath + File.separator + fileName);

			Category c = new Category();
			c.setCategoryname(name);
			c.setIcon("uploads/" + fileName);
			c.setUser(u);

			cateDao.create(c);
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		} else if (uri.endsWith("/manager/edit")) {
		    int id = Integer.parseInt(req.getParameter("id"));
		    String name = req.getParameter("categoryname");

		    Category c = cateDao.findById(id);
		    if (c != null) {
		        c.setCategoryname(name);

		        // Xử lý upload ảnh mới (nếu có)
		        Part iconPart = req.getPart("icon");
		        if (iconPart != null && iconPart.getSize() > 0) {
		            String fileName = Paths.get(iconPart.getSubmittedFileName()).getFileName().toString();
		            String uploadPath = req.getServletContext().getRealPath("/uploads");
		            File uploadDir = new File(uploadPath);
		            if (!uploadDir.exists()) uploadDir.mkdirs();
		            iconPart.write(uploadPath + File.separator + fileName);

		            c.setIcon("uploads/" + fileName); // cập nhật đường dẫn ảnh mới
		        }

		        cateDao.update(c);
		    }
		    resp.sendRedirect(req.getContextPath() + "/manager/home");
		}

	}

}
