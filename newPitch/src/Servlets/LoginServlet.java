package Servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BaseClasses.Ent;
import BaseClasses.Pitch;
import BaseClasses.User;
import BaseClasses.Message;
import DB.DBManager;

import com.googlecode.objectify.ObjectifyService;

public class LoginServlet extends HttpServlet {
	
	static {
		try{
		ObjectifyService.register(Ent.class);
		ObjectifyService.register(User.class);
		ObjectifyService.register(Pitch.class);
		ObjectifyService.register(Message.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static final Logger _log = Logger.getLogger(LoginServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String email = req.getParameter("email");
		if(email.isEmpty()){
			resp.sendRedirect("login.jsp?error=Missing Email");
			return;
		}
		
		String password = req.getParameter("password");
		User user = (User)DBManager.getInstance().getUserByID(email);
		if(user==null){
			resp.sendRedirect("login.jsp?error=Incorrect Info");
			return;
		}
		if(user.getPassword().equals(password)){
			 HttpSession session = req.getSession();
			 session.removeAttribute("user");
			 session.setAttribute("userName", email);
			 session.setAttribute("user", user);
			resp.sendRedirect("index.jsp");
		}
		else{
			resp.sendRedirect("login.jsp?error=Incorrect Info");
		}
	}
}