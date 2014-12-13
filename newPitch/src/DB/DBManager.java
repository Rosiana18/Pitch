package DB;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import BaseClasses.Ent;
import BaseClasses.Pitch;
import BaseClasses.User;

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
	public static ArrayList<Integer> loadUp(HttpServletRequest req)
	{
		String whatIsIts[] = {"science","engineering","writing","craft","fixing","visualDesign"
				,"conceptDesign","event","teaching","cause","diy","art","music"};
		
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(String a : whatIsIts)
		{
			if(req.getParameter(a)==null){
				ret.add(0);
			}else{
				ret.add(Integer.valueOf(req.getParameter(a)));
			}
		}
		return ret;
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
	
	public List<Ent> filterBy(List<Integer> valList, List<String> strList, User u, int size, int len, String[] keyWords)
	{
		return searcher.filterBy(valList, strList, u, len, size, keyWords);
	}
	
	public User getUserByID(String id){
		return searcher.getUserByEmail(id);
	}
	
	public Pitch getPitchByID(String id)
	{
		return searcher.getPitchByID(id);
	}
	
	
	
}
