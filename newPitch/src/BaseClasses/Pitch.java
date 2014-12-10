package BaseClasses;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class Pitch extends Ent{
	@Index String title;
	@Index ArrayList<String> users;
	@Index ArrayList<String> bidders;
	ArrayList<String> descriptionTitles;
	ArrayList<String> descriptions;
	@Index ArrayList<Message> feedbacks;
	@Index ArrayList<Message> notifications;
	@Index int duration;
	@Index int size;
	String owner;
	Pitch(){};
	public Pitch(String title, ArrayList<String> _descriptionTitles, ArrayList<String> _descriptions,
			String _owner, int _duration, int _size) {
		id = title;
		this.title = title;
		this.descriptions = _descriptions;
		descriptionTitles = _descriptionTitles;
		users = new ArrayList<String>();
		bidders = new ArrayList<String>();
		feedbacks = new ArrayList<Message>();
		notifications = new ArrayList<Message>();
		owner = _owner;
		duration = _duration;
		size = _size;
	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return descriptions.get(0);
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
	public String getOwnerId()
	{
		return owner;
	}
 
}
