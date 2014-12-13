package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String redirect = "profile.jsp";
		String aboutMe = req.getParameter("aboutMe");
		if(firstName.isEmpty()||lastName.isEmpty()){
			resp.sendRedirect(redirect +" ?error=missing");
			return;
		}
		
		HttpSession session = req.getSession(true);
		User user = DBManager.getInstance().getUserByID((String)session.getAttribute("userName"));
		user.setName(firstName, lastName);
		user.setAbout(aboutMe);
		HashMap<String,Integer> valTagValues = new HashMap<String,Integer>();
		String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
				,"conceptDesign","event","teaching","cause","diy","art","music"};
		ArrayList<Integer> ret = new ArrayList<Integer>();
		int counter=0;
		for(String a : whatIsIts)
		{
			if(req.getParameter(a)==null){
				ret.add(0);
			}else{
				ret.add(Integer.valueOf(req.getParameter(a)));
			}
			valTagValues.put(a, ret.get(counter));
			counter++;
		}
		
		///how do i set the tag???
		user.setCategoryTags(ret);
		redirect += "?user=" + user.getId();
		
		DBManager.getInstance().add(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		resp.sendRedirect(redirect);
		}
}