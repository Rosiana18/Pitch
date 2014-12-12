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

public class BidServlet extends HttpServlet {
	/*
	 * 	/bid post
	 *
	 *	update into pitch
	 *	remove into unbid
	 * 	add	into bid list
	*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//get pitch
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = ((BaseClasses.Pitch)DB.DBManager.getInstance().getPitchByID(pitch));
		
		//get selected member
		String member = req.getParameter("member");
		
		//get current user
		HttpSession session = req.getSession(false);
		session.getAttribute("user");
		User currentUser = (User)session.getAttribute("user");
		
		//type of request
		String button = req.getParameter("button");
		
		// add yourself to bid list
		if(button.equals("add")){	
			//becomes a bidder if you're not already a bidder/ a member/ the owner
			if(!currentUser.getId().equals(currentPitch.getOwnerId())
					&&!currentPitch.getBidderList().contains(currentUser.getId())
					&&!currentPitch.getUserList().contains(currentUser.getId())){
				//notify user
				Message note = new Message(currentUser.getEmail(), currentUser.getEmail(), "Your bid",
						"You bid "+pitch+ " pitch.");
				currentUser.addNotification(note);
				currentPitch.addBidder(currentUser.getId());
				
				//*************************set notification!!!****************************
				DBManager.getInstance().add(currentUser);
				session.setAttribute("user", currentUser);
			}			
		// remove yourself from the bid list
		}else if(button.equals("remove")){
			if(currentPitch.getBidderList().contains(currentUser.getEmail())){
				//notify user
				Message note = new Message(currentUser.getEmail(), currentUser.getEmail(), "Your bid",
						"You unbid "+pitch+ " pitch.");
				currentUser.addNotification(note);
				currentPitch.removeBidder(currentUser.getEmail());
				
				//*************************set notification!!!****************************
				DBManager.getInstance().add(currentPitch);
				DBManager.getInstance().add(currentUser);
				session.setAttribute(currentPitch.getId(), currentPitch);
				session.setAttribute("user",currentUser);
			}
		// update the pitch page - redirection
		}else if(button.equals("update")){
			resp.sendRedirect("updatePitch.jsp?pitch="+pitch+"?userId="+currentUser.getId());
		}
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
	
	/*
	 * 	/bid get
	 *	add bidder into user List
	 */
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
				acceptBidder.addPitch(pitch);
				
				Message note = new Message(currentUser.getEmail(), currentUser.getEmail(), "Member acceptance",
						"You have accepted "+ bidder + " as a member of " +pitch+ " pitch.");
				
				currentUser.addNotification(note);
				currentUser.addPitch(pitch);
				
				//*************************set notification!!!****************************
				DBManager.getInstance().add(currentUser);
				DBManager.getInstance().add(acceptBidder);
				DBManager.getInstance().add(currentPitch);
				session.setAttribute("user",currentUser);
				session.setAttribute(currentPitch.getId(), currentPitch);
			}
		}
		resp.sendRedirect("pitch.jsp?pitch="+pitch);
	}
}
