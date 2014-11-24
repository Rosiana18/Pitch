package DB;

import BaseClasses.Ent;
import com.google.appengine.api.datastore.Entity;

public abstract class DBsubtracter {
	abstract Ent remove(SearchUnit...searchUnits);
	abstract Ent remove(Object id);
}
