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

public class FeedbackServlet extends HttpServlet{
	/*
	 * 	/feed post
	 * 	add post
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
		String subject = req.getParameter("subject");
		String body = req.getParameter("body");
		
		// get user
		HttpSession session = req.getSession(false);
		session.getAttribute("user");
		User currentUser = (User)session.getAttribute("user");
		
		if(!subject.isEmpty() && !body.isEmpty()){
			//create new feedback
			Message feedback = new Message(currentUser.getId(),null, subject ,body);
			currentPitch.addFeedback(feedback);
			
			// *********************needs to be set********************
			DBManager.getInstance().add(currentPitch);
			session.setAttribute(currentPitch.getId(),currentPitch);
			
			//create new notification
			String message = currentUser.getId() + " left a \"" + subject + "\" on " + currentPitch.getTitle() + ".";
			for(String user: ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch)).getUserList()){
				Message notification = new Message(currentUser.getId(), user , "New Feedback", message);
				User otherUser = (BaseClasses.User)DB.DBManager.getInstance().getUserByID(user);
				otherUser.addNotification(notification);
				
				// **************needs to be added and set******************
				DBManager.getInstance().add(otherUser);
			}
		}
		// redirect or refresh the page i guess?
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
}
