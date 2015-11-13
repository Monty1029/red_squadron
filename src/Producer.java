/* SYSC3110 Software Design Project
 * Team redSquadron
 * Producer subclass by Monty Dhanani
 */

import java.util.*;

/**
 * Sub-class of a User that creates Documents and likes them as soon as they are created, can also like and rank other document
 * @author Monty Dhanani, Garrett Steele (Class re-factoring and additions from Milestones 1-2)
 *
 */
public class Producer extends User {

	//public boolean values to apply to one of these objects
	public static final int STRATEGY_A = 1;
	public static final int STRATEGY_B = 2;
	
	//private variables for each object
	private List<Document> produced;
	private int actStrategy;

	/**
	 * Creates a new Producer object
	 * @param username name of user
	 * @param taste preferred taste of user
	 * @param sim the simulation
	 */
	public Producer(String username, String taste, Simulation sim) {
		super(username, taste, sim);
		produced = new ArrayList<Document>();		//blank list of produced documents
		actStrategy = STRATEGY_A;					//default action strategy
	}
	
	/**
	 * Fetch the number of produced documents.
	 * Method added Nov 3, 2015 by Garrett Steele during unit testing
	 * @return the number of documents produced by the producer
	 */
	public List<Document> getProduced(){return produced;}
	
	/**
	 * Select the acting strategy to use, but only allow the change to be a valid one
	 * @param n the integer of Producer.STRATEGY_A or Producer.STRATEGY_B
	 */
	public void setActStrategy(int n)
	{
		if(n == STRATEGY_A){actStrategy = STRATEGY_A;}
		else if(n == STRATEGY_B){actStrategy = STRATEGY_B;}
	}
	
	
	
	/**
	 * Cycles through all the existing documents and returns an ArrayList
	 * of Documents with the same taste as the Consumer.
	 * Producer also likes every Document it creates
	 * Method modified by Garrett Steele on Nov 5th, 2015 to reduce duplicate code
	 * @param allDocs list of all existing documents
	 * @param n the number of top documents to rank
	 */
	public List<Document> act(List<Document> allDocs, int n) {

		produce();												//make a doc
		String tempTaste;										//a variable to keep track of a producer's original taste
		int rand;												//random number to pick another taste to use
		Random randomizer = new Random();						//random number generator
		tempTaste = taste;										//store the actual taste
		
		
		//if the strategy is "B", need to change the taste used
		if(actStrategy == STRATEGY_B)
		{
			
			//need to pick a different taste that is not that of the original taste
			while(true)
			{
				rand = randomizer.nextInt(sim.getAvailableTags().size());		//get a random tag
				if(!sim.getAvailableTags().get(rand).equals(taste))				//if that tag is not the same as the current taste it will be used
				{
					taste = sim.getAvailableTags().get(rand);					//set the intermediate taste
					break;														//stop looping forever
				}
			}
		}
		
		
		//need to do the following actions no matter the action strategy selected for the Producer
		lastRanked = strat.rank(this, allDocs, n);					//rank the document and store the result
		int payoff = this.payoff(lastRanked);						//do payoff work
		List<Document> toReturn = matchTaste(lastRanked);			//get the list to return to the Simulation
		follow(lastRanked);											//follow them
		taste = tempTaste;											//reset the Producer's taste
		
		return toReturn;
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

	/*/**
	 * Ranks all of the documents and returns the top 10
	 * @param unrankedDocs is the list of unranked documents
	 * @return a list of ranked documents
	 */
	/*public ArrayList<Document> rank(List<Document> unrankedDocs) {
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
	}*/

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
		cumulative += pointCounter;
		payoff = pointCounter;
		return pointCounter;
	}

}