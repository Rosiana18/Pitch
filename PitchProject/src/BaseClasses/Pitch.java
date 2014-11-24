package BaseClasses;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class Pitch extends Ent{
	@Index String title;
	@Index ArrayList<String> users;
	@Index ArrayList<String> bidders;
	@Index String description;
	Pitch(){};
	public Pitch(String title, String description) {
		id = title;
		this.title = title;
		this.description = description;
		users = new ArrayList<String>();
		bidders = new ArrayList<String>();
	}
	public String getTitle(){
		return title;
	}
	
	public void addUser(String user){
		users.add(user);
	}
	public void addBidder(String user){
		bidders.add(user);
	}
	public ArrayList<String> getUserList(){
		return users;
	}
	public ArrayList<String> getBidderList(){
		return bidders;
	}
	public String getDescription(){
		return description;
	} 
}
