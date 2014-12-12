package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BaseClasses.Pitch;
import BaseClasses.User;

public class BidServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//get pitch
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
		
		//get user
		HttpSession session = req.getSession(false);
		session.getAttribute("user");
		User currentUser = (User)session.getAttribute("user");
		
		//set the bid
		if(!currentPitch.getBidderList().contains(currentUser.getId())&&!currentPitch.getUserList().contains(currentUser.getId())){
			currentPitch.addBidder(currentUser.getId());
		}
		//*******************************************************
		//anything else to add (sessions/instance) IDK how to set?
		//*******************************************************
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
}
