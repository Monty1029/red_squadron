
/* SYSC3110 Software Design Project
 * Team redSquadron
 * Consumer subclass by Monty Dhanani
 */

import java.util.*;

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
	 * of Documents with the same taste as the Consumer
	 */
	public ArrayList<Document> act(ArrayList<Document> d) {
		ArrayList<Document> sameTagDocs = new ArrayList<Document>();

		for (Document d : allDoc) {
			if (d.getTag().equals(super.getTaste())) {
				sameTagDocs.add(d);
			}
		}
		return sameTagDocs;
	}

	/**
	 * Ranks all of the documents and returns the top 10
	 * @param unrankedDocs is the list of unranked documents to rank
	 * @return a list of ranked documents
	 */
	public ArrayList<Document> popularityRank(List<Document> unrankedDocs) {
		ArrayList<Document> ranked = new ArrayList<Document>();
		Collections.sort(unrankedDocs.getLikes());
		Collections.reverseOrder(unrankedDocs);
		for (int i = 0; i < 10; i++) {
			ranked.add(unrankedDocs.get(i));
		}
		return ranked;
	}

	/*
	 * public ArrayList<Document> followedRank(List<Document> docs) { for
	 * milestone 2 }
	 */

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
	public int payoff(ArrayList<Document> d) {
		int pointCounter = 0;

		for (Document d : docs) {
			if ((d.getTag().equals(super.getTaste())) && (super.hasLiked())) {
				pointCounter++;
			}
		}
		return pointerCounter;
	}

}
