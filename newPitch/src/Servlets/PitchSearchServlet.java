package Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.mail.Session;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gwt.dev.cfg.Properties;
import com.googlecode.objectify.annotation.Index;
import BaseClasses.Ent;
import BaseClasses.User;

import DB.DBManager;

public class PitchSearchServlet extends HttpServlet{
	private String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
			,"conceptDesign","event","teaching","cause","diy","art","music"};
	private DBManager dbm = DBManager.getInstance();
	
	/*	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		ArrayList<Integer> ckboxes = loadUp(req);
		//ArrayList<String> st = new ArrayList<String>();
		for(String s : whatIsIts)
		{
			st.add(s);
		}
		dbm.filterBy(ckboxes,st); 
		
	}*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		//get the category tags
		ArrayList<Integer> categoryTags = loadUp(req);
		ArrayList<String> strList = new ArrayList();
		for(String z : whatIsIts)
		{
			strList.add(z);
		}
		String kw = req.getParameter("keyword");
		String siz = req.getParameter("size");
		String len = req.getParameter("length");
		String u = req.getParameter("user");
		int size = Integer.valueOf(siz)/8;
		int length = Integer.valueOf(len)/8;
		ArrayList<Ent> results = (ArrayList<Ent>) dbm.filterBy(categoryTags, strList, null, 0 ,0 , kw.split(" "));
		req.getSession().setAttribute("searchResults", results);
		resp.sendRedirect("/PitchSearchResults.jsp");
	}
	private ArrayList<Integer> loadUp(HttpServletRequest req)
	{
		String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
				,"conceptDesign","event","teaching","cause","diy","art","music"};
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String a : whatIsIts)
		{
			if(req.getParameter(a)==null){
				ret.add(0);
			}else{
				ret.add(Integer.valueOf(req.getParameter(a)));
			}
		}
		
		
		return ret;
	}

}
