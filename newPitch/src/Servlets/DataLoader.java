package Servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BaseClasses.Ent;
import BaseClasses.Pitch;
import BaseClasses.User;
import BaseClasses.Message;
import DB.DBManager;

import com.googlecode.objectify.ObjectifyService;

public class DataLoader extends HttpServlet {
	static final int PITCH_SIZE = 3;
	static final int USER_SIZE = 10;
	static final int MIN_FRIEND = 1;
	static final int MAX_FRIEND = 3;
	static final int MIN_PITCH_COLLABS = 1;
	static final int MAX_PITCH_COLLABS = 2;
	static final int MIN_PITCH_BIDDERS = 0;
	static final int MAX_PITCH_BIDDERS = 3;
	static final int MIN_USER_PITCHES = 0;
	static final int MAX_USER_PITCHES = 2;
	static final String[] lastName = {"adams","allen","allison","alvarez","anderson","andrews","armstrong","arnold","avila","bailey","baker","barnes","bennet","bishop","boyd","bradley","brooks","brown","bryan","burke","burton","butler",
										"campbell","carlson","carr","carter","chase","chen","christensen","clark","collins","comer","cook","cooper","cox","crawford","cunningham","davis","day","dean","dickinson","edwards","elliott","ellis","evans",
										"fischer","fisher","fong","ford","freeman","frost","garcia","gardner","gomes","gomez","gonzales","graham","green","griffin","hall","hamilton","hansen","harris","harrison","hartman","harvey","hayes","henderson",
										"henry","hernandez","hill","holmes","howard","hughes","jackson","jensen","johnson","jones","keller","kelley","kennedy","king","lane","long","lopez","marsh","mathews","mcdonald","miller","mitchell","moore","morgan",
										"morris","nelsen","newton","obrien","oconnor","olsen","palmer","parker","patterson","peck","perkins","perry","peterson","phillips","powell","price","randolph","reed","rice","richardson","roberts","robinson","rogers",
										"sanders","simpson","smith","spencer","stewart","stone","walker","wallace","warner","williams","young","zimmer","zimmerman"};
	static final String[] firstName = {"james","john","robert","michael","mary","william","david","richard","charles","joseph","thomas","patricia","christopher","linda","barbara","daniel",
										"paul","mark","elizbeth","donald","jennifer","george","maria","kenneth","susan","steven","edward","margaret","brian","ronald","dorothy","anthony","lisa","kevin",
										"nancy","karen","betty","helen","jason","matthew","gary","timothy","sandra","jose","larry","jeffrey","frank","donna","carol","ruth","scott","eric","stephen","andrew",
										"sharon","michelle","laura","sarah","kimberly","deborah","jessica","raymond","shirley","cynthia","angela","mellisa","brenda","amy","jerry","gregory","stephanie","willie",
										"patrick","terry","peter","christine","marie","janet","frances","catherine","henry"};
	static final String[] title = {"Awesome Robot", "Godzilla","Deadpool","Super Computer","Christmas Tree"};
	static final String[] descTitles = {"Awesome Robot","Godzilla","Deadpool","Super Computer","Christmas Tree"};
	static final String[] descs = {"This will be an awesome robot, that can bring beers to you from your fridge.",
										"This project will genetically modify a lizzard to make real godzilla.",
										"Let's make a deadpool costume.",
										"This will be an awesome super computer that can add 2 single digit numbers.",
										"It is almost Christmas. Let's make our own christmas tree using toilet papers."};
	ArrayList<String> descriptionTitles;
	ArrayList<String> descriptions;
	Random r = new Random();
	ArrayList<String> users;
	
