package DB;
import static com.googlecode.objectify.ObjectifyService.ofy;
import BaseClasses.Ent;

public class BasicAdd extends DBadder{

	@Override
	void add(AddUnit... addUnits) {
		// fuuuuu
		
	}

	@Override
	void add(Ent e) {
		ofy().save().entity(e).now();
		
	}

}
