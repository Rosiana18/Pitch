package myTest;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Properties;
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

public class SignUpServlet extends HttpServlet {
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
			Entity userInfo = new Entity("UserInfo", emailKey);
			userInfo.setProperty("linkedIn", linkedin);
			userInfo.setProperty("date", date);
			userInfo.setProperty("firstname", firstName);
			userInfo.setProperty("lastname", lastName);
			userInfo.setProperty("password", password1);
			userInfo.setProperty("email", email);
			userInfo.setProperty("confirmationKey",confirmationKey);
			DatastoreService datastore = DatastoreServiceFactory
					.getDatastoreService();
			datastore.put(userInfo);

			// send email for confirmation
			sendConfirmation(firstName, lastName, email, confirmationKey);

			resp.sendRedirect("/signUp.jsp" + "?name=" + firstName + lastName);
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