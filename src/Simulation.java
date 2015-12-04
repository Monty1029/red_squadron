//Evan Bottomley
//Updated Nov. 6, 2015

import java.awt.Component;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import javax.swing.JFrame;

/**
 * Manages a collection of Users and Documents
 * @author Evan Bottomley, Contributors: Garrett Steele and Bronwyn Skelley
 *
 */
public class Simulation extends Observable implements Serializable{

	/**
	 * The various tags
	 * @author Evan Bottomley
	 *
	 */
	public enum AllTags{
		jazz("Jazz"),
		techno("Techno"),
		metal("Metal"),
		classical("Classical"),
		rock("rock");
		
		private final String t;
		
		/**
		 * Constructor
		 * @param t the String tag
		 */
		private AllTags(String t){this.t = t;}
		
		/**
		 * Give the music taste
		 */
		public String toString(){return t;}
	}
	
	
	public static String FILENAMES = "serialized#.txt";
	public static String STACK = "stack#.txt";
	
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
	 */
	public Simulation() {
		
		availableTags = new ArrayList<String>();
		map = new HashMap<User, ArrayList<Document>>();
		allDoc = new ArrayList<Document>();
		allUser = new ArrayList<User>();
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
		int i = 0;
		String s;
		ArrayList<String> chosen = new ArrayList<>();
		if (n > AllTags.values().length) n = AllTags.values().length; //n cannot exceed max number of tags
		while(i < n) {
			r = rand.nextInt(AllTags.values().length);
			s = AllTags.values()[r].toString();
			
			
			if(!chosen.contains(s)){
			availableTags.add(s);
			chosen.add(s);
			i++;}
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
		map = new HashMap<User, ArrayList<Document>>();
		allDoc = new ArrayList<Document>();
		allUser = new ArrayList<User>();
		results = new StringBuffer();
		
		
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
	public void update() {
		this.setChanged();
		this.notifyObservers();
		
		
	}
	
	/**
	 * Starts the simulation
	 * @param tags number of tags
	 * @param cons number of consumers
	 * @param prods number of producers
	 * @param docs number of documents
	 * @param ranks max number of docs to rank
	 */
	public void start(int tags, int cons, int prods, int docs, int ranks) {
		this.ranks = ranks;
		availableTags = new ArrayList<>();
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
	 * Get the graphable User
	 * @return the graphable User
	 */
	public User getGraphable() {
		
		return graphable;
	}
	
	/**
	 * Method to save the simulation to a file
	 * @param saveNum the document number to use in the name
	 */
	public void saveSim(int saveNum, String fileName)
	{
		//create the name of the file to write to
		String file = fileName.replaceAll("#", "" + saveNum);
		
		try {
			//create an output stream to write the simulation with
			ObjectOutputStream serialized = new ObjectOutputStream( new FileOutputStream(file));
			
			serialized.writeObject(this);	//write out the Simulation
			serialized.close();				//close off the output stream
					
					
		} catch (IOException e) {
			//Output to the console if there is a problem
			e.printStackTrace();
		}
	}
	
	/**
	 * Load the simulation stored in the specified file name
	 * @param fileName the name of the file to read from
	 * @param gui 
	 */
	public static Simulation loadSim(String fileName, GUI gui)
	{
		//create a null simulation reference
		Simulation sim = null;
		
		try {
			//create an object input stream to read in the simulation object
			ObjectInputStream serialized = new ObjectInputStream( new FileInputStream(fileName));
			
			
			sim = (Simulation) serialized.readObject();		//read in the simulation
			serialized.close();								//close off the input stream
			
		} 
		//output to the console if there is a problem with the read, adn return null
		catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		
		if(sim != null)
		{
			sim.addObserver(new PayoffGraphUpdatable());
			 if (gui != null){sim.addObserver(gui);}
		}
		
		//return the simulation
		return sim;
	}
	

	
	
}