package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.SessionAttr;
import entity.History;
import entity.User;
import entity.Video;
import impl.HistoryServiceImpl;
import impl.VideoServiceImpl;
import service.HistoryService;
import service.VideoService;

@WebServlet(urlPatterns = "/video")
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = -5438588218103765742L;
	
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionParam = req.getParameter("action");
		String href = req.getParameter("id");
		HttpSession session = req.getSession();
		
		switch (actionParam) {
			case "watch":
				doGetWatch(session, href, req, resp);
				break;
			case "like":
				doGetLike(session, href, req, resp);
				break;
		}
	}
	private void doGetWatch(HttpSession session, String href,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Video video = videoService.findByHref(href);
		req.setAttribute("video", video);
		
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		
		if(currentUser != null) {
			History history = historyService.create(currentUser, video);
			req.setAttribute("flagLikedBtn", history.getIsLiked());
		}
		
		req.getRequestDispatcher("/templates/views/user/video-detail.jsp").forward(req, resp);
	}
	
	private void doGetLike(HttpSession session, String href,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		boolean result = historyService.updateLikeOrUnlike(currentUser, href);
		if(result==true) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
//		req.getRequestDispatcher("/templates/views/user/video-detail.jsp").forward(req, resp);
	}

}
