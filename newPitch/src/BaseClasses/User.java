package BaseClasses;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class User extends Ent implements java.io.Serializable{
	@Index String firstName;
	@Index String lastName;
	@Index String password;
	@Index String linkedIn;
	@Index String confirmationKey;
	@Index ArrayList<Message> messages;
	@Index ArrayList<Message> notifications;
	@Index ArrayList<String> pitches;
	@Index ArrayList<String> friends;
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
		notifications = new ArrayList<Message>();
		pitches = new ArrayList<String>();
		friends = new ArrayList<String>();
	}
	public String getKey(){
		return confirmationKey;
	}
	public void setKey(String val){
		confirmationKey = val;
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
}
