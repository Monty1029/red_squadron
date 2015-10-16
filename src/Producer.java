
/* SYSC3110 Software Design Project
 * Team redSquadron
 * Producer subclass by Monty Dhanani
 */

import java.util.*;

public class Producer extends User {

	private List<Document> produced;

	public Producer(String username, String taste, Simulation sim) {
		super(username, taste, sim);
		produced = new ArrayList<Document>();
	}

	/**
	 * Cycles through all the existing documents and returns an ArrayList
	 * of Documents with the same taste as the Consumer.
	 * Producer also likes every Document it creates
	 */
	public ArrayList<Document> act(ArrayList<Document> allDocs) {
		ArrayList<Document> sameTagDocs = new ArrayList<Document>();

		for (Document d : allDoc) {
			if (d.getTag().equals(super.getTaste())) {
				sameTagDocs.add(d);
				d.likeDoc();
			}
		}
		return sameTagDocs;
	}
	/**
	 * Creates a new Document of the Producer's favourite taste and adds
	 * the document to an ArrayList of documents that it has produced.
	 */
	public void produce() {
		Document newlyProduced = new Document("" + super.getName() + super.produced.getSize(), super.getTaste());
		produced.add(newlyProduced);
	}

	/**
	 * Ranks all of the documents and returns the top 10
	 * @param unrankedDocs is the list of unranked documents
	 * @return a list of ranked documents
	 */
	public ArrayList<Document> rank(List<Document> unrankedDocs) {
		ArrayList<Document> ranked = new ArrayList<Document>();
		Collections.sort(unrankedDocs.getLikes());
		Collections.reverseOrder(unrankedDocs);
		for (int i = 0; i < 10; i++) {
			ranked.add(unrankedDocs.get(i));
		}
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
	public int payoff(ArrayList<Document> docs) {
		
		int pointCounter = 0; //Keeps track of how many documents appear that match the Producer's taste
		
		for (Document d : docs) {
			if ((d.getTag().equals(super.getTaste())) && (super.hasLiked())) {
				pointCounter++;
			}
		}
		return pointerCounter;
	}

}
