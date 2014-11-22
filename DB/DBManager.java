import java.util.List;
import com.google.appengine.api.datastore.Entity;


public class DBManager {
	
	DBSearcher searcher;
	DBadder adder;
	DBsubtracter remover;
	
	DBManager instance = null;
	public DBManager getInstance()
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
		adder = null;
		remover = null;
	}
	
	
	public void add(AddUnit...addUnits)
	{
		adder.add(addUnits);
	}
	public void add(Entity e)
	{
		adder.add(e);
	}
	
	public Entity remove(SearchUnit...searchUnits)
	{
		return remover.remove(searchUnits);
	}
	
	public List filterBy(SearchUnit...searchUnits)
	{
		return searcher.filterBy(searchUnits);
	}
	
}
