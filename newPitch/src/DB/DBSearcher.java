package DB;

import java.util.List;

//import com.google.appengine.api.datastore.Query;

import BaseClasses.Ent;
import BaseClasses.User;

//import com.google.appengine.api.search.Query;
import com.googlecode.objectify.cmd.Query;
interface DBSearcher {
	Query accrueQuery(SearchUnit...searchUnits);
	List filterBy(SearchUnit...searchUnits);
	List filterBy(String query);
	List<Ent> filterBy(List<Integer> valList, List<String> strList, User u, 
			int doSomething, int makeSomething, int learnSomething, int groupSize,
			int projectLength, int projectSize, String textFields[]);
	List search(Query q);
	Ent getUserByEmail(String id);
}

