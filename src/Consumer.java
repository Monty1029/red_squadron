
/* SYSC3110 Software Design Project
 * Team redSquadron
 * Consumer subclass by Monty Dhanani
 */

import java.util.*;

public class Consumer extends User {
	
	public Consumer(String username, String taste) {
		super(String username, String taste);

	}

	public ArrayList<Document> act(ArrayList<Document> d) {
		ArrayList<Document> sameTagDocs = new ArrayList<Document>();

		for (Document d : allDoc) {
			if (d.getTag().equals(super.getTaste())) {
				sameTagDocs.add(d);
			}
		}
		return sameTagDocs;
	}

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

	public int payoff(ArrayList<Document> d) {
		return 0;
	}

}
