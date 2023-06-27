package impl;

import javax.servlet.ServletContext;

import entity.User;
import service.EmailService;
import util.SendMailUtil;

public class EmailServiceImpl implements EmailService {
	
	private static final String EMAIL_WELCOME_SUBJECT = "welcome to TT Entertaimaent";
	private static final String EMAIL_FORGOT_PASSWORD = "TT Entertaiment - New password";
	
	@Override
	public void sendEmail(ServletContext context, User recipient, String type) { 
			String host = context.getInitParameter("host");
			String port = context.getInitParameter("port");
			String user = context.getInitParameter("user");
			String pass = context.getInitParameter("pass");
			
			try {
				String content = null;
				String subject = null;
				switch (type) {
				case "welcome":
					subject = EMAIL_WELCOME_SUBJECT;
					content = "Dear" + recipient.getUsername() + ", Hope you have a good time!!";
					break;

				case "forgot":
					subject = EMAIL_FORGOT_PASSWORD;
					content = "Dear" + recipient.getUsername() + ", Your new password here: " + recipient.getPassword() ;
					break;
				default:
					subject = "TT Entertaiment";
					content = "Maybe this email is wrong, don't care about it";
				}
				SendMailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject, content);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
}
