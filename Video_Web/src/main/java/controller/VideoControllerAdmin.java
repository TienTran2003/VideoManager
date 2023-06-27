package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import entity.User;
import entity.Video;
import impl.VideoServiceImpl;
import service.VideoService;

@WebServlet(urlPatterns = {"/admin/video"}, name = "VideoControllerOfAdmin")
public class VideoControllerAdmin extends HttpServlet{
	
	private static final long serialVersionUID = -9187090258976993226L;
	private VideoService videoService = new VideoServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin()== Boolean.TRUE) {
			String action = req.getParameter("action");
			switch(action) {
			case "view":
				doGetOverView(req, resp);
				break;
			case "delete":
				doGetDelete( req, resp);
				break;
			case "add":
				req.setAttribute("isEdit", false);
				doGetAdd( req, resp);
				break;
			case "edit":
				req.setAttribute("isEdit", true);
				doGetEdit( req, resp);
				break;
			}
		} else {
			resp.sendRedirect("index");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin()== Boolean.TRUE) {
			String action = req.getParameter("action");
			switch(action) {
			case "add":
				doPostAdd(req, resp);
				break;
			case "edit":
				doPostEdit( req, resp);
				break;
			}
		} else {
			resp.sendRedirect("index");
		}
		
	}
	
	protected void doGetOverView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> videos = videoService.findAll();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/templates/views/admin/video-overview.jsp").forward(req, resp);
	}
	
	protected void doGetDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String href = req.getParameter("href");
		Video videoDelete = videoService.delete(href);
		if( videoDelete != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
		
		
	}
	
	protected void doGetAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/templates/views/admin/video-edit.jsp").forward(req, resp);
	}
	
	protected void doGetEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String href = req.getParameter("href");
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		
		req.getRequestDispatcher("/templates/views/admin/video-edit.jsp").forward(req, resp);
	}
	
	protected void doPostAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("href");
		String poster = req.getParameter("poster");
		Video video = new Video();
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);
		
		Video videoReturn = videoService.create(video);
		if( videoReturn != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}
	
	protected void doPostEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String href = req.getParameter("href");
		String poster = req.getParameter("poster");
		String hrefOrigin = req.getParameter("hrefOrigin");
		Video video = videoService.findByHref(hrefOrigin);
		video.setTitle(title);
		video.setHref(href);
		video.setDescription(description);
		video.setPoster(poster);
		
		Video videoReturn = videoService.update(video);
		if( videoReturn != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}
}
