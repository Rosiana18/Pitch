package BaseClasses;
import java.util.ArrayList;
import java.util.HashMap;

import com.googlecode.objectify.annotation.*;

@Entity
public class Ent
{
	@Id String id;
    
    
/*	//personality tags
	@Index int community=0;
	@Index int personal=0;
	@Index int causeOriented=0;
	@Index int political=0;
	@Index int environment=0;
	@Index int socialAwareness=0;
	@Index int education=0;
	@Index int technology=0;
	@Index int code=0;
	@Index int robotics=0;
	@Index int maths=0;
	@Index int structured=0;
	@Index int computers=0;
	@Index int physics=0;
	@Index int chemistry=0;
	@Index int sports=0;
	@Index int visual=0;
	@Index int heady=0;
	@Index int philosophical=0;
	@Index int cathartic=0;
	@Index int musical=0;
	@Index int weekendey=0;
	@Index int grownUp=0;
	@Index int ideas=0;
	@Index int quirky=0;
	@Index int traditional=0;
	@Index int funDriven=0;
	@Index int purposeDriven=0;
	@Index int creative=0;
	@Index int depth=0;
	@Index int breadth=0;
	@Index int nightOwl=0;
	@Index int goals=0;
	@Index int openEnded=0;
	@Index int cars=0;
	@Index int food=0; */
	
	
	// ratings
	/*
	@Index int communityService=0;
	@Index int social=0;
	@Index int quirky=0;
	@Index int trendmaker=0;
	@Index int fun=0;
	@Index int thoughtProvoking=0;
	@Index int important=0;
	*/
	
	// personality
	public HashMap<String,Integer> personTagValues;
	/*
	@Index int realist=0;
	@Index int dreamer=0;
	@Index int philosophical=0;
	@Index int serious=0;
	@Index int depthImportant=0;
	@Index int breadthImportant=0;
	@Index int morningPerson=0;
	@Index int weekendy=0;
	@Index int nightOwl=0;
	@Index int goalOriented=0;
	*/
	
	
	//values
	public HashMap<String,Integer> valTagValues;
	/*
	@Index int structure=0;
	@Index int dynamicEnvironment=0;
	@Index int selfReliance=0;
	@Index int teamwork=0;
	@Index int creativeApproach=0;
	@Index int reliability=0;
	@Index int impact=0;
	@Index int enjoyment=0;
	*/
	
	//categorization tags
	public HashMap<String,Integer> catTagValues;
	/*
	@Index int doSomething=0;
	@Index int makeSomething=0; // tangible
	@Index int learnSomething=0;
	@Index int projectLength=0; //1 is smallest, 5 is largest
	@Index int projectSize=0; // 1-5 (partner, small, med, large, organization)
	*/
	
	/*
	@Index int partnerGroup=0;
	@Index int smallGroup=0;
	@Index int medGroup=0;
	@Index int largeGroup=0;
	@Index int organizationGroup=0;
	@Index int startingStage=0;
	@Index int workingStage=0;
	@Index int finishingStage=0;
	@Index int weekendProject=0;
	@Index int shortProject=0;
	@Index int medProject=0;
	@Index int longProject=0;
	@Index int ongoingProject=0;
	*/
	
	//what is it ~ what do you do?
	@Index int science=0;
	@Index int engineering=0;
	@Index int writing=0;
	@Index int craft=0; //i.e. woodworking or metallurgy or whatever
	@Index int fixing=0; //i.e. fixing things like cars or whatever
	@Index int visualDesign=0;
	@Index int conceptDesign=0;
	@Index int event=0; // is it an event, like a gathering, meetings, whatever
	@Index int teaching=0; //is it a yoga workshop or a an algebra class?
	@Index int cause=0; // a project to accomplish something
	@Index int diy=0;
	@Index int art=0;
	@Index int music=0;
	
    public void setCategoryTags(ArrayList<Integer> tags){
    	if(tags.size()==13){
	    	science = tags.get(0);
	    	engineering=tags.get(1);
	    	writing=tags.get(2);
	    	craft=tags.get(3);
	    	fixing=tags.get(4);
	    	visualDesign=tags.get(5);
	    	conceptDesign=tags.get(6);
	    	event=tags.get(7);
	    	teaching=tags.get(8);
	    	cause=tags.get(9);
	    	diy=tags.get(10);
	    	art=tags.get(11);
	    	music=tags.get(12);
    	}
    }
	
	Ent()
	{
		
	}
    Ent(String o)
    {
    	id = o;
    }
    
    public String getId(){
    	return id;
    }
    public ArrayList<String> getCategoryTags(){
    	ArrayList<String> tags = new ArrayList<String>();
    	if(science ==1){
    		tags.add("science");
    	}
    	if(engineering ==1){
    		tags.add("engineering");
    	}
    	if(writing ==1){
    		tags.add("writing");
    	}
    	if(craft ==1){
    		tags.add("craft");
    	}
    	if(fixing ==1){
    		tags.add("fixing");
    	}
    	if(visualDesign ==1){
    		tags.add("visualDesign");
    	}
    	if(conceptDesign ==1){
    		tags.add("conceptDesign");
    	}
    	if(event ==1){
    		tags.add("event");
    	}
    	if(teaching ==1){
    		tags.add("teaching");
    	}
    	if(cause ==1){
    		tags.add("cause");
    	}
    	if(diy ==1){
    		tags.add("diy");
    	}
    	if(art ==1){
    		tags.add("art");
    	}
    	if(music ==1){
    		tags.add("music");
    	}
    	return tags;
    }

    

}
