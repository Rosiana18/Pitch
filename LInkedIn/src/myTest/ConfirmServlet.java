package myTest;

import java.io.IOException;
import java.net.URL;
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
		String msg = req.getParameter("id");
		_log.log(Level.WARNING, msg);
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