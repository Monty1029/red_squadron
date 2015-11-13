//Name: Garrett Steele
//Date: Nov 13, 2015
//Class: SYSC3110 - Software Development Project
//Git Repository: redSquadron

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to represent the Popularity ranking strategy
 * @author Garrett Steele
 *
 */
public class PopularityStrategy implements Strategy {

	@Override
	/**
	 * Re-factored from the original code written in consumer and Producer by Monty Dhanani, re-factoring to new method done by Garrett Steele
	 */
	public List<Document> rank(User u, List<Document> docs, int n) {
		
		int k = n;													//set number of iterations to n
		if (n > docs.size()){k = docs.size();}						//if n is too large for the document list, use the size of the document list
		
		ArrayList<Document> ranked = new ArrayList<Document>();		//new list of documents to return ranked
		Collections.sort(docs);										//the comparable implementation in Document allows this based on number of times the document is liked
		
		//retrieve top ranked documents that match the User's taste
		for (int i = 0; i < k; i++) {
			if(docs.get(i).getTag().equals(u.getTaste())){ranked.add(docs.get(i));}
		}

		return ranked;
	}
	

}
