package DB;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import BaseClasses.Ent;
import BaseClasses.Pitch;
import BaseClasses.SortableEnt;
import BaseClasses.User;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
//import com.google.appengine.api.datastore.Query;
//import com.google.appengine.api.search.Query;
import com.googlecode.objectify.cmd.Query;
import com.googlecode.objectify.cmd.LoadType;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class BasicSearch implements DBSearcher {
	String catTags[] = {"doSomething","makeSomething","learnSomething","projectLength","projectSize" };
	String valTags[] = {"structure","dynamicEnvironment","selfReliance","teamwork"
			,"creativeApproach","reliability","impact","enjoyment"};
	String personTags[] = {"realist","dreamer","philosophical","serious","depthImportant",
			"breadthImportant","morningPerson","nightOwl","goalOriented"};
	
	final double catPointsScaling = 5;
	final double valPointsScaling = 5;
	final double personPointsScaling = 5;
	final double keywordPointsScaling = 10;
	
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
	public User getUserByEmail(String id){
		return ofy().load().type(User.class).id(id).now();
	}
	
	@Override
	public List<Ent> filterBy(List<Integer> valList, List<String> strList, User u, 
			int doSomething, int makeSomething, int learnSomething, int groupSize,
			int projectLength, int projectSize, String textFields[])
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
			return refineSearch(a.list(), u, 
					doSomething, makeSomething, learnSomething, groupSize,
					projectLength, projectSize,  textFields);
		}
		
		return refineSearch(q.list(), u, 
				doSomething, makeSomething, learnSomething, groupSize,
				projectLength, projectSize,  textFields);
	}
	
	private List<SortableEnt> freeTextPoints(List<SortableEnt> sortables, String textFields[])
	{
		// give s the values from the description text
		for(SortableEnt s: sortables)
		{
			s.processText(textFields);	
		}
		
		// total counts init
		int totalWords = 0;
		HashMap<String,Integer> totalOccurances = new HashMap<String,Integer>();
		for(String field : textFields)
		{
			totalOccurances.put(field, 0);
		}
		
		// make total counts of values now in s
		for(SortableEnt s : sortables)
		{
			totalWords += s.getTotalWords();
			for(String field : textFields)
			{
				int oldValue = totalOccurances.get(field);
				int newOne = s.getKeywords().get(field);
				totalOccurances.put(field, s.getKeywords().put(field, oldValue+newOne));
			}
		}
		
		// give points based upon how many matches within the text a given pitch contributes to the total
		for(SortableEnt s : sortables)
		{
			for(String field : textFields)
			{
				s.addPoints( (s.getKeywords().get(field)/totalOccurances.get(field))
						*keywordPointsScaling);
			}
		}
		return sortables;
	}
	private List<Ent> refineSearch(List<Ent> pitches, User u, 			
			int doSomething, int makeSomething, int learnSomething, int groupSize,
			int projectLength, int projectSize, String textFields[])
	{
		
		ArrayList<SortableEnt> sortables = new ArrayList<SortableEnt>();
		for(Ent e : pitches)
		{
			SortableEnt sortable = new SortableEnt(e);
			sortables.add(sortable);
			User owner = getUserByEmail( ((Pitch)e).getOwnerId() );
			for(String att : catTags)
			{
				//TODO catTagValues should be provided by the page, not from the User Ent
				double attValueDifference = Math.abs(u.catTagValues.get(att) - owner.catTagValues.get(att));
				if(attValueDifference >= 3)
				{
					continue;
				}
				sortable.addPoints( (1/attValueDifference)*catPointsScaling);
			}
			
			for(String att : valTags)
			{
				double attValueDifference = Math.abs(u.valTagValues.get(att) - owner.valTagValues.get(att));
				if(attValueDifference >= 3)
				{
					continue;
				}
				sortable.addPoints( (1/attValueDifference)*valPointsScaling);
			}
			
			for(String att : personTags)
			{
				double attValueDifference = Math.abs(u.personTagValues.get(att) - owner.personTagValues.get(att));
				if(attValueDifference >= 3)
				{
					continue;
				}
				sortable.addPoints( (1/attValueDifference)*personPointsScaling);
			}
			
		}
		sortables = (ArrayList<SortableEnt>) freeTextPoints(sortables, textFields );
		Collections.sort(sortables);
		ArrayList<Ent> ret = new ArrayList<Ent>();
		for (SortableEnt s : sortables)
		{
			ret.add(s.getEnt());
		}
		return ret;
	}

}
