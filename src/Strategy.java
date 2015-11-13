//Name: Garrett Steele
//Date: Nov 13, 2015
//Class: SYSC3110 - Software Development Project
//Git Repository: redSquadron


import java.util.List;

/**
 * Interface for classes that rank a given list of documents
 * @author Garrett Steele
 *
 */
public interface Strategy {

	/**
	 * Method to rank the list of documents to like and return to the method caller.
	 * @param u the user calling the method
	 * @param docs the list of documents to perform the ranking on
	 * @param n the number of top "n" documents to return
	 * @return the top "n" documents
	 */
	public List<Document> rank(User u, List<Document> docs, int n);
	
	
}
