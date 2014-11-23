package BaseClasses;

import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Subclass;

@Subclass public class User extends Ent{
	@Index String firstName;
	@Index String lastName;
	@Index String password;
	@Index String linkedIn;
	@Index String confirmationKey;
	User(){}
	
	public User(String email, String _firstName, String _lastName, String _password, String _confirmationKey)
	{
		id = email;
		firstName = _firstName;
		lastName = _lastName;
		password = _password;
		confirmationKey = _confirmationKey;
	}
	public String getKey(){
		return confirmationKey;
	}
	public void setKey(String val){
		confirmationKey = val;
	}
}
