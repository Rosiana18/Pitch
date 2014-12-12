package BaseClasses;

import java.util.ArrayList;

import DB.DBManager;

import com.google.gwt.dev.shell.Messages;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class User extends Ent implements java.io.Serializable{
	@Index String firstName;
	@Index String lastName;
	@Index String password;
	@Index String linkedIn;
	@Index String confirmationKey;
	@Index String picture;
	//@Index String conversation;
	@Index ArrayList<Message> messages;
	@Index ArrayList<Message> notifications;
	@Index ArrayList<String> pitches;
	@Index ArrayList<String> friends;
	@Index ArrayList<Conversation> conversations;
	@Index String email;
	
	User(){}
	
	public User(String email, String _firstName, String _lastName, String _password, String _confirmationKey)
	{
		id = email;
		this.email = email;
		firstName = _firstName;
		lastName = _lastName;
		password = _password;
		confirmationKey = _confirmationKey;
		messages = new ArrayList<Message>();
		messages.add(new Message("Pitch Admin",_firstName,"","Welcome to Pitch! Have fun."));
		notifications = new ArrayList<Message>();
		pitches = new ArrayList<String>();
		friends = new ArrayList<String>();
		conversations = new ArrayList<Conversation>();
	}
	public String getKey(){
		return confirmationKey;
	}
	public void setKey(String val){
		confirmationKey = val;
	}
	public String getImage(){
		return picture;
	}
	public String getPassword(){
		return password;	
				}
	public String getName(){
		return (firstName + " " + lastName);
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public String getEmail(){
		return email;
	}
	public void addMessage(Message message){
		messages.add(message);
	}
	public void addNotification(Message message){
		notifications.add(message);
	}
	public void addPitch(String id){
		if(pitches==null)
		{
			pitches = new ArrayList<String>();
		}
		pitches.add(id);
	}
	public void addFriend(String id){
		friends.add(id);
	}
	public ArrayList<Message> getMessages(){
		return messages;
	}
	public ArrayList<String> getPitches(){
		return pitches;
	}
	public ArrayList<Message> getNotifications(){
		return notifications;
	}
	public ArrayList<String> getFriends(){
		return friends;
	}

	public void setName(String firstName2, String lastName2) {
		this.firstName = firstName2;
		this.lastName = lastName2;
	}

}
