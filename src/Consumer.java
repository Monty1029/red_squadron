/* SYSC3110 Software Design Project
 * Team redSquadron
 * Consumer subclass by Monty Dhanani
 * re-factored by Garrett Steele for Milestone 3
 */

import java.util.*;

/**
 * Sub-class of a User that likes and ranks other documents
 * @author Monty Dhanani, Garrett Steele (Class refactoring from Milestones 1-2)
 * 
 *
 */
public class Consumer extends User {
	
	/**
	 * Creates a Consumer object
	 * @param username of the Consumer
	 * @param taste that the Consumer likes the most
	 * @param sim reference to the Simulation class
	 */
	public Consumer(String username, String taste, Simulation sim) {
		super(username, taste, sim);
	}

	/**
	 * Cycles through all the existing documents and returns an ArrayList
	 * of Documents with the same taste as the Consumer.
	 * Method modified by Garrett Steele on Nov 5th, 2015 to reduce duplicate code
	 * @param allDocs list of all existing documents
	 * @param n number of documents to return ranked
	 */
	public List<Document> act(List<Document> allDocs, int n) {

		List<Document> ranked = strat.rank(this, allDocs, n);	//rank them
		int payoff = this.payoff(ranked);						//do payoff work
		follow(ranked);											//follow them
		
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
	/*NOTE: For now, payoff method in both Consumer and Producer class are copy & pasted code,
	but they will be different in Milestone 2
	*/
	public int payoff(List<Document> docs) {
		int pointCounter = 0;

		for (Document d : docs) {
			if ((d.getTag().equals(super.getTaste())) && (!d.hasLiked(this))) {
				pointCounter++;
			}
		}
		System.out.println("" + super.getName() + " payoff: " + pointCounter + "");
		cumulative += pointCounter;
		payoff = pointCounter;
		return pointCounter;
	}

}