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

public class SignUpServlet extends HttpServlet {
	
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
	
	public static final Logger _log = Logger.getLogger(SignUpServlet.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String email = req.getParameter("email");
		Key emailKey = KeyFactory.createKey("email", email);
		String linkedin = req.getParameter("linkedin-id");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		String confirmationKey = "";

		try{
			String generatedEmail;
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(email.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedEmail = sb.toString();
            confirmationKey = generatedEmail;
		// edit the confirmation link and the notValid link
		
		}catch(Exception e){
			
		}
		Date date = new Date();

		// also check if email exist
		if (emailExists()) {
			resp.sendRedirect("retry.jsp" + "?error=" + "email");
		}

		// non-linekdin login
		// generate normal id
		if (linkedin == null) {
			linkedin = "not valid";
		}

		if (validPass(password1, password2)) {
			// save first time sign up info in the datastore
			User newUser = new User(email, firstName, lastName,  password1, confirmationKey);
			_log.log(Level.WARNING,"Before DBManager");
			DBManager.getInstance().add(newUser);
			ArrayList<String> titles = new ArrayList<String>();
			titles.add("title1");
			ArrayList<String> descriptions = new ArrayList<String>();
			descriptions.add("description1");
			//Pitch pitch = new Pitch("My Project", titles, descriptions, null, confirmationKey, 0, 0);
			//pitch.addUser(newUser.getId());
			//newUser.addPitch(pitch.getId());
			Message msg = new Message("Pitch Admin",newUser.getFirstName(),"","Welcome to Pitch! Have fun.");
			Message note = new Message("Test Pitch",newUser.getFirstName(),"Subject","New video has been added.");
			newUser.addMessage(msg);
			newUser.addNotification(note);
			newUser.addFriend(newUser.getId());
			//DBManager.getInstance().add(pitch);
			_log.log(Level.WARNING,"After");
			// send email for confirmation
			sendConfirmation(firstName, lastName, email, confirmationKey);

			resp.sendRedirect("/signUp.jsp");
		} else {
			resp.sendRedirect("/retry.jsp" + "?error=" + "pass");
		}
	}

	private boolean emailExists() {

		return false;
	}

	private void sendConfirmation(String firstName, String lastName,
			String email, String confirmationKey) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			String confirmLink = "www.pitch461lproject.appspot.com/confirm"
					+ "?email="+ email + "&id=" +  confirmationKey;
			String notValid = "www.pitch461lproject.appspot.com/delete"
					+ "?email="+ email + "&id=" + confirmationKey;
			String strEmailBody = "Hey "
					+ firstName
					+ "!"
					+ "\nBefore we can let you access our cool website, you need to confirm your email first!"
					+ "\nPlease follow the link under to confirm your account information\n"
					+ confirmLink
					+ "\n\nIf you did not sign up with us, please click the link under.\n"
					+ notValid + "\n\nThank you,\nPitch Project Team";
			MimeMessage outMessage = new MimeMessage(session);
			outMessage.setFrom(new InternetAddress(
					"admin@pitch461lproject.appspotmail.com"));
			outMessage.addRecipient(MimeMessage.RecipientType.TO,
					new InternetAddress(email));
			outMessage.setSubject("Pitch Account Confirmation");
			outMessage.setText(strEmailBody);
			Transport.send(outMessage);
		} catch (MessagingException e) {
			_log.info("ERROR: Could not send out Email Results response : "
					+ e.getMessage());
		}
		return;
	}

	private boolean validPass(String password1, String password2) {
		if (password1.equals(password2)&&password1!=null&&password1.length()>=6&&password1.length()<=12) {
			return true;
		} else
			return false;
	}
}