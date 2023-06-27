package controller;

import java.io.IOException;
import java.sql.SQLOutput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import constant.SessionAttr;
import entity.User;
import impl.EmailServiceImpl;
import impl.UserServiceImpl;
import service.EmailService;
import service.UserService;

@WebServlet(urlPatterns = {"/login", "/logout", "/register", "/forgotPass", "/changePass"})
public class UserController extends HttpServlet{
	private static final long serialVersionUID = -6463179069092857254L;
	
	private UserService userService = new UserServiceImpl();
	private EmailService emailService = new EmailServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch(path) {
			case "/login":
				doGetLogin(req, resp);
				break;
			case "/register":
				doGetRegister(req, resp);
				break;
			case "/logout":
				doGetLogout(session,req, resp);
				break;
			case "/forgotPass":
				doGetForgotPass(req, resp);
				break;

		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String path = req.getServletPath();
		switch(path) {
			case "/login":
				doPostLogin(session,req, resp);
				break;
			case "/register":
				doPostRegister(session,req, resp);
				break;
			case "/forgotPass":
				doPostForgotPass(req, resp);
				break;
			case "/changePass":
				doPostChangePass(session, req, resp);
				break;
		
		}
	}
	
	private void doGetLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/templates/views/user/login.jsp").forward(req, resp);
	}
	
	private void doGetRegister(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/templates/views/user/register.jsp").forward(req, resp);
	}
	
	private void doGetLogout(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/templates/views/user/profile.jsp").forward(req, resp);
	}
	
	private void doGetForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/templates/views/user/forgot-pass.jsp").forward(req, resp);
	}
	

	
	private void doPostLogin(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = userService.login(username, password);
		System.out.println(username);
		System.out.println(password);
		if(user != null) {
			
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		}else {
			resp.sendRedirect("login");
		}
	}
	
	private void doPostRegister(HttpSession session,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		User user = userService.create(username, password, email);
		if(user != null) {
			emailService.sendEmail(getServletContext(), user, "welcome");
			session.setAttribute(SessionAttr.CURRENT_USER, user);
			resp.sendRedirect("index");
		}else {
			resp.sendRedirect("register");
		}
	}
	
	private void doPostForgotPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String email = req.getParameter("email");
		User userWithPass = userService.resetPassword(email);
		if(userWithPass != null) {
			emailService.sendEmail(getServletContext(), userWithPass, "forgot");
			resp.setStatus(204);
		} else {
			resp.setStatus(400);
		}
	}
	
	private void doPostChangePass(HttpSession session ,HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		String currentPass = req.getParameter("currentPass");
		String newPass = req.getParameter("newPass");
		User currentUser = (User) session.getAttribute(SessionAttr.CURRENT_USER);
		if(currentUser.getPassword().equals(currentPass)) {
			currentUser.setPassword(newPass);
			User updateUser = userService.update(currentUser);
			if(updateUser != null) {
				session.setAttribute(SessionAttr.CURRENT_USER, updateUser);
				resp.setStatus(204);
			} else {
				resp.setStatus(400);
			}
		} else {
			resp.setStatus(400);
		}
	}
	
}
