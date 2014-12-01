package BaseClasses;

import java.util.HashMap;

public class SortableEnt implements Comparable {
	private double score;
	private int totalWords;
	private HashMap<String,Integer> keywords;
	private Ent mine;
	
	public SortableEnt(Ent a)
	{
		mine = a;
		keywords = new HashMap<String,Integer>();
		score = 0;
		totalWords = 0;
	}
	
	public void processText(String kwords[])
	{
		String t = ((Pitch)mine).getDescription();
		for(String s : t.split(" "))
		{
			totalWords++;
			for(String s1 : kwords)
			{
				if(s.equals(s1))
				{
					int occurances = keywords.get(s1);
					keywords.put(s1, occurances+1);
				}
			}	
		}
	}
	
	public HashMap<String,Integer> getKeywords()
	{
		return keywords;
	}
	public int getTotalWords()
	{
		return totalWords;
	}
	public void addPoints(double _points)
	{
		score+=_points;
	}
	public Ent getEnt()
	{
		return mine;
	}
	
	@Override
	public int compareTo(Object other) {
		// error handling, meh
		if(other instanceof SortableEnt)
		{
			return (int) (score - ((SortableEnt)other).score);
		}
		return 0;
	}

}
