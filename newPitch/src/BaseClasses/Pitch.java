package BaseClasses;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class Pitch extends Ent{
	@Index String title;
	@Index ArrayList<String> users;
	@Index ArrayList<String> bidders;
	@Index String description;
	@Index ArrayList<Message> feedbacks;
	@Index ArrayList<Message> notifications;
	Pitch(){};
	public Pitch(String title, String description) {
		id = title;
		this.title = title;
		this.description = description;
		users = new ArrayList<String>();
		bidders = new ArrayList<String>();
		feedbacks = new ArrayList<Message>();
		notifications = new ArrayList<Message>();
	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return description;
	}
	public void addUser(String user){
		users.add(user);
	}
	public void addBidder(String user){
		bidders.add(user);
	}
	public void addFeedback(Message feedback){
		feedbacks.add(feedback);
	}
	public ArrayList<Message> getFeedbacks(){
		return feedbacks;
	}
	public ArrayList<Message> getNotifications(){
		return notifications;
	}
	public ArrayList<String> getUserList(){
		return users;
	}
	public ArrayList<String> getBidderList(){
		return bidders;
	}
 
}