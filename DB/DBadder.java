import com.google.appengine.api.datastore.Entity;

public abstract class DBadder {
	abstract void add(AddUnit...addUnits);
	abstract void add(Entity e);
}
