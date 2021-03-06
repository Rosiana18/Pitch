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

public class SendMessageServlet extends HttpServlet {
	
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
		//Some comment
	}
	
	public static final Logger _log = Logger.getLogger(SendMessageServlet.class
			.getName());
		
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String msg = req.getParameter("Message");
		String to = req.getParameter("to");
		if(msg.isEmpty()||to.isEmpty()){
			resp.sendRedirect("conversation.jsp?error=missing");
			return;
		}
		HttpSession session = req.getSession(false);
		User user = DBManager.getInstance().getUserByID((String)session.getAttribute("userName"));
		User toUser = (User)DBManager.getInstance().getUserByID(to); 
		Message message = new Message(user.getId(),to,"",msg);
		user.addMessage(message);	
		toUser.addMessage(message);
		DBManager.getInstance().add(toUser);
		DBManager.getInstance().add(user);
		session.removeAttribute("user");
		session.setAttribute("user",user);
		resp.sendRedirect("conversation.jsp?convID="+to);
		}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		doPost(req,resp);
	}
}