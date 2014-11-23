package DB;
import java.util.List;

import BaseClasses.Ent;
import BaseClasses.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.search.Query;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class BasicSearch extends DBSearcher {

	@Override
	Query accrueQuery(SearchUnit... searchUnits) {
		
		for(SearchUnit su : searchUnits)
		{
			if(su.type == SearchUnit.SUTypes.CATEGORY)
			{
				// do stuff
			}
			if(su.type == SearchUnit.SUTypes.TAG)
			{
				// other stuff
			}
		}
		System.out.println("Not implemented :(");
		return null;
	}

	@Override
	List filterBy(SearchUnit... searchUnits) {
		
		for(SearchUnit su : searchUnits)
		{
			if(su.type == SearchUnit.SUTypes.CATEGORY)
			{
				// do stuff
			}
			if(su.type == SearchUnit.SUTypes.TAG)
			{
				// other stuff
			}
		}
		System.out.println("Not implemented :(");
		return null;
	}

	@Override
	List search(Query q) {
		
		//return ofy().load()...(q);
		//not sure this is even useful
		System.out.println("Not implemented:(");
		return null;
	}

	@Override
	List filterBy(String query) {
		String tokens[] = query.split(" ");
		// replace this hardcoding with some calls to Quantum to figure out which tokens
		// are tags, which are categories, etc.
		// search based on that
		
		// this is just assuming you give it an email as an id
		
		return ofy().load().type(User.class).filterKey("",query).list();
	}
	
	@Override
	Ent getById(String id){
		return ofy().load().type(User.class).id(id).now();
	}

}
