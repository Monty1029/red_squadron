//Evan Bottomley
//Updated Oct. 12, 2015

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
	
	public void seed() {
		allTags.add("Jazz");
		allTags.add("Techno");
		allTags.add("Metal");
		allTags.add("Classical");
		allTags.add("Rock");
		//Add users
		User u;
		Random rand = new Random();
		int r;
		for (int i = 0; i < 5; i++) {
			r = rand.nextInt(allUser.size());
			u = new Consumer(("Consumer"+i), allTags.get(r));
			allUser.add(u);
			r = rand.nextInt(allUser.size());
			u = new Producer(("Producer"+i), allTags.get(r));
			allUser.add(u);
		}
		//Add documents
		Document d;
		for (int i = 0; i < 5; i++) {
			r = rand.nextInt(allUser.size());
			d = new Document(("Doc" + i), allTags.get(r));
			allDoc.add(d);
		}
	}

	public static void main(String[] args) {
		//Create a new simulation and seed the simulation
		Simulation sim = new Simulation(5);
		sim.seed();
		Random rand = new Random();
		int x;
		int loops = 0;
		List<Document> liked;
		while(loops < 100) {
			x = rand.nextInt(sim.allUser.size());
			User acting = sim.allUser.get(x);
			liked = acting.act(sim.allDoc);
			for(Document doc: liked) {
				sim.addLike(acting, doc);
			}
			loops++;
		}
		System.out.println("End simulation.");
	}

}
