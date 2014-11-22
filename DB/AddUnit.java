import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.Entity;


public class AddUnit {
	ArrayList<Entity> addList;
	
	AddUnit(Entity...entities)
	{
		addList = new ArrayList<Entity>();
		for(Entity e : entities)
		{
			addList.add(e);
		}
	}
	
	AddUnit(List<Entity> ents)
	{
		addList = (ArrayList<Entity>) ents;
	}
	
	public List<Entity> getListToAdd() 
	{
		return addList;
	}
}
