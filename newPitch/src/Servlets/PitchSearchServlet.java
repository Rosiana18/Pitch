package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.annotation.Index;

import BaseClasses.User;
import DB.DBManager;

public class PitchSearchServlet extends HttpServlet{
	private String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
			,"conceptDesign","event","teaching","cause","diy","art","music"};
	private DBManager dbm = DBManager.getInstance();
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		//get the category tags
		ArrayList<Integer> categoryTags = loadUp(req);
		categoryTags.add(3);
		resp.sendRedirect("/myPitches.jsp");
	}
	private ArrayList<Integer> loadUp(HttpServletRequest req)
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String a : whatIsIts)
		{
			if(req.getParameter(a)==null){
				ret.add(0);
			}else{
				ret.add(Integer.valueOf(req.getParameter(a)));
			}
		}
		
		for(Integer b : ret )
		{
			
		}
		
		return ret;
	}

}
