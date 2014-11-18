import com.google.appengine.api.datastore.Entity;

public abstract class DBsubtracter {
	abstract Entity remove(SearchUnit...searchUnits);
}
