package BaseClasses;

import java.io.Serializable;
import java.util.ArrayList;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class Pitch extends Ent implements Serializable{
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
	private String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
			,"conceptDesign","event","teaching","cause","diy","art","music"};
	Pitch(){};
	public Pitch(String title, ArrayList<String> _descriptionTitles, ArrayList<String> _descriptions, 
			ArrayList<Integer> tags, String _owner, int _duration, int _size) {
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
		
		science = tags.get(0);
		engineering = tags.get(1);
		writing = tags.get(2);
		craft = tags.get(3);
		fixing = tags.get(4);
		visualDesign = tags.get(5);
		conceptDesign = tags.get(6);
		event = tags.get(7);
		teaching = tags.get(8);
		cause = tags.get(9);
		diy = tags.get(10);
		art = tags.get(11);
		music = tags.get(12);

	}
	public String getTitle(){
		return title;
	}
	public String getDescription(){
		return descriptions.get(0);
	}
	public ArrayList<String> getAllDescriptions(){
		return descriptions;
	}
	public ArrayList<String> getAllTitles(){
		return descriptionTitles;
	}
	public void addUser(String user){
		if(users==null){
			users = new ArrayList<String>();
		}
		users.add(user);
	}
	public void addBidder(String user){
		if(bidders==null){
			bidders = new ArrayList<String>();
		}
		bidders.add(user);
	}
	public void addFeedback(Message feedback){
		if(feedbacks==null){
			feedbacks = new ArrayList<Message>();
		}
		feedbacks.add(0,feedback);
	}
	public void addNotification(Message notification){
		if(notifications==null){
			notifications = new ArrayList<Message>();
		}
		notifications.add(notification);
	}
	public ArrayList<Message> getFeedbacks(){
		if(feedbacks==null){
			feedbacks = new ArrayList<Message>();
		}
		return feedbacks;
	}
	public ArrayList<Message> getNotifications(){
		if(notifications==null){
			notifications = new ArrayList<Message>();
		}
		return notifications;
	}
	public ArrayList<String> getUserList(){
		if(users==null){
			users = new ArrayList<String>();
		}
		return users;
	}
	public ArrayList<String> getBidderList(){
		if(bidders==null){
			bidders = new ArrayList<String>();
		}
		return bidders;
	}
	public String getOwnerId()
	{
		return owner;
	}
	public int getDuration(){
		return duration;
	}
	public int getSize(){
		return size;
	}
	public void removeBidder(String user){
		if(bidders!=null){
			bidders.remove(user);				
		}
	}
	public void removeUser(String user){
		if(users!=null){
			users.remove(user);			
		}
	}
	
}
