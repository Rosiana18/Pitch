package DB;
import java.util.ArrayList;
import java.util.List;

import BaseClasses.Ent;
import BaseClasses.Pitch;
import BaseClasses.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
//import com.google.appengine.api.datastore.Query;
//import com.google.appengine.api.search.Query;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.LoadType;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class BasicSearch implements DBSearcher {
	public Query accrueQuery(SearchUnit... searchUnits) {
		
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
	public List filterBy(SearchUnit... searchUnits) {
		
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
	public List search(Query q) {
		
		//return ofy().load()...(q);
		//not sure this is even useful
		System.out.println("Not implemented:(");
		return null;
	}

	@Override
	public List filterBy(String query) {
		String tokens[] = query.split(" ");
		// replace this hardcoding with some calls to Quantum to figure out which tokens
		// are tags, which are categories, etc.
		// search based on that
		
		// this is just assuming you give it an email as an id
		
		return ofy().load().type(User.class).filterKey("",query).list();
	}
	
	@Override
	public Ent getUserByEmail(String id){
		return ofy().load().type(User.class).id(id).now();
	}
	
	@Override
	public List<Ent> filterBy(List<Integer> valList, List<String> strList)
	{
		Class type = null;
		assert(valList.size() == strList.size());
		
		// count how many entities get cut down for each filter in the search
		// fill with nulls so that we can distinguish "haven't started" from "0"
		ArrayList<Integer> count = new ArrayList<Integer>();
		for(int j = 0; j<valList.size(); j++)
		{
			count.add(null);
		}
		
		// differentiate between a search for a Pitch vs. User
		if(strList.get(0).equals("science"))
		{
			type = Pitch.class;
		}
		if(strList.get(0).equals("realist"))
		{
			type = User.class;
		}
		if(type == null)
		{
			return null;
		}
		
		// start querying 
		LoadType<Ent> a = ofy().load().type(type);
		Query<Ent> q = null;
		for(int i = 0; i < valList.size(); i++)
		{
			Integer val = valList.get(i);
			String att = strList.get(i);
			if(val == null)
			{
				continue;
			}
			if(q == null)
			{
				q = a.filter(att+" >=", val);
				continue;
			}
			q = q.filter(att+" >=", val);
			count.set(i, q.count());
			
			
		}
		
		
		if( q == null)
		{
			return a.list();
		}
		
		return q.list();
	}

}
