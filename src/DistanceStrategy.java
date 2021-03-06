//Name: Evan Bottomley
//Date: Nov 19, 2015
//Class: SYSC3110 - Software Development Project
//Git Repository: redSquadron

import java.io.Serializable;
import java.util.*;



/**
 * Class to represent the Distance ranking strategy
 * @author Evan Bottomley
 *
 */
public class DistanceStrategy implements Strategy,Serializable {

	private static final int bonus1 = 5;
	private static final int bonus2 = 2;
	private static final int bonus3 = 1;
	
	
	/**
	 * Rank documents in provided list according to distance of users who liked them from the user ranking the documents
	 */
	@Override
	public List<Document> rank(User u, List<Document> docs, int n) {
		ArrayList<Document> list = (ArrayList<Document>) docs;
		ArrayList<Integer> values = new ArrayList<Integer>();
		int total;
		boolean found;
		//Assign values to each document based on distance of those that liked it
		for (Document d: list) {
			
			total = 0;
			
			for (User liked: d.getLikedUsers()) {
				if (u.getFollowing().contains(liked)) {
					total += bonus1;
				}
				else {
					found = false;
					for (User liked2: u.getFollowing()) {
						if (liked2.getFollowing().contains(liked) && !found) {
							total += bonus2;
							found = true;
						}
					}
					if (!found) total += bonus3;
				}
			}
			values.add(total);
		}
		//Use bubble sort to rearrange arraylists of values and documents
		int temp;
		Document tempDoc;
		for(int i=0; i < list.size(); i++){
            for(int j=1; j < (list.size()-i); j++){
            	if(values.get(j-1) < values.get(j)){
            		//swap the elements!
                    temp = values.get(j-1);
                    values.set((j-1), values.get(j));
                    values.set(j,temp);
                    tempDoc = list.get(j-1);
                    list.set((j-1), list.get(j));
                    list.set(j,tempDoc);
            	}
                   
            }
		}
		int k = n;													//set number of iterations to n
		if (n > list.size()) k = list.size();						//if n is too large for the document list, use the size of the document list

		return list.subList(0,k);
	}

	/**
	 * Overwrite, gives strategy name
	 */
	public String toString(){ return "Distance Strategy";}
	

}