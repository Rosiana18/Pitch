package Servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BaseClasses.User;
import DB.DBManager;

public class ConfirmServlet extends HttpServlet {
	public static final Logger _log = Logger.getLogger(ConfirmServlet.class
			.getName());

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String confirmationCode = req.getParameter("id");
		String email = req.getParameter("email");
		User user = (User)DBManager.getInstance().getById(email);
		if(user.getKey().equals(confirmationCode)){
			user.setKey("0");
			DBManager.getInstance().add(user);
			resp.sendRedirect("/confirm.jsp");
		}
		else{
			resp.sendRedirect("/error.jsp?msg=" + "Confirmation Error!");
		}
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