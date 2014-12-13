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
		User u = ofy().load().type(User.class).id(id).now();
		return u;
	}
	
	@Override
	public Pitch getPitchByID(String id)
	{
		return ofy().load().type(Pitch.class).id(id).now();
	}
	
	@Override
	public List<Ent> filterBy(List<Integer> valList, List<String> strList, User u,
			int projectLength, int projectSize, String textFields[])
	{
		Class type = null;
		assert(valList.size() == strList.size());
		
		// count how many entities get cut down for each filter in the search
		// fill with nulls so that we can distinguish "haven't started" from "0"
		ArrayList<Pitch> ret = new ArrayList<Pitch>();

		

		
		// start querying 
		//LoadType<Pitch> a = ofy().load().type(Pitch.class);
		for(int i = 0; i < valList.size(); i++)
		{
			Integer val = valList.get(i);
			String att = strList.get(i);
			/*for(Pitch p : ofy().load().type(Pitch.class).list())
			{
			
					ret.add(p);
				
			}*/
		}
		for(Pitch p : ofy().load().type(Pitch.class).list())
		{
		
				ret.add(p);
			
		}
		
		
		return refineSearch(ret, u, projectLength, projectSize,  textFields);
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
				if(totalOccurances.get(field) != 0){
					s.addPoints( (s.getKeywords().get(field)/totalOccurances.get(field))
							*keywordPointsScaling);
				}
				
			
			}
		}
		return sortables;
	}
	private List<Ent> refineSearch(List<Pitch> pitches, User u, 			
			int length, int size, String textFields[])
	{
		
		ArrayList<SortableEnt> sortables = new ArrayList<SortableEnt>();
		for(Ent e : pitches)
		{
			SortableEnt sortable = new SortableEnt(e);
			sortable.init(textFields);
			sortables.add(sortable);
			User owner = getUserByEmail( ((Pitch)e).getOwnerId() );
			
			
			
			/*for(String att : catTags)
			{
				//TODO catTagValues should be provided by the page, not from the User Ent
				double attValueDifference = Math.abs(u.catTagValues.get(att) - owner.catTagValues.get(att));
				if(attValueDifference >= 3)
				{
					continue;
				}
				sortable.addPoints( (1/attValueDifference)*catPointsScaling);
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
			
			*for(String att : valTags)
			{
				HashMap uhm = u.valTagValues;
				HashMap ohm = owner.valTagValues;
				double attValueDifference = Math.abs((Double)uhm.get(att) - (Double)ohm.get(att));
				if(attValueDifference >= 3)
				{
					continue;
				}
				sortable.addPoints( (1/attValueDifference)*valPointsScaling);
			}
			*/
			
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
