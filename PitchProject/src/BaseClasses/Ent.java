package BaseClasses;
import com.googlecode.objectify.annotation.*;

@Entity
public class Ent
{
	@Id Object id;
    
    
	//personality tags
	@Index int community=0;
	@Index int personal=0;
	@Index int causeOriented=0;
	@Index int political=0;
	@Index int environment=0;
	@Index int socialAwareness=0;
	@Index int education=0;
	@Index int technology=0;
	@Index int code=0;
	@Index int diy=0;
	@Index int robotics=0;
	@Index int maths=0;
	@Index int structured=0;
	@Index int computers=0;
	@Index int physics=0;
	@Index int chemistry=0;
	@Index int sports=0;
	@Index int art=0;
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
	
	//categorization tags
	@Index int definedProduct=0;
	@Index int abstractProduct=0;
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
    
	Ent()
	{
		
	}
    Ent(Object o)
    {
    	id = o;
    }



}
