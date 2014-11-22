package DB;

import java.util.List;

import com.google.appengine.api.search.Query;

public abstract class DBSearcher {
	abstract Query accrueQuery(SearchUnit...searchUnits);
	abstract List filterBy(SearchUnit...searchUnits);
	abstract List filterBy(String query);
	abstract List search(Query q);
}

