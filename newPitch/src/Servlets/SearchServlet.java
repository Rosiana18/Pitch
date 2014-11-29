package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.objectify.annotation.Index;

import BaseClasses.User;
import DB.DBManager;

public class SearchServlet extends HttpServlet{
	private String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
			,"conceptDesign","event","teaching","cause","diy","art","music"};
	private DBManager dbm = DBManager.getInstance();
	
	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		
		String confirmationCode = req.getParameter("");
		String email = req.getParameter("email");
		
	}
	
	private ArrayList<Integer> loadUp(HttpServletRequest req)
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String a : whatIsIts)
		{
			ret.add( (Integer)req.getAttribute(a) );
		}
		
		for(Integer b : ret )
		{
			
		}
		
		return null;
	}

}
