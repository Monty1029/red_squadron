//Name: Garrett Steele
//Date: Nov 18, 2015
//Class: SYSC3110 - Software Development Project
//Git Repository: redSquadron

	//////////////////////////////////////////////
	//											//
	//					Imports					//
	//											//
	//////////////////////////////////////////////
 

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class for the Producer class. Does 
 * @author Garrett Steele
 *
 */
public class ProducerTest {

	Simulation sim;				//a simulation to reference
	List<Document> docs;		//a list of pre-fab documents
	Strategy strat;				//a strategy to reference
	
	/**
	 * Method to create a simulation to reference and the list of documents to use.
	 */
	@Before
	public void setUp()
	{
		sim = new Simulation();								//create a simulation to reference
		docs = createDocList();								//create a list of pre-fab documents
		strat = new PopularityStrategy();					//create a strategy to reference
	}
	
	
	/**
	 * Unit test to ensure the act method returns as expected for a number of test cases. Also tests that the payoff of a producer is incremented when a new person likes their document
	 */
	@Test
	public void testActA() {
		
		List<Document> actedOn;												//create a list to hold the documents acted upon
		
		Producer producer1 = new Producer("producer1", "taste1",sim);		//create a producer
		producer1.setStrat(strat);
		assertEquals(0, producer1.getProduced().size());					//ensure they have made no documents
		actedOn = producer1.act(docs,10);									//request them to act
		
		//check that produce() works within the act() method
		assertEquals(1, producer1.getProduced().size());					//should have created and added their own document to their list of produced documents 
		assertEquals(1, producer1.getProduced().get(0).getLikes());			//should have liked their own document
		
		//check the returned list of documents
		assertEquals(1, actedOn.size());									//should be only one document in that list
		assertEquals(docs.get(0), actedOn.get(0));							//these should reference the same Document object
		assertEquals(0, producer1.getFollowed());							//the User should not be following itself, and there is no-one else to follow them
		assertEquals(0, producer1.getFollowing().size());					//nor does the document have any people who like it to follow
		assertEquals(10, producer1.getLastRanked().size());					//should have kept the top 10 documents in memory after "p1" acts, 
																				//only the size truly matters as an indication, otherwise it would be 0
		//do a null document test
		actedOn  = producer1.act(null, 0);
		assertEquals(0,producer1.getLastRanked().size());
		assertEquals(2,producer1.getCumulative());
		assertEquals(1,producer1.getPayoff());
		
		
	}
	

