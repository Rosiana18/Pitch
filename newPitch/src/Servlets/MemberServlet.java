package Servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BaseClasses.Message;
import BaseClasses.Pitch;
import BaseClasses.User;
import DB.DBManager;

public class MemberServlet extends HttpServlet{
	
	/*
	 * 	/delete post
	 * 	delete member
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//get pitch
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
				
		//get current user
		HttpSession session = req.getSession(false);
		User currentUser = DBManager.getInstance().getUserByID((String)session.getAttribute("userName"));
		
		//get member to be removed
		String member = req.getParameter("member");
		User removeUser = (User) DB.DBManager.getInstance().getUserByID(member);
		
		
		//remove from list
		if(currentUser.getId().equals(currentPitch.getOwnerId())){
			removeUser.removePitch(pitch);
			currentPitch.removeUser(member);
			
			//send notification
			Message message = new Message(currentUser.getId(), member, "Member removal", 
					"You have been removed from "+pitch+ " pitch.");
			removeUser.addMessage(message);			
			Message note = new Message(currentUser.getId(), currentUser.getId(), "Member removal",
					"You have removed "+ member + " from " +pitch+ " pitch.");
			currentUser.addMessage(note);
		}

		//******************set notification?**********************
		DBManager.getInstance().add(removeUser);
		DBManager.getInstance().add(currentUser);
		DBManager.getInstance().add(currentPitch);
		session.setAttribute("user", currentUser);
	
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
}
