package BaseClasses;

import java.util.ArrayList;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class Conversation extends Ent implements java.io.Serializable{
	@Index String entity1;
	@Index String entity2;
	@Index ArrayList<Message> messages;
	Conversation(){}
	
	public Conversation(String entity1, String entity2)
	{
	this.entity1 = entity1;
	this.entity2 = entity2;
	messages = new ArrayList<Message>();
	}
	
	public String getOtherEntity(String entity){
		if(entity == entity1) return entity2;
		else if(entity2 == entity) return entity1;
		return "ERROR";
	}
	public ArrayList<Message> getAllMessages(){
		return messages;
	}
	public ArrayList<Message> getUnreadMessages(){
		ArrayList<Message> unread = new ArrayList<Message>();
		for(Message msg: messages){
			if(msg.unread()){
				unread.add(msg);
			}
		}
		return unread;
	}
}
