package Servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BaseClasses.Message;
import BaseClasses.Pitch;
import BaseClasses.User;

public class MemberServlet extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//get pitch
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
				
		//get current user
		HttpSession session = req.getSession(false);
		session.getAttribute("user");
		User currentUser = (User)session.getAttribute("user");
		
		//get member to be removed
		String member = req.getParameter("member");
		User removeUser = (User) DB.DBManager.getInstance().getUserByID(member);
		
		//send notification
		Message message = new Message(currentUser.getId(), member, "Member removal", 
				"You have been removed from "+pitch+ " pitch.");
		removeUser.addNotification(message);
		Message note = new Message(currentUser.getId(), currentUser.getId(), "Member removal",
				"You have removed "+ member + " from " +pitch+ " pitch.");
		currentUser.addNotification(note);
		
		//**************************set notification?**********************
		
		//remove from list
		if(currentUser.getId().equals(currentPitch.getOwnerId())){
			currentPitch.removeUser(member);
		}
		//**************needs to be updated in the database*****************
	
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
}
