//Evan Bottomley
//Updated Oct. 10, 2015

import java.util.*;
public class Simulation {

	private List<String> allTags;
	private List<String> availableTags;
	private HashMap<User, List<Document>> map;
	private List<Document> allDoc;
	private List<User> allUser;
	
	public Simulation() {
		this(5);
	}
	
	public Simulation(int n) {
		allTags = new ArrayList<String>();
		availableTags = new ArrayList<String>();
		map = new HashMap<User, ArrayList<Document>>();
		allDoc = new ArrayList<Document>();
		allUser = new ArrayList<User>();
	}
	
	//Add a user's document like to the hashmap
	public void addLike(User user, Document doc) {
		if (map.containsKey(user)) {
			map.get(user).add(doc);
		}
		else {
			List<Document> l = new ArrayList<Document>();
			l.add(doc);
			map.put(user, l);
		}
	}

	public static void main(String[] args) {
		//Create a new simulation and seed the simulation
		Simulation sim = new Simulation();
		sim.allTags.add("Test1");
		sim.availableTags.add("Test1");
		//Add users
		User u;
		for (int i = 0; i < 5; i++) {
			u = new Consumer(("Consumer"+i), "Test1");
			allUser.add(u);
			u = new Producer("Producer"+i, "Test1");
			allUser.add(u);
		}
		//Add documents
		Document d;
		for (int i = 0; i < 5; i++) {
			d = new Document(("Doc" + i), "Test1");
			allDoc.add(d);
		}
		
		Random rand = new Random();
		int x;
		int loops = 0;
		while(loops < 100) {
			x = rand.nextInt(allUser.size()) + 1;
			allUser.get(x).act();
			loops++;
		}
		System.out.println("End simulation.");
	}

}
