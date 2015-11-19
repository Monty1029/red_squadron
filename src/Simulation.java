//Evan Bottomley
//Updated Nov. 6, 2015

import java.awt.Component;
import java.awt.Toolkit;
import java.util.*;

import javax.swing.JFrame;

/**
 * Manages a collection of Users and Documents
 * @author Evan Bottomley, Contributors: Garrett Steele and Bronwyn Skelley
 *
 */
public class Simulation extends Observable{

	private List<String> allTags;
	private List<String> availableTags;
	private HashMap<User, ArrayList<Document>> map;
	private List<Document> allDoc;
	private List<User> allUser;
	private GUI gui;
	private StringBuffer results;
	private int ranks = 10;
	private Producer seed;
	private JFrame graph;														//added by Garrett and Bronwyn on Nov 6
	private User graphable;														//added by Garrett and Bronwyn on Nov 6
	
	//getters
	/**
	 * Get the list of all possible tags
	 * @return all possible tags
	 */
	public List<String> getAllTags(){return allTags;}
	/**
	 * Get the list of all Documents
	 * @return the list of all Documents
	 */
	public  List<Document> getAllDoc(){return allDoc;}
	/**
	 * Get the list of all Users
	 * @return the list of all Users
	 */
	public  List<User> getAllUser(){return allUser;}
	/**
	 * Get the list of all tags used
	 * @return the list of all tags used
	 */
	public List<String> getAvailableTags(){return availableTags;}
	
	/**
	 * Set which usert to graph
	 * @param u the user to graph
	 */
	public void setGraphable(User u){graphable = u;}
	
	
	/**
	 * Primary constructor, create new Simulations, fills out allTags list
	 * @param n number of different tags that will be used in the simulation
	 */
	public Simulation() {
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
		seed = new Producer("Seed","seed", this);
		addObserver(new PayoffGraphUpdatable());
		results = new StringBuffer();
		
	}
	
	/**
	 * Method to add the GUI as an observer
	 * @param g GUIto reference
	 */
	public void setGUI(GUI g) {
		this.addObserver(g);
	}
	
	
	/**
	 * Fills out availableTags list with a certain number of tags from allTags
	 * @param n number of different tags that will be used in the simulation
	 */
	public void selectTags(int n) {
		Random rand = new Random();
		int r;
		String s;
		if (n > allTags.size()) n = allTags.size(); //n cannot exceed max number of tags
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
		seed = new Producer("Seed","seed", this);		//seeding producer
		graphable = seed;								//pick the seed to graph
		
		
		
		//Add n1 Consumers
		System.out.print("Consumers:");
		results.append("Consumers:" );
		for (int i = 0; i < n1; i++) {
			r = rand.nextInt(availableTags.size());
			u = new Consumer(("Consumer"+i), availableTags.get(r), this);
			System.out.print(" " + u.getName() + "(" + u.getTaste() + ") ");
			results.append(" " + u.getName() + "(" + u.getTaste() + ") ");
			allUser.add(u);
		}
		//Add n2 Producers
		System.out.print("\nProducers:");
		results.append("\nProducers:");
		for (int i = 0; i < n2; i++) {
			r = rand.nextInt(availableTags.size());
			u = new Producer(("Producer"+i), availableTags.get(r), this);
			System.out.print(" " + u.getName() + "(" + u.getTaste() + ") ");
			results.append(" " + u.getName() + "(" + u.getTaste() + ") ");
			allUser.add(u);
		}
		//Add n3 documents
		Document d;
		System.out.print("\nSeed Documents:");
		results.append("\nSeed Documents:");
		for (int i = 0; i < n3; i++) {
			r = rand.nextInt(availableTags.size());
			d = new Document(("Doc" + i), availableTags.get(r), seed);
			System.out.print(" " + d.getName() + "(" + d.getTag() + ") ");
			results.append(" " + d.getName() + "(" + d.getTag() + ") ");
			allDoc.add(d);
		}
		System.out.println("\n");
		System.out.println("----------------------------------------------------------------------");
		System.out.println("\n");
		results.append("\n\n----------------------------------------------------------------------\n\n");
	}
	
	
	/**
	 * Print the list of available tags in the simulation
	 */
	private void printTags() {
		System.out.print("Available Tags:");
		results.append("Available Tags:");
		for(int i = 0; i < availableTags.size(); i++) {
			System.out.print(" " + availableTags.get(i));
			results.append(" " + availableTags.get(i));
		}
		System.out.println("");
		results.append("\n");
	}
	

	/**
	 * Return the hashMap of users and liked documents
	 * @return the hashMap of users and liked documents
	 */
	public HashMap<User, ArrayList<Document>> getHash() {
		return map;
	}
	
	/**
	 * Get the results String.
	 * @return the results String
	 */
	public StringBuffer getResults() {
		return results;
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
	 * Print the update to the GUI.
	 */
	private void update() {
		/*gui.getTextArea().setText(results.toString());
		System.out.println(gui.getTextArea().getText());
		if(graphable != null){updateGraph();}					//line added by Garrett and Bronwyn on nov 6*/
		
		this.setChanged();
		this.notifyObservers();
		
		
	}
	
	/**
	 * Method to update the graph of a specific User. Added by Garrett and Bronwyn on Nov 6
	 */
	/*private void updateGraph()
	{
		if (graph != null){graph.dispose();}
		
		graph = new JFrame("Graph: " + graphable.getName());
		graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Double dub = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		graph.setSize(dub.intValue(), 700);
		
		graph.add(new PayoffGraph(graphable.getPayoffArr(), graph));
		graph.setVisible(true);
		
	}*/
	
	
	/**
	 * Starts the simulation
	 * @param tags number of tags
	 * @param cons number of consumers
	 * @param prods number of producers
	 * @param docs number of documents
	 */
	public void start(int tags, int cons, int prods, int docs, int ranks) {
		this.ranks = ranks;
		this.selectTags(tags);
		this.printTags();
		this.seed(cons, prods, docs);
		update();
	}
	
	/**
	 * Perform n steps.
	 * @param n number of steps to perform
	 */
	public void step(int n) {
		Random rand = new Random();
		int x;
		List<Document> liked;
		for (int i = 0; i < n; i++) {
			x = rand.nextInt(allUser.size());
			User acting = allUser.get(x);
			liked = acting.act(allDoc, ranks);
			for(Document doc: liked) {
				addLike(acting, doc);
			}
			results.append("Name: " + acting.getName() + ", Taste: " + acting.getTaste() + "\n");
			results.append("Following: " + acting.getFollowing().toString() + "\n");
			results.append("Followed: " + acting.getFollowed() + "\n");
			results.append("Likes: " + liked.toString() + "\n");
			results.append("Payoff: " + acting.getPayoff()+ "\n");
			results.append("\n");
			
			//added by Garrett and Bronwyn on Nov 6
			for(User u: allUser){
				u.getPayoffArr().add(u.getCumulative());
			}
		}
		update();
	}

	
	/**
	 * Main function creates a simulation and GUI
	 * @param args not used currently
	 */
	public static void main(String[] args) {
		//Create a new simulation and seed the simulation
		
		Simulation sim = new Simulation();
		GUI g = new GUI(sim);
		sim.setGUI(g);
		
		while(true){} //Loop forever
		/*
		sim.selectTags(5);
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
		*/
	}
	
	/**
	 * Get the graphable User
	 * @return the graphable User
	 */
	public User getGraphable() {
		
		return graphable;
	}
}