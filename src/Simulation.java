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
		allTags.add("Jazz");
		allTags.add("Techno");
		allTags.add("Metal");
		allTags.add("Classical");
		allTags.add("Rock");
		Random rand = new Random();
		int r;
		String s;
		if (n > allTags.size()) n = allTags.size();
		for (int i = 0; i < n; i++) {
			r = rand.nextInt(allTags.size());
			s = allTags.get(i);
			availableTags.add(s);
			allTags.remove(s);
		}
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
		//Add users
		User u;
		Random rand = new Random();
		int r;
		for (int i = 0; i < 5; i++) {
			r = rand.nextInt(allUser.size());
			u = new Consumer(("Consumer"+i), availableTags.get(r), this);
			allUser.add(u);
			r = rand.nextInt(allUser.size());
			u = new Producer(("Producer"+i), availableTags.get(r), this);
			allUser.add(u);
		}
		//Add documents
		Document d;
		for (int i = 0; i < 5; i++) {
			r = rand.nextInt(allUser.size());
			d = new Document(("Doc" + i), availableTags.get(r));
			allDoc.add(d);
		}
	}
	
	public HashMap<User, ArrayList<Document>> getHash() {
		return map;
	}

	public static void main(String[] args) {
		//Create a new simulation and seed the simulation
		Simulation sim = new Simulation(5);
		sim.seed();
		Random rand = new Random();
		int x;
		int loops = 0;
		List<Document> liked;
		while(loops < 50) {
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
