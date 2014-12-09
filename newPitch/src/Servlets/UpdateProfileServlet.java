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

import com.google.gwt.dev.ModuleTabPanel.Session;
import com.googlecode.objectify.ObjectifyService;

public class UpdateProfileServlet extends HttpServlet {
	
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
	
	public static final Logger _log = Logger.getLogger(UpdateProfileServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		HttpSession session = req.getSession(true);
		session.getAttribute("user");
		User user = (User)DBManager.getInstance().getById(( (User)session.getAttribute("user")).getId());
		System.out.println(user.getName());
		user.setName(firstName, lastName);
		System.out.println(user.getName());
		DBManager.getInstance().add(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		resp.sendRedirect("profile.jsp");
		}
}