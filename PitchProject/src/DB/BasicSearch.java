package DB;
import java.util.ArrayList;
import java.util.List;

import BaseClasses.Ent;
import BaseClasses.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.search.Query;
import com.google.appengine.api.search.Query.Builder;
import com.googlecode.objectify.cmd.LoadType;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class BasicSearch<E> extends DBSearcher {

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
		LoadType<User> lt = ofy().load().type(User.class);
		com.googlecode.objectify.cmd.Query<User> ltf = null;
		String tokens[] = query.split(" ");
		for(String token : tokens)
		{
			String queryElements[] = token.split(";");
			String first = queryElements[0];
			String second = queryElements[1];
			if(ltf == null)
			{
				ltf = lt.filter(first, second);
			}
			else
			{
				ltf = ltf.filter(first, second);
			}
			
		}
		
		return ltf.list();
	}
	
	@Override
	Ent getById(String id){
		return ofy().load().type(User.class).id(id).now();
	}

}

