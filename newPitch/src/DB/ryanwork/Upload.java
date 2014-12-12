package DB.ryanwork;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import BaseClasses.Ent;
import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.ObjectifyService;

public class Upload extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9035504686093405461L;
	static {
		ObjectifyService.register(BaseClasses.Ent.class);
	}
	 public void doPost(HttpServletRequest req, HttpServletResponse resp)
             throws IOException {
		 /*
		 Ent e1 = new Ent("Ryan", 10L, "User", "Student");
		 Ent e2 = new Ent("Migaa", 1L, "User", "Student");
		 Ent e3 = new Ent("Barack", 2L, "User", "Politics");
		 Ent e4 = new Ent("Einstein", 3L, "User", "Physics");
		 Ent e5 = new Ent("Naveen", 4L, "Super User", "Teaching");
		 Ent e6 = new Ent("Christine", 5L, "Super User", "Teaching");
		 Ent e7 = new Ent("Feynmann", 6L, "User", "Physics");
		 Ent e8 = new Ent("83213", 7L, "Conversation", "Feedback");
		 Ent e9 = new Ent("Laser Project", 8L, "Pitch", "Physics");
		 Ent e10 = new Ent("Pie Barack!", 9L, "Pitch", "Politics");
		 
		 ofy().save().entities(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10).now();
		 */
		 
	 }

}
