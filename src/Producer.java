
/* SYSC3110 Software Design Project
 * Team redSquadron
 * Producer subclass by Monty Dhanani
 */

import java.util.*;

public class Producer extends User {

	private List<Document> produced;

	public Producer(String username, String taste) {
		super(String username, String taste);
		produced = new ArrayList<Document>();
	}

	public ArrayList<Document> act(ArrayList<Document> allDocs) {
		/*
		 * goes through d d is every single document create new list sameTaste
		 * for every document in the d list, add all the matching tastes to
		 * sameTaste while liking that document return list of liked docs
		 * 
		 */

		ArrayList<Document> sameTagDocs = new ArrayList<Document>();

		for (Document d : allDoc) {
			if (d.getTag().equals(super.getTaste())) {
				sameTagDocs.add(d);
				d.likeDoc();
			}
		}
		return sameTagDocs;
	}

	public void produce() {
		Document newlyProduced = new Document("" + super.getName() + super.produced.getSize(), super.getTaste());
		produced.add(newlyProduced);
	}

	public ArrayList<Document> rank(List<Document> unrankedDocs) {
		ArrayList<Document> ranked = new ArrayList<Document>();
		Collections.sort(unrankedDocs.getLikes());
		Collections.reverseOrder(unrankedDocs);
		for (int i = 0; i < 10; i++) {
			ranked.add(unrankedDocs.get(i));
		}
		return ranked;
	}

	public int payoff(ArrayList<Document> docs) {
		/*
		 * cycle through the list of ranked documents and everytime it sees a document that matches
		 * 						my taste and i haven't liked it, increment a counter by 1
		 * 	return counter value
		 */
		
		/*int pointCounter = 0;
		
		for (Document d : docs) {
			if ((d.getTag().equals(super.getTaste())) && 
		}*/
		return 0;
	}

}
