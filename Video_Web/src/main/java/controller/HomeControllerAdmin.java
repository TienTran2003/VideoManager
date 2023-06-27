package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import constant.SessionAttr;
import dto.UserDto;
import dto.VideoLikedinfo;
import entity.User;
import entity.Video;
import impl.StatsServiceImpl;
import impl.UserServiceImpl;
import service.StatsService;
import service.UserService;

@WebServlet(urlPatterns = {"/admin","/admin/favorites","/admin/user"}, name = "HomeConTrollerOfAdmin")
public class HomeControllerAdmin extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3722286214632700903L;
	private StatsService statsService = new StatsServiceImpl();
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser != null && currentUser.getIsAdmin() == Boolean.TRUE) {
			
			String path = req.getServletPath();
			switch(path) {
				case "/admin":
					doGetHome( req, resp);
					break;
				case "/admin/favorites":
					doGetFavorites( req, resp);
					break;
				case "/admin/user":
					doGetHomeUser( req, resp);
					break;
				case "/admin/delete":
					doDeleteuser(req, resp);
					break;
			}
			
		} else {
			resp.sendRedirect("index");
		}
		
		
	}
	protected void doGetHome(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<VideoLikedinfo> videos = statsService.findVideoLikedInfo();
		req.setAttribute("videos", videos);
		req.getRequestDispatcher("/templates/views/admin/home.jsp").forward(req, resp);
	}
	
	protected void doGetHomeUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> user = userService.findAll();
		req.setAttribute("user", user);
		req.getRequestDispatcher("/templates/views/admin/homeuser.jsp").forward(req, resp);
	}
	
	protected void doDeleteuser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String username = req.getParameter("username");
		User userDelete = userService.delete(username);
		if( userDelete != null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
		
	}
	
	protected void doGetFavorites(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String videoHref = req.getParameter("href");
		List<UserDto> users = userService.findUsersLikedByVideoHref(videoHref);
		if(users.isEmpty()) {
			resp.setStatus(400);
		}else {
			ObjectMapper mapper = new ObjectMapper();
			String dataRepose = mapper.writeValueAsString(users);
			resp.setStatus(200);
			out.print(dataRepose);
			out.flush();
		}
	}
	
}
