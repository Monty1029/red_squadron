//Evan Bottomley
//Updated Oct. 16, 2015

import java.util.*;

/**
 * Manages a collection of Users and Documents
 * @author Evan Bottomley
 *
 */
public class Simulation {

	private List<String> allTags;
	private List<String> availableTags;
	private HashMap<User, ArrayList<Document>> map;
	private List<Document> allDoc;
	private List<User> allUser;
	
	/**
	 * Default constructor, arbitrarily set to 5
	 */
	public Simulation() {
		this(5);
	}
	
	
	/**
	 * Primary constructor, create new Simulations, fills out allTags list and availableTags list
	 * @param n number of different tags that will be used in the simulation
	 */
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
			s = allTags.get(r);
			availableTags.add(s);
			allTags.remove(s);
		}
	}
	
	/**
	 * Add a user's document like to the hashmap
	 * @param user the User key
	 * @param doc the Document to add
	 */
	public void addLike(User user, Document doc) {
		if (map.containsKey(user)) { //If user is in the hashmap, add document to the ArrayList
			map.get(user).add(doc);
		}
		else {
			ArrayList<Document> l = new ArrayList<Document>(); //If not, create new ArrayList and put the user in the map
			l.add(doc);
			map.put(user, l);
		}
	}
	
	
	/**
	 * Seed the simulation with n1 consumers, n2 producers and n3 documents
	 * @param n1 number of consumers
	 * @param n2 number of producers
	 * @param n3 number of documents to begin the simulation with
	 */
	public void seed(int n1, int n2, int n3) {
		//Add users
		User u;
		Random rand = new Random();
		int r;
		//Add n1 Consumers
		System.out.print("Consumers:");
		for (int i = 0; i < n1; i++) {
			r = rand.nextInt(availableTags.size());
			u = new Consumer(("Consumer"+i), availableTags.get(r), this);
			System.out.print(" " + u.getName() + "(" + u.getTaste() + ") ");
			allUser.add(u);
		}
		//Add n2 Producers
		System.out.print("\nProducers:");
		for (int i = 0; i < n2; i++) {
			r = rand.nextInt(availableTags.size());
			u = new Producer(("Producer"+i), availableTags.get(r), this);
			System.out.print(" " + u.getName() + "(" + u.getTaste() + ") ");
			allUser.add(u);
		}
		//Add n3 documents
		Document d;
		System.out.print("\nSeed Documents:");
		for (int i = 0; i < n3; i++) {
			r = rand.nextInt(availableTags.size());
			d = new Document(("Doc" + i), availableTags.get(r));
			System.out.print(" " + d.getName() + "(" + d.getTag() + ") ");
			allDoc.add(d);
		}
		System.out.println("\n");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("\n");
	}
	
	
	/**
	 * Print the list of available tags in the simulation
	 */
	private void printTags() {
		System.out.print("Available Tags:");
		for(int i = 0; i < availableTags.size(); i++) {
			System.out.print(" " + availableTags.get(i));
		}
		System.out.println("");
	}
	

	/**
	 * Return the hashMap of users and liked documents
	 * @return the hashMap of users and liked documents
	 */
	public HashMap<User, ArrayList<Document>> getHash() {
		return map;
	}
	
	/**
	 * Method for a producer to add the new document
	 * @param doc document to add
	 */
	public void addDoc(Document doc)
	{
		allDoc.add(doc);
	}
	

	
	/**
	 * Main function creates a simulation, seeds it and runs for x iterations
	 * @param args not used currently
	 */
	public static void main(String[] args) {
		//Create a new simulation and seed the simulation
		Simulation sim = new Simulation(5);
		sim.printTags();
		sim.seed(5, 5, 10);
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
