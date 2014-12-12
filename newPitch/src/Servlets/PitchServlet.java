package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BaseClasses.Pitch;
import BaseClasses.User;
import DB.DBManager;


public class PitchServlet extends HttpServlet{
	private String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
			,"conceptDesign","event","teaching","cause","diy","art","music"};
	
	/*
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
		String owner =((BaseClasses.User) session.getAttribute("user")).getEmail();
		
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
		
		// how do i allocate this to a user's pitch list or the database itself?
		DBManager.getInstance().add(newPitch);
		User theUser = (BaseClasses.User) session.getAttribute("user");
		User anotherUser = (BaseClasses.User) DBManager.getInstance().getUserByID(theUser.getId());
		
		theUser.addPitch(newPitch.getId());
		DBManager.getInstance().add(theUser);
		
		//redirect to myPitch
		resp.sendRedirect("/mypitches.jsp");
	}
}