	static {
			try{
		ObjectifyService.register(Ent.class);
		ObjectifyService.register(User.class);
		ObjectifyService.register(Pitch.class);
		ObjectifyService.register(Message.class);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static final Logger _log = Logger.getLogger(DataLoader.class
			.getName());

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {	
		users = new ArrayList<String>();
		

		
		for(int i=0;i<USER_SIZE;i++){
			User localUser = RandomUser();
			String mainDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam sed neque ipsum. Maecenas luctus nisl eget metus commodo, eget bibendum quam convallis. Suspendisse fringilla lacinia mi vitae scelerisque. Vestibulum lobortis arcu condimentum ipsum ultrices, vitae dictum dolor placerat. Aenean iaculis nulla sapien, quis luctus augue scelerisque ut. Mauris tempor pulvinar tortor, eu aliquam elit sagittis vitae. Maecenas at aliquam magna, laoreet congue turpis. Pellentesque non lacus sit amet nulla vulputate rutrum id ac augue. Nunc hendrerit, odio ut hendrerit sodales, ex dui laoreet ligula, sit amet posuere tortor mauris eu arcu. Praesent nec placerat velit. Pellentesque ultrices massa sapien, eu vehicula tellus fermentum vitae. Vestibulum neque quam, rutrum et aliquam eget, auctor eget sapien. Quisque eu quam ac tortor mollis facilisis mollis vitae odio. Nulla maximus enim justo, nec molestie diam bibendum vitae. Nunc blandit molestie enim eu rhoncus. Donec arcu nulla, mollis cursus venenatis vel, imperdiet volutpat mi. Nulla et magna ut nisi mattis varius quis non enim. Vestibulum volutpat nibh libero, ac elementum ipsum pretium facilisis. Nunc ut luctus nunc, non volutpat neque. Quisque lacinia lorem orci, et semper turpis rutrum a. In hac habitasse platea dictumst. Vestibulum sodales diam ac nibh consequat, a tincidunt tortor convallis. Maecenas ante velit, accumsan non molestie vitae, gravida eget lorem. Proin est arcu, laoreet in condimentum eget, vulputate eu lacus. Proin tempus elit id massa fermentum dapibus. Nulla at consequat ex. Ut venenatis euismod nulla sed ultricies. Nulla id tempus felis. Sed accumsan, dui non cursus semper, purus lacus efficitur augue, quis condimentum velit libero in tellus. Nunc id venenatis dui, ut sodales lorem. Phasellus nulla nibh, laoreet et est vel, interdum hendrerit diam. Nulla sagittis ex ut tortor auctor, et aliquet nisi rutrum. Sed bibendum nec ex sed porttitor.";
			localUser.setAbout(mainDescription);
			users.add(localUser.getId());
			DBManager.getInstance().add(localUser);
		}
		for(int i=0;i<users.size();i++){
			int friendSize = r.nextInt(MAX_FRIEND);
			for(int j = 0; j<friendSize; j++){
			String user = users.get(r.nextInt(users.size()));
			User theUser = (User)DBManager.getInstance().getUserByID(users.get(i));
			User theOtherUser = (User)DBManager.getInstance().getUserByID(user);
			theUser.addFriend(user);
			theOtherUser.addFriend(users.get(i));
			DBManager.getInstance().add(theUser);
			DBManager.getInstance().add(theOtherUser);
			}
		}
		for(int i=0; i<PITCH_SIZE;i++){
			Pitch localPitch = RandomPitch((User)DBManager.getInstance().getUserByID(users.get(r.nextInt(users.size()-1))));
			int max_collab = 5;
			int max_bidder = 5;
			for(int k=0; k<=r.nextInt(max_collab);k++){
				String user = users.get(r.nextInt(users.size()-1));
				localPitch.addUser(user);
				User theUser = ((User)DBManager.getInstance().getUserByID(user));
				theUser.addPitch(localPitch.getId());
				DBManager.getInstance().add(theUser);
				}
			for(int k=0; k<=r.nextInt(max_bidder);k++){
				String user = users.get(r.nextInt(users.size()-1));
				localPitch.addBidder(user);	
				}
			DBManager.getInstance().add(localPitch);
			
			}
		}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {	
		doPost(req,resp);
	}
	
	private User RandomUser(){
		String first = firstName[r.nextInt(firstName.length-1)];
		String last = lastName[r.nextInt(lastName.length-1)];
		String email = first+"."+last+"@pitch.com";
		String password = "password123";
		while(DBManager.getInstance().getUserByID(email) != null){
			email = first+"."+last+r.nextInt(1000)+"@pitch.com";
		}
		return new User(email,first,last,password,"0");
	}
	private Pitch RandomPitch(User owner){
		int randomNum = r.nextInt(descs.length-1);
		descriptions = new ArrayList<String>();
		descriptions.add(descs[randomNum]);
		descriptionTitles = new ArrayList<String>();
		descriptionTitles.add(descTitles[randomNum]);
		String name = title[randomNum];
		String localTitle = name;
		//while(DBManager.getInstance().getPitchByID(name) != null){
			localTitle = name+r.nextInt(1000);

		//}
		ArrayList<Integer> tagslol = new ArrayList<Integer>();
		for(int j = 0; j<13; j++)
		{
			if(r.nextInt()%5 == 0)
			{
				tagslol.add(1);
			}
			else
			{
				tagslol.add(0);
			}
		}
		return new Pitch(localTitle, descriptionTitles,descriptions,tagslol,owner.getId(),10,r.nextInt(20)+1);

		//}
		
	}	
}