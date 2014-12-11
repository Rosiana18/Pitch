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

public class LogoutServlet extends HttpServlet {
	
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
	
	public static final Logger _log = Logger.getLogger(LogoutServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
			 HttpSession session = req.getSession(false);
			 session.removeAttribute("user");
			 session.invalidate();
			resp.sendRedirect("login.jsp");	
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
	doPost(req,resp);
	}
	}
	
