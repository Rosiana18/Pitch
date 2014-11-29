package DB;
import static com.googlecode.objectify.ObjectifyService.ofy;
import BaseClasses.Ent;

class BasicAdd implements DBadder{

	@Override
	public void add(AddUnit... addUnits) {
		// fuuuuu
	}

	@Override
	public void add(Ent e) {
		ofy().save().entity(e).now();
		
	}

}
