package Servlets;

import java.io.FileInputStream;
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
		descriptions = new ArrayList<String>();
		descriptionTitles = new ArrayList<String>();
		
		for(int i=0;i<USER_SIZE;i++){
			User localUser = RandomUser();
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
				((User)DBManager.getInstance().getUserByID(user)).addPitch(localPitch.getId());
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
		String name = title[r.nextInt(title.length-1)];
		String localTitle = name;
		while(DBManager.getInstance().getPitchByID(name) != null){
			localTitle = name+r.nextInt(1000);
		}
		return new Pitch(localTitle, descriptionTitles,descriptions,owner.getId(),10,r.nextInt(20)+1);
	}	
}