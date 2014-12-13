package DB.ryanwork;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.Ent;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

import static com.googlecode.objectify.ObjectifyService.ofy;


public class Search extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7310019600421270738L;
	static {
		ObjectifyService.register(BaseClasses.Ent.class);
	}
	 public void doPost(HttpServletRequest req, HttpServletResponse resp)
             throws IOException {
		 
		 //Objectify objf = ObjectifyService.factory().begin();
		 String category = req.getParameter("category_text");
		 String tag = req.getParameter("tag_text");
		 StringBuilder returnme = new StringBuilder();
		 List<BaseClasses.Ent> ret = null;
		 if( category.equals(""))
		 {
			 if( !(tag.equals("") ))
			 {
				 ret = ofy().load().type(Ent.class).filter("tag",tag).list(); //.first().now();
			 }

		 }
		 else if(tag.equals(""))
		 {
			 ret = ofy().load().type(Ent.class).filter("category",category).list();
		 }
		 else
		 {
			 ret = ofy().load().type(Ent.class).filter("category",category).filter("tag", tag).list();
		 }
		 
		 if(ret==null || ret.size()==0)
		 {
			 returnme.append("Since you didn't specify any query, here's everything:\n\n");
		 }
		 for(Ent e: ret)
		 {
			returnme.append(e.toString()+"<br>") ;
		 }
		 
		 req.getSession().setAttribute("DB_resp", returnme.toString()); 
		 resp.sendRedirect("results.jsp");
		 
	 }
}
