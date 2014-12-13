package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
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
		String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
				,"conceptDesign","event","teaching","cause","diy","art","music"};
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String a : whatIsIts)
		{
			if(req.getParameter(a)==null){
				ret.add(0);
			}else{
				ret.add(Integer.valueOf(req.getParameter(a)));
			}
		}
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String mainDescription = req.getParameter("description");
		String redirect = "profile.jsp";
		if(firstName.isEmpty()||lastName.isEmpty()||mainDescription.isEmpty()){
			redirect += "?error=missing";
		}
		System.out.println(ret.size());
		HttpSession session = req.getSession(true);
		User user = DBManager.getInstance().getUserByID((String)session.getAttribute("userName"));
		user.setName(firstName, lastName);
		user.setDescription(mainDescription);
		for(Integer b: ret){
			System.out.println(b);
		}
		
		DBManager.getInstance().add(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		resp.sendRedirect(redirect);
		}
}