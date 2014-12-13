package Servlets;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.Ent;
import BaseClasses.Message;
import BaseClasses.Pitch;
import BaseClasses.User;
import DB.DBManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.googlecode.objectify.ObjectifyService;

@WebServlet("/invitefriend")
public class InviteFriendServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String email = req.getParameter("email");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			String confirmLink = "www.pitch461lproject.appspot.com/addfriend"
					+ "?email="+ email ;
			String strEmailBody = "Hey there!"
					+ "\nYour friend would like you to invite you to checkout our cool website Pitch. Please sign up by clicking on the link below to discover the cool services we offer!"
                    + "\nhttp://pitch461lproject.appspot.com/"
                    + "\nHappy Pitching!"	;
					
			MimeMessage outMessage = new MimeMessage(session);
			outMessage.setFrom(new InternetAddress(
					"admin@pitch461lproject.appspotmail.com"));
			outMessage.addRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress(email));
			outMessage.setSubject("Try out Pitch!");
			outMessage.setText(strEmailBody);
			Transport.send(outMessage);
		} catch (MessagingException e) {
			
		 e.getMessage();
		}
		  
	}

	

	
}