//Name: Garrett Steele
//Date: Nov 5, 2015
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
 * JUnit test class for the Producer class.
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
	 * Unit test to ensure the act method returns as expected for a number of test cases.
	 */
	@Test
	public void testAct() {
		
		List<Document> actedOn;												//create a list to hold the documents acted upon
		
		Producer producer1 = new Producer("producer1", "taste1",sim);		//create a producer
		producer1.setStrat(strat);
		assertEquals(0, producer1.getProduced().size());					//ensure they have made no documents
		actedOn = producer1.act(docs,10);										//request them to act
		
		//check that produce() works within the act() method
		assertEquals(1, producer1.getProduced().size());					//should have created and added their own document to their list of produced documents 
		assertEquals(1, producer1.getProduced().get(0).getLikes());			//should have liked their own document
		
		//check the returned list of documents
		assertEquals(1, actedOn.size());									//should be only one document in that list
		assertEquals(docs.get(0), actedOn.get(0));							//these should reference the same Document object
		assertEquals(0, producer1.getFollowed());							//the User should not be following itself, and there is no-one else to follow them
		assertEquals(0, producer1.getFollowing().size());					//nor does the document have any people who like it to follow
		
	}

	
	/**
	 * Tests whether the payoff works as expected for a consumer for pre-made lists of documents.
	 */
	@Test
	public void testPayoff() {
		
		//create some producers
		Producer producer1 = new Producer("producer1", "taste1",sim);
		Producer producer2 = new Producer("producer2", "taste2",sim);
		
		//create some consumers to follow the producers to adjust the payoff expected value
		Consumer consumer1 = new Consumer("consumer1", "taste1", sim);
		Consumer consumer2 = new Consumer("consumer2", "taste2", sim);
		
		//have both consumers follow producer1
		consumer1.follow(producer1);
		consumer2.follow(producer1);
		
		//for the following steps no list of type Document actually needs to be passed based on how producers calculate payoff
		
		//calculate payoffs for the producers
		assertEquals(2, producer1.payoff(null));				//followed by 2 people, no produced documents liked
		assertEquals(0, producer2.payoff(null));				//followed by no people, no produced documents liked
		
		for(int i = 0; i < 3; i++){producer1.produce();}		//have producer1 generate 3 documents
		for(int i = 0; i < 2; i++){producer2.produce();}		//have producer1 generate 2 documents
		
		//calculate payoffs for the producers
		assertEquals(5, producer1.payoff(null));				//followed by 2 people, 3 produced documents liked
		assertEquals(2, producer2.payoff(null));				//followed by no people, 2 produced documents liked
		
		
	}

	
	/**
	 * Tests whether the Consumer constructor acts as expected.
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
	 * Tests whether the produce method works as intended and creates a new document of the correct attributes, also tests getProduced() as part of the process.
	 */
	@Test
	public void testProduce() {
		
		Producer producer1 = new Producer("producer1", "taste1",sim);	//create a producer to test
		
		assertEquals(0, producer1.getProduced().size());				//should have no created documents at this point
		producer1.produce();											//producer creates and likes their document
		
		assertEquals(1, producer1.getProduced().size());				//should have added their own document to their list of produced documents 
		assertEquals(1, producer1.getProduced().get(0).getLikes());		//should have liked their own document
			
	}

	

	/**
	 * Create a pre-fabricated list of documents to perform actions with.
	 * @return a prefab list of documents
	 */
	private List<Document> createDocList()
	{
		//create the list for documents to use
		List<Document> documents = new ArrayList<>();
		
		//create the documents
		documents.add(new Document("name1", "taste1"));
		documents.add(new Document("name2", "taste2"));
		documents.add(new Document("name3", "taste2"));
		documents.add(new Document("name4", "taste3"));
		documents.add(new Document("name5", "taste3"));
		documents.add(new Document("name6", "taste3"));
		documents.add(new Document("name7", "taste3"));
		documents.add(new Document("name8", "taste4"));
		documents.add(new Document("name9", "taste4"));
		documents.add(new Document("name10", "taste4"));
		
		return documents;

	}
	
}
