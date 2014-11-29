package DB;

import BaseClasses.Ent;
import com.google.appengine.api.datastore.Entity;

interface DBsubtracter {
	Ent remove(SearchUnit...searchUnits);
	Ent remove(Object id);
}
