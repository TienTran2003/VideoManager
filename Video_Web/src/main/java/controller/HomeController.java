package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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



@WebServlet(urlPatterns = {"/index", "/favorites", "/history"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 4634043340786329391L;
	private static final int VIDEO_MAX_PAGE_SIZE = 8;
	private VideoService videoService = new VideoServiceImpl();
	private HistoryService historyService = new HistoryServiceImpl();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch(path) {
			case "/index":
				doGetIndex( req, resp);
				break;
			case "/favorites":
				doGetFavorite(session, req, resp);
				break;
			case "/history":
				doGetHistory(session, req, resp);
				break;
		}
		
	}
	protected void doGetIndex(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Video> countVideo = videoService.findAll();
		int maxPage = (int) Math.ceil(countVideo.size() / (double) (VIDEO_MAX_PAGE_SIZE));
		req.setAttribute("maxPage", maxPage);
		String pageNumber = req.getParameter("page");
		List<Video> videos;
		if(pageNumber==null || Integer.valueOf(maxPage) > maxPage) {
			videos = videoService.findAll(1, VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", 1);
		}else {
			videos = videoService.findAll(Integer.valueOf(pageNumber), VIDEO_MAX_PAGE_SIZE);
			req.setAttribute("currentPage", Integer.valueOf(pageNumber));
		}
		
		
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/templates/views/user/index.jsp").forward(req, resp);
	}
	protected void doGetFavorite(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> histories = historyService.findByUserAndIsLike(user.getUsername());
		List<Video> videos = new ArrayList<Video>();
		histories.forEach(item -> videos.add(item.getVideo()));
		
		/*
		 	for(int i = 0; i<histories.size(); i++){
		 		videos.add(histories.get(i).get(video));
		 	}
		*/
		
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/templates/views/user/favorite.jsp").forward(req, resp);
	}
	
	protected void doGetHistory(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		List<History> histories = historyService.findByUser(user.getUsername());
		List<Video> videos = videoService.findAll();
		histories.forEach(item -> videos.add(item.getVideo()));
		
		/*
		 	for(int i = 0; i<histories.size(); i++){
		 		videos.add(histories.get(i).get(video));
		 	}
		*/
		
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/templates/views/user/history.jsp").forward(req, resp);
	}
}
