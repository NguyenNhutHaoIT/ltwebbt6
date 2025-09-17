package vn.iotstar.controllers.user;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.iotstar.dao.CategoryDao;	
import vn.iotstar.dao.Impl.CategoryDaoImpl;

@WebServlet(urlPatterns = { "/user/home" })
public class HomeController extends HttpServlet {
	CategoryDao cateDao = new CategoryDaoImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setAttribute("listcate", cateDao.findAll());
		req.getRequestDispatcher("/views/user/home.jsp").forward(req, resp);
	}

}