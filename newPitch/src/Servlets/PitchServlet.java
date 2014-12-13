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
import DB.DBManager;


public class PitchServlet extends HttpServlet{
	private String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
			,"conceptDesign","event","teaching","cause","diy","art","music"};
	
	/*
	 * Create PItch
	 * DO POST METHOD INFO
	 * SUCCESSFULL:
	 * 		allocation of:
	 * 				Title
	 * 				Description
	 * 				Extra Fields
	 * 				Owner
	 * 				Tags - used ryan's code from PitchSearchServlet.java
	 * UNSUCCESSFULL
	 * 		put the new pitch in the database - reason: don't know how the database work?
	 * 				would putting it under user automatically put it in the database
	 * 				Again this is related to how you implement the database. Ryan fix this for me
	 * -Rosie		
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ArrayList<String> _title = new ArrayList<String>();
		ArrayList<String> _description = new ArrayList<String>();
		
		// title
		String mainTitle = req.getParameter("title");
		
		// initial field of the description and title field
		String mainDescription = req.getParameter("description");
		if(mainTitle.isEmpty()||mainDescription.isEmpty()){
			resp.sendRedirect("createPitch.jsp?error=Missing Content");
			return;
		}
		_title.add("Main Description");
		_description.add(mainDescription);
		
		// extra fields
		String number = req.getParameter("number");
		int num = 0;
		if(number != null){
			num = (int) Integer.valueOf(number);
			for(int i = 0; i < num; i++){
				int c = i+1;
				String titleAdd = req.getParameter("title"+c);
				String descriptionAdd = req.getParameter("description"+c);
				if((!titleAdd.isEmpty())&&(!descriptionAdd.isEmpty())){
					_title.add(titleAdd);
					_description.add(descriptionAdd);
				}
			}	
		}
		
		//owner
		HttpSession session =req.getSession(false);
		String owner = DBManager.getInstance().getUserByID((String)session.getAttribute("userName")).getEmail();
		
		//tags
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String a : whatIsIts)
		{
			if(req.getParameter(a)==null){
				ret.add(0);
			}else{
				ret.add(Integer.valueOf(req.getParameter(a)));
			}
		}
		
		//duration and size
		int duration = Integer.valueOf(req.getParameter("length"));
		int size = Integer.valueOf(req.getParameter("size"));
		
		// create the Pitch
		Pitch newPitch = new Pitch(mainTitle, _title, _description, ret, owner, duration, size);
		DBManager.getInstance().add(newPitch);
		
		// set user
		User UserOnSession = DBManager.getInstance().getUserByID((String)session.getAttribute("userName"));
		User UserOnDB = (BaseClasses.User) DBManager.getInstance().getUserByID(UserOnSession.getId());
		UserOnSession.addPitch(newPitch.getId());
		
		DBManager.getInstance().add(UserOnDB);
		session.setAttribute("user", UserOnDB);
		
		//redirect to myPitch
		resp.sendRedirect("/mypitches.jsp");
	}
	// update the pitch
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		ArrayList<String> _title = new ArrayList<String>();
		ArrayList<String> _description = new ArrayList<String>();
		String pitch = req.getParameter("pitch");
		Pitch currentPitch = DBManager.getInstance().getPitchByID(pitch);
		// title
		String mainTitle = req.getParameter("title");
		
		// initial field of the description and title field
		String mainDescription = req.getParameter("description");
		if(mainTitle.isEmpty()||mainDescription.isEmpty()){
			resp.sendRedirect("createPitch.jsp?error=Missing Content");
			return;
		}
		_title.add("Main Description");
		_description.add(mainDescription);
		
		//previous fields
		int prevNum = currentPitch.getAllDescriptions().size();
		for(int i = 0; i < prevNum; i++){
			int c = i+1;
			String titleAdd = req.getParameter("prevTitle"+c);
			String descriptionAdd = req.getParameter("prevDescription"+c);
			if((!titleAdd.isEmpty())&&(!descriptionAdd.isEmpty())){
				_title.add(titleAdd);
				_description.add(descriptionAdd);
			}
		}
		// extra fields
		String number = req.getParameter("number");
		int num = 0;
		if(number != null){
			num = (int) Integer.valueOf(number);
			for(int i = 0; i < num; i++){
				int c = i+1;
				String titleAdd = req.getParameter("title"+c);
				String descriptionAdd = req.getParameter("description"+c);
				if((!titleAdd.isEmpty())&&(!descriptionAdd.isEmpty())){
					_title.add(titleAdd);
					_description.add(descriptionAdd);
				}
			}	
		}
		
		//tags
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String a : whatIsIts)
		{
			if(req.getParameter(a)==null){
				ret.add(0);
			}else{
				ret.add(Integer.valueOf(req.getParameter(a)));
			}
		}
		
		//duration and size
		int duration = Integer.valueOf(req.getParameter("length"));
		int size = Integer.valueOf(req.getParameter("size"));
		
		//owner
		HttpSession session =req.getSession(false);
		User ownerUser = DBManager.getInstance().getUserByID(currentPitch.getOwnerId());
		Message message = new Message((String)session.getAttribute("userName"), ownerUser.getEmail(),
				"Update on " + pitch + " pitch.", "Your " + pitch + " pitch has been updated.");
		DBManager.getInstance().add(ownerUser);
		
		// create the Pitch
		Pitch newPitch = new Pitch(mainTitle, _title, _description, ret, ownerUser.getEmail(), duration, size);
		DBManager.getInstance().add(newPitch);
		
		// set user
		for(String specificUser: currentPitch.getUserList()){
			User otherUser = (BaseClasses.User) DBManager.getInstance().getUserByID(specificUser);
			message = new Message((String)session.getAttribute("userName"), otherUser.getEmail(),
					"Update on " + pitch + " pitch.", "Your " + pitch + " pitch has been updated.");
			
			otherUser.addNotification(message);
			DBManager.getInstance().add(otherUser);
		}
		DBManager.getInstance().add(ownerUser);
		
		//redirect to myPitch
		resp.sendRedirect("/mypitches.jsp");
	}
}
