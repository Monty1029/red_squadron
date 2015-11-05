/* SYSC3110 Software Design Project
 * Team redSquadron
 * Producer subclass by Monty Dhanani
 */

import java.util.*;

/**
 * Sub-class of a User that creates Documents and likes them as soon as they are created, can also like and rank other document
 * @author Monty Dhanani, Contributors: Garrett Steele (method refactoring where stated)
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
	 * Fetch the number of produced documents.
	 * Method added Nov 3, 2015 by Garrett Steele during unit testing
	 * @return the number of documents produced by the producer
	 */
	public List<Document> getProduced(){return produced;}
	

	/**
	 * Cycles through all the existing documents and returns an ArrayList
	 * of Documents with the same taste as the Consumer.
	 * Producer also likes every Document it creates
	 * Method modified by Garrett Steele on Nov 5th, 2015 to reduce duplicate code
	 * @param allDocs list of all existing documents
	 */
	public List<Document> act(List<Document> allDocs) {

		produce();									//make a doc
		ArrayList<Document> ranked = rank(allDocs);
		int payoff = this.payoff(ranked);	//rank them and get payoff
		
		
		return basicAct(allDocs);
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
	 * Calculates a payoff based on followers and documents liked that were produced
	 * @param docs is a list of ranked documents
	 * @return the number of documents that appear in the ranked list that
	 * 			match the Producer's taste
	 */
	public int payoff(List<Document> docs) {
		
		int pointCounter = super.followed; //keeps track of how many likes there are in the produced documents
		
		for (Document d : produced) {
				pointCounter+= d.getLikes();
		}
		System.out.println("" + super.getName() + " payoff: " + pointCounter + "");
		return pointCounter;
	}

}