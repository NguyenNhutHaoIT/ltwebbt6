package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import vn.iotstar.entity.Video;
import vn.iotstar.entity.User;
import vn.iotstar.services.VideoService;
import vn.iotstar.services.Impl.VideoServiceImpl;
import jakarta.servlet.annotation.MultipartConfig;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 1,  // 1 MB
    maxFileSize = 1024 * 1024 * 10,       // 10 MB
    maxRequestSize = 1024 * 1024 * 15     // 15 MB
)
@WebServlet(urlPatterns = {
        "/admin/videos",
        "/admin/video-insert",
        "/admin/video-edit",
        "/admin/video-update",
        "/admin/video-delete",
        "/admin/video-search"
})
public class VideoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    VideoService videoService = new VideoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();

        if (uri.endsWith("/admin/videos")) {
            req.setAttribute("videos", videoService.findAll());
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        } else if (uri.endsWith("/admin/video-insert")) {
            req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
        } else if (uri.endsWith("/admin/video-edit")) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("video", videoService.findById(id));
            req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
        } else if (uri.endsWith("/admin/video-delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            videoService.delete(id);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (uri.endsWith("/admin/video-search")) {
            String keyword = req.getParameter("keyword");
            List<Video> result = videoService.search(keyword);
            req.setAttribute("videos", result);
            req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI();
        User u = (User) req.getSession().getAttribute("user");

        if (uri.endsWith("/admin/video-insert")) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            Part posterPart = req.getPart("poster");
            String fileName = Paths.get(posterPart.getSubmittedFileName()).getFileName().toString();
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();
            posterPart.write(uploadPath + File.separator + fileName);

            Video v = new Video();
            v.setTitle(title);
            v.setDescription(description);
            v.setPoster("uploads/" + fileName);
            v.setUser(u);

            videoService.create(v);
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        } else if (uri.endsWith("/admin/video-update")) {
            int id = Integer.parseInt(req.getParameter("id"));
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            Video v = videoService.findById(id);
            if (v != null) {
                v.setTitle(title);
                v.setDescription(description);

                Part posterPart = req.getPart("poster");
                if (posterPart != null && posterPart.getSize() > 0) {
                    String fileName = Paths.get(posterPart.getSubmittedFileName()).getFileName().toString();
                    String uploadPath = req.getServletContext().getRealPath("/uploads");
                    File uploadDir = new File(uploadPath);
                    if (!uploadDir.exists()) uploadDir.mkdirs();
                    posterPart.write(uploadPath + File.separator + fileName);
                    v.setPoster("uploads/" + fileName);
                }

                videoService.update(v);
            }
            resp.sendRedirect(req.getContextPath() + "/admin/videos");
        }
    }
}
