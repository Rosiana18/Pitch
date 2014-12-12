package BaseClasses;

import java.util.Date;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class Message extends Ent implements java.io.Serializable{
	
	@Index String from;
	@Index String to;
	@Index String subject;
	@Index String body;
	@Index Date date;
	@Index boolean unread;
	Message(){}
	
	public Message(String from, String to, String subject, String body)
	{
		id = from+to+subject+body;
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		date = new Date();
		unread = true;
	}
	
	public String getTo(){
		return to;
	}
	
	public String getFrom(){
		return from;	
				}
	
	public String getSubject(){
		return (subject);
	}
	
	public String getBody(){
		return body;
	}
	public Date getDate(){
		return date;
	}
	public boolean unread(){
		return unread;
	}
	public void read(){
		unread = false;
	}
}
