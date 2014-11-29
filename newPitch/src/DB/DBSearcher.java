package DB;

import java.util.List;

//import com.google.appengine.api.datastore.Query;

import BaseClasses.Ent;

//import com.google.appengine.api.search.Query;
import com.googlecode.objectify.cmd.Query;
interface DBSearcher {
	Query accrueQuery(SearchUnit...searchUnits);
	List filterBy(SearchUnit...searchUnits);
	List filterBy(String query);
	List filterBy(List<Integer> valList, List<String> strList);
	List search(Query q);
	Ent getUserByEmail(String id);
}

