package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import myTest.services.PMF;import BaseClasses.Ent;
import BaseClasses.Ent;
import BaseClasses.User;
import DB.DBManager;

public class ConfirmServlet extends HttpServlet {
	public static final Logger _log = Logger.getLogger(ConfirmServlet.class
			.getName());

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String confirmationCode = req.getParameter("id");
		String email = req.getParameter("email");
		_log.log(Level.WARNING, email);
		_log.log(Level.WARNING, confirmationCode);
		//PersistenceManager pm = PMF.get().getPersistenceManager();
		List<User> list = (List<User>)DBManager.getInstance().filterBy(email);
		_log.log(Level.WARNING,list.get(0).getKey());
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		processRequest(req,resp);
		
		}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		processRequest(req,resp);
		
		}
}