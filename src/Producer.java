
/* SYSC3110 Software Design Project
 * Team redSquadron
 * Producer subclass
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
		 * goes through d
		 * d is every single document
		 * create new list sameTaste
		 * for every document in the d list, add all the matching tastes to sameTaste
		 * 									while liking that document
		 * return list of liked docs
		 * 
		 */
		
		for (Document d : allDoc) {
			if (d.getTag())
		}
	}

	public void produce() {
		Document newlyProduced = new Document("" + super.getName() + super.produced.getSize(), super.getTaste());
		produced.add(newlyProduced);
	}

	public ArrayList<Document> rank(List<Document> unrankedDocs) {
		ArrayList<Document> ranked = new ArrayList<Document>();
		for(int i=0; i<10; i++) {
			for (Document d : unrankedDocs) {
				d.getLikes();
				ranked.
			}
		}
		return ranked;
	}

	public int payoff(ArrayList<Document> d) {

	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if ((o == null) || (!(o instanceof Item))) {
			return false;
		}
		Item i = (Item) o;
		return (name.equals(i.name) && (stockCount == i.stockCount) && (price == i.price));
	}

}
