package DB;
import java.util.ArrayList;


public abstract class SearchUnit {
	enum SUTypes {CATEGORY,TAG,DATE}
	ArrayList<Object> data;
	SUTypes type;
}
