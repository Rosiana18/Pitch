package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.Pitch;


public class PitchServlet extends HttpServlet{
	
	/*
	 * DO POST METHOD INFO
	 * SUCCESSFULL:
	 * 		allocation of:
	 * 				Title
	 * 				Description
	 * 				Extra Fields
	 * UNSUCCESSFULL
	 * 		allocation of:
	 * 				Owner - reason: I'm not sure how to obtain owner from the createPitch.jsp
	 * 				Tags - reason: Pitch.java does not have tag array
	 * 		create new Pitch into a user - reason: don't know how to grab user
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
		Scanner scan = new Scanner(number);
		if(scan.hasNextInt()){
			num = scan.nextInt();
		}
		for(int i = 0; i < num; i++){
			String titleAdd = req.getParameter("title"+i);
			String descriptionAdd = req.getParameter("description"+i);
			if((!titleAdd.isEmpty())&&(!descriptionAdd.isEmpty())){
				_title.add(descriptionAdd);
				_description.add(descriptionAdd);
			}
		}
		
		//owner
		String owner = req.getParameter("owner");
		
		//tags
		
		// create the Pitch
		Pitch newPitch = new Pitch(mainTitle, _title, _description, owner);
		
		// how do i allocate this to a user's pitch list or the database itself?
		
		//redirect to myPitch
		resp.sendRedirect("/myPitches.jsp");
	}
}
