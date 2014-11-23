package BaseClasses;
public class User extends Ent{
	String firstName;
	String lastName;
	String password;
	String linkedIn;
	String confirmationKey;
	
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
}
