package BaseClasses;
public class User extends Ent{
	String name;
	
	User(String email, String _name)
	{
		id = email;
		name = _name;
	}
}
