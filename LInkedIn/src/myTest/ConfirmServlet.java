package myTest;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import myTest.services.PMF;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ConfirmServlet extends HttpServlet {
	public static final Logger _log = Logger.getLogger(ConfirmServlet.class
			.getName());

	private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String confirmationCode = req.getParameter("key");
		String email = req.getParameter("email");
		_log.log(Level.WARNING, email);
		_log.log(Level.WARNING, confirmationCode);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key key;
		Entity result;
		try{
			key = KeyFactory.createKey("email", email);
			result = pm.getObjectById(Entity.class, key);
			_log.log(Level.WARNING, (String)result.getProperty("confirmationKey"));
			_log.log(Level.WARNING, "TRY");
		}
		catch(Exception e){
			_log.log(Level.WARNING, "EXCEPTION");
		}
		finally{
			pm.close();
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