package DB;

import java.util.List;

import BaseClasses.Ent;

import com.googlecode.objectify.ObjectifyService;


public class DBManager {
	static {
		try{
		ObjectifyService.register(Ent.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	DBSearcher searcher;
	DBadder adder;
	DBsubtracter remover;
	
	static DBManager instance = null;
	public static DBManager getInstance()
	{
		if (instance == null)
		{
			instance = new DBManager();
		}
		return instance;
	}
	private DBManager()
	{
		searcher = new BasicSearch();
		adder = new BasicAdd();
		remover = null;
	}
	
	
	public void add(AddUnit...addUnits)
	{
		adder.add(addUnits);
	}
	public void add(Ent e)
	{
		adder.add(e);
	}
	
	public Ent remove(SearchUnit...searchUnits)
	{
		return remover.remove(searchUnits);
	}
	
	public List filterBy(SearchUnit...searchUnits)
	{
		return searcher.filterBy(searchUnits);
	}
	
	public List filterBy(String query)
	{
		return searcher.filterBy(query);
	}
	
	public Ent getById(String id){
		return searcher.getUserByEmail(id);
	}
	
	
	
}
