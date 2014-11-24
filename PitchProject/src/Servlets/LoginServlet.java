package Servlets;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import BaseClasses.Ent;
import BaseClasses.Pitch;
import BaseClasses.User;
import DB.DBManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;

public class LoginServlet extends HttpServlet {
	
	static {
		try{
		ObjectifyService.register(Ent.class);
		ObjectifyService.register(User.class);
		ObjectifyService.register(Pitch.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static final Logger _log = Logger.getLogger(LoginServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		User user = (User)DBManager.getInstance().getById(email);
		if(user.getPassword().equals(password)){
			 HttpSession session = req.getSession();
			 session.setAttribute("userName", email);
			 session.setAttribute("user", user);
			resp.sendRedirect("index.jsp");
			
		}
		else{
		}
	}
}