	/**
	 * Unit test of how a Producer performs under the B strategy for acting, does not have to test all the same things as testActA() because they share the same interior method calls.
	 */
	@Test
	public void testActB()
	{
		sim.selectTags(2);															//tell the simulation to select 2 tags, that way it can select a tag other than it's own
		Producer p1 = new Producer("name1", sim.getAvailableTags().get(0), sim);	//create a user to like the first tag of the simulation
		Producer p2 = new Producer("name2", sim.getAvailableTags().get(0), sim);	//create a user to like the second tag of the simulation
		Producer p3 = new Producer("name3", sim.getAvailableTags().get(0), sim);	//create a user to like the second tag of the simulation
		Producer pro = new Producer(null,null,sim);
		
		p1.setActStrategy(Producer.STRATEGY_B);										//set the strategy to "B" for "p1", "p3" is then still "A"
		p2.setActStrategy(Producer.STRATEGY_B);										//set the strategy to "B" for "p2" as well
		
		//need  to create a different list of documents for this test as they will be based on the tags from the simulation
		Document doc1 = new Document("doc1",sim.getAvailableTags().get(0), pro);
		Document doc2 = new Document("doc1",sim.getAvailableTags().get(0), pro);
		Document doc3 = new Document("doc1",sim.getAvailableTags().get(1), pro);
		Document doc4 = new Document("doc1",sim.getAvailableTags().get(1), pro);
		Document doc5 = new Document("doc1",sim.getAvailableTags().get(1), pro);
		
		//add them to the list
		List<Document> newDocs = new ArrayList<>();
		newDocs.add(doc1);
		newDocs.add(doc2);
		newDocs.add(doc3);
		newDocs.add(doc4);
		newDocs.add(doc5);
		
		assertNotEquals(sim.getAvailableTags().get(0), p1.act(newDocs, 5).get(0).getTag());	//the tags should be different in each list
		assertNotEquals(p2.act(newDocs, 5).size(), p3.act(newDocs, 5).size());				//the two act strategies should not give the same size of list in this case
		
		assertEquals(5, p1.getLastRanked().size());		//should have kept the top 5 documents in memory after "p1" acts, 
															//only the size truly matters as an indication, otherwise it would be 0
		
		
		
	}
	
	
	/**
	 * Tests whether the payoff works as expected for a Producer for pre-made lists of documents.
	 */
	@Test
	public void testPayoff() {
		
		//create some producers and consumers
		Producer producer1 = new Producer("producer1", "taste1",sim);
		Producer producer2 = new Producer("producer2", "taste2",sim);
		
		
		//calculate payoffs for the producers
		assertEquals(0, producer1.getPayoff());					//no produced documents liked
		assertEquals(0, producer1.getCumulative());				//no produced documents liked
		
		
		
		for(int i = 0; i < 3; i++){producer1.produce();}		//have producer1 generate 3 documents
		for(int i = 0; i < 2; i++){producer2.produce();}		//have producer1 generate 2 documents
		
		//calculate payoffs for the producers
		assertEquals(3, producer1.getCumulative());				//3 produced documents liked
		assertEquals(2, producer2.getCumulative());				//2 produced documents liked, and liked by 1 consumer
		
		
	}

	
	/**
	 * Tests whether the Producer constructor acts as expected. Does not need to do a null check as this is handled in the User constructor it calls,
	 *  and is tested in ConsumerTest.java where all it does is call the super constructor.
	 */
	@Test
	public void testProducer() {
		
		//create some consumers
		Producer producer1 = new Producer("producer1", "taste1",sim);
		Producer producer2 = new Producer("", "taste2",sim);
		Producer producer3 = new Producer("producer3", "",sim);
		Producer producer4 = new Producer("", "",sim);
				
		//check that the names are correct
		assertEquals("producer1", producer1.getName());
		assertEquals("", producer2.getName());
		assertEquals("producer3", producer3.getName());
		assertEquals("", producer4.getName());
				
		//check that the tastes are correct
		assertEquals("taste1",producer1.getTaste());
		assertEquals("taste2",producer2.getTaste());
		assertEquals("",producer3.getTaste());
		assertEquals("",producer4.getTaste());
		
		//make sure they all create a new list for the documents that they will fill in
		assertNotEquals(null, producer1.getProduced());
		assertNotEquals(null, producer2.getProduced());
		assertNotEquals(null, producer3.getProduced());
		assertNotEquals(null, producer4.getProduced());
				
	}

	
	/**
	 * Tests whether the produce method works as intended and creates a new document of the correct attributes, also tests getProduced() and incrementPayoff() as part of the process.
	 */
	@Test
	public void testProduce() {
		
		Producer producer1 = new Producer("producer1", "taste1",sim);	//create a producer to test
		
		assertEquals(0, producer1.getProduced().size());				//should have no created documents at this point
		producer1.produce();											//producer creates and likes their document
		
		assertEquals(1, producer1.getProduced().size());				//should have added their own document to their list of produced documents 
		assertEquals(1, producer1.getProduced().get(0).getLikes());		//should have liked their own document
		assertEquals(1, producer1.getPayoff());							//should have a previous payoff of 1
		assertEquals(1, producer1.getCumulative());						//should have an updated cumulative payoff of 1
			
	}

	

	/**
	 * Create a pre-fabricated list of documents to perform actions with.
	 * @return a prefab list of documents
	 */
	private List<Document> createDocList()
	{
		//create the list for documents to use
		List<Document> documents = new ArrayList<>();
		Producer pro = new Producer(null,null,sim);
		
		//create the documents
		documents.add(new Document("name1", "taste1", pro));
		documents.add(new Document("name2", "taste2", pro));
		documents.add(new Document("name3", "taste2", pro));
		documents.add(new Document("name4", "taste3", pro));
		documents.add(new Document("name5", "taste3", pro));
		documents.add(new Document("name6", "taste3", pro));
		documents.add(new Document("name7", "taste3", pro));
		documents.add(new Document("name8", "taste4", pro));
		documents.add(new Document("name9", "taste4", pro));
		documents.add(new Document("name10", "taste4", pro));
		
		return documents;

	}
	
}
