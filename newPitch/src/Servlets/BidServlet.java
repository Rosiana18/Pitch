package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BaseClasses.Message;
import BaseClasses.Pitch;
import BaseClasses.User;

public class BidServlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//get pitch
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
		
		//get current user
		HttpSession session = req.getSession(false);
		session.getAttribute("user");
		User currentUser = (User)session.getAttribute("user");
		
		//type of request
		String button = req.getParameter("button");
		if(button.equals("add")){
			
			//becomes a bidder if you're not already a bidder/ a member/ the owner
			if(!currentUser.getId().equals(currentPitch.getOwnerId())
					&&!currentPitch.getBidderList().contains(currentUser.getId())
					&&!currentPitch.getUserList().contains(currentUser.getId())){
				currentPitch.addBidder(currentUser.getId());
				//notify user
				Message note = new Message(currentUser.getEmail(), currentUser.getEmail(), "Your bid",
						"You bid "+pitch+ " pitch.");
				currentUser.addNotification(note);
				//*************************set notification!!!****************************
			}
			//*******************************************************
			//anything else to add (sessions/instance) IDK how to set/update?
			//*******************************************************
		}else if(button.equals("remove")){
			if(currentPitch.getBidderList().contains(currentUser.getEmail())){
				currentPitch.removeBidder(currentUser.getEmail());
				//notify user
				Message note = new Message(currentUser.getEmail(), currentUser.getEmail(), "Your bid",
						"You unbid "+pitch+ " pitch.");
				currentUser.addNotification(note);
				//*************************set notification!!!****************************
			}
			//*******************************************************
			//anything else to add (sessions/instance) IDK how to set/update?
			//*******************************************************
		}
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//get pitch
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
		
		//get current user
		HttpSession session = req.getSession(false);
		session.getAttribute("user");
		User currentUser = (User)session.getAttribute("user");
		
		//get bidder
		String bidder = req.getParameter("bidder");
		User acceptBidder = (User) DB.DBManager.getInstance().getUserByID(bidder);
		
		//add bidder as a user if you're a member or if you're the owner
		if(currentPitch.getUserList().contains(currentUser.getId())||currentPitch.getOwnerId().equals(currentUser.getId())){
			// only let non member joins the pitch
			if(!currentPitch.getUserList().contains(bidder)&&(!currentPitch.getOwnerId().equals(bidder))){
				currentPitch.addUser(bidder);
				currentPitch.removeBidder(bidder);
				
				//send notification
				Message message = new Message(currentUser.getEmail(), bidder, "Member acceptance", 
						currentUser.getName() + " have accepted you as a member of "+pitch+ " pitch.");
				acceptBidder.addNotification(message);
				Message note = new Message(currentUser.getEmail(), currentUser.getEmail(), "Member acceptance",
						"You have accepted "+ bidder + " as a member of " +pitch+ " pitch.");
				currentUser.addNotification(note);
				//*************************set notification!!!****************************
			}
		}
		//*******************************************************
		//anything else to add (sessions/instance) IDK how to set/update?
		//*******************************************************
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
}
