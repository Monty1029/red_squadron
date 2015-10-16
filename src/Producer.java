/* SYSC3110 Software Design Project
 * Team redSquadron
 * Producer subclass by Monty Dhanani
 */

import java.util.*;

/**
 * Sub-class of a User that creates Documents and likes them as soon as they are created, can also like and rank other document
 * @author Monty Dhanani
 *
 */
public class Producer extends User {

	private List<Document> produced;

	/**
	 * Creates a new Producer object
	 * @param username name of user
	 * @param taste preferred taste of user
	 * @param sim the simulation
	 */
	public Producer(String username, String taste, Simulation sim) {
		super(username, taste, sim);
		produced = new ArrayList<Document>();
	}

	/**
	 * Cycles through all the existing documents and returns an ArrayList
	 * of Documents with the same taste as the Consumer.
	 * Producer also likes every Document it creates
	 * @param allDocs list of all existing documents
	 */
	public List<Document> act(List<Document> allDocs) {
		String toprint = "" + super.getName() + "(" + super.getTaste() + ") " + " likes: ";
		ArrayList<Document> sameTagDocs = new ArrayList<Document>();

		produce();									//make a doc
		ArrayList<Document> ranked = rank(allDocs);
		int payoff = this.payoff(ranked);	//rank them and get payoff
		
		for (Document d : allDocs) {
			if (d.getTag().equals(super.getTaste())) {
				sameTagDocs.add(d);
				if(!d.hasLiked(this)){	//like the document if they have not liked it before
					
					//follow people who like the document
					for(User u: d.getLikedUsers())
					{
						if(!u.equals(this))	//if that user is not this user
						{
							follow(u);
						}
					}
					
					d.likeDoc(this);
					
				}
				
				toprint += d.getName() + "(" + d.getTag() + ") ";
			}
		}
		
		System.out.println(toprint + "\n");
		
		
		return sameTagDocs;
	}
	/**
	 * Creates a new Document of the Producer's favourite taste and adds
	 * the document to an ArrayList of documents that it has produced.
	 */
	public void produce() {
		Document newlyProduced = new Document("" + super.getName() + "(Doc" +  produced.size() + ")", super.getTaste());
		produced.add(newlyProduced);
		super.sim.addDoc(newlyProduced);
		newlyProduced.likeDoc(this);
	}

	/**
	 * Ranks all of the documents and returns the top 10
	 * @param unrankedDocs is the list of unranked documents
	 * @return a list of ranked documents
	 */
	public ArrayList<Document> rank(List<Document> unrankedDocs) {
		ArrayList<Document> ranked = new ArrayList<Document>();
		Collections.sort(unrankedDocs);
		String toprint = "" + super.getName() + " ranked: ";
		
		
		//hard-coded limit of 10 for testing purposes
		for (int i = 0; i < 10; i++) {
			ranked.add(unrankedDocs.get(i));
			
		}
		
		for(Document d: ranked){
			toprint += d.getName() + "(liked: " + d.getLikes() + ") ";
		}
		System.out.println(toprint);
		return ranked;
	}

	/**
	 * Cycles through the list of ranked documents and every time it sees
	 * a document that matches the Producer's taste, and if the Producer hasn't liked it yet,
	 * increment a counter by 1.
	 * @param docs is a list of ranked documents
	 * @return the number of documents that appear in the ranked list that
	 * 			match the Producer's taste
	 */
	public int payoff(List<Document> docs) {
		
		int pointCounter = 0; //Keeps track of how many documents appear that match the Producer's taste
		
		for (Document d : docs) {
			if ((d.getTag().equals(super.getTaste())) && (!d.hasLiked(this))) {
				pointCounter++;
			}
		}
		System.out.println("" + super.getName() + " payoff: " + pointCounter + "");
		return pointCounter;
	}

}