//Name: Garrett Steele
//Date: Nov 13, 2015
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

import org.junit.Test;


/**
 * JUnit test class for the User class.
 * @author Garrett Steele
 *
 */
public class UserTest {

	//This class does not test it's own constructors as it is an abstract class and can not be instantiated

	
	public void testSetStrat()
	{
		PopularityStrategy strat = new PopularityStrategy();						//create a new strategy
		Consumer consumer1 = new Consumer("name1","taste1",new Simulation());		//create consumer
		consumer1.setStrat(strat);													//apply strategy to the consumer
		assertEquals(strat, consumer1.strat);										//ensure the strategy was set
	}
	
	
	/**
	 * Tests whether a User successfully follows another user only once, this also tests getFollowing() and getFollowed() as part of the process.
	 * This also checks that the user in question was followed.
	 * As one cannot instantiate a User, this test is performed on a Consumer, which has no additions from the User class
	 */
	@Test
	public void testFollowUser() {
		
		//create a simulation to reference
		Simulation sim = new Simulation();
		
		//create the consumers to test with, could not pre-create these with "@Before" due to changes in each test case
		Consumer consumer1 = new Consumer("Consumer 1", "Taste 1", sim);	//will be followed by 0 people
		Consumer consumer2 = new Consumer("Consumer 2", "Taste 1", sim);	//will be followed by the other 3 people
		Consumer consumer3 = new Consumer("Consumer 3", "Taste 1", sim);	//will be followed by 2 other people, but those people shall try to follow that person twice
		Consumer consumer4 = new Consumer("Consumer 4", "Taste 1", sim);	//followed by 1 person
		Consumer consumer5 = new Consumer("Consumer 5", "Taste 1", sim);	//followed by 3 people
		
		//do the following for consumer 2
		consumer1.follow(consumer2);
		consumer3.follow(consumer2);
		consumer4.follow(consumer2);
		
		//do the following for consumer 3
		consumer1.follow(consumer3);
		consumer1.follow(consumer3);
		consumer2.follow(consumer3);
		consumer2.follow(consumer3);
		
		//do the following for consumer 4
		consumer1.follow(consumer4);
		
		
		//do the following for consumer 5
		consumer1.follow(consumer5);
		consumer2.follow(consumer5);
		consumer3.follow(consumer5);
		
		//check number of people each consumer is following
		assertEquals(4,consumer1.getFollowing().size());			//consumer1 follows 4
		assertEquals(2,consumer2.getFollowing().size());			//consumer1 follows 2
		assertEquals(2,consumer3.getFollowing().size());			//consumer1 follows 2
		assertEquals(1,consumer4.getFollowing().size());			//consumer1 follows 1
		assertEquals(0,consumer5.getFollowing().size());			//consumer1 follows 0
		
		//check the number of times each consumer is followed
		assertEquals(0,consumer1.getFollowed());					//consumer1 is followed 0
		assertEquals(3,consumer2.getFollowed());					//consumer2 is followed 3
		assertEquals(2,consumer3.getFollowed());					//consumer3 is followed 2
		assertEquals(1,consumer4.getFollowed());					//consumer4 is followed 1
		assertEquals(3,consumer5.getFollowed());					//consumer5 is followed 3
		
	}

	/**
	 * Tests whether the Users that like a list of documents will be followed only once by a user
	 */
	public void testFollowDocuments(){
		
		//create a simulation to reference
		Simulation sim = new Simulation();
		
		//create the consumers to test with, could not pre-create these with "@Before" due to changes in each test case
		Consumer consumer1 = new Consumer("Consumer 1", "taste1", sim);
		Consumer consumer2 = new Consumer("Consumer 2", "taste1", sim);
		Consumer consumer3 = new Consumer("Consumer 3", "taste1", sim);
		Consumer consumer4 = new Consumer("Consumer 4", "taste1", sim);
		Consumer consumer5 = new Consumer("Consumer 5", "taste1", sim);
		
		//create the documents to test with
		Document doc1 = new Document("doc1", "taste1");		//liked by no-one
		Document doc2 = new Document("doc2", "taste1");		//liked by 1 person
		Document doc3 = new Document("doc3", "taste1");		//liked by 5 people
		
		//cause the different documents to be liked by varying number of people
		doc2.likeDoc(consumer2);
		doc3.likeDoc(consumer1);
		doc3.likeDoc(consumer2);
		doc3.likeDoc(consumer3);
		doc3.likeDoc(consumer4);
		doc3.likeDoc(consumer5);
		
		//create the Lists of type document
		List<Document> list1 = new ArrayList<Document>();		//empty list
		List<Document> list2 = new ArrayList<Document>();		//consists only of doc1
		list2.add(doc1);
		List<Document> list3 = new ArrayList<Document>();		//consists of all docs
		list3.add(doc1);
		list3.add(doc2);
		list3.add(doc3);
		
		consumer1.follow(list1);
		assertEquals(0,consumer1.getFollowing().size());		//consumer 1 should be following no-one
		consumer1.follow(list2);
		assertEquals(1,consumer1.getFollowing().size());		//consumer1 should now be following 1 person
		consumer1.follow(list3);
		assertEquals(4, consumer1.getFollowing().size());		//consumer1 should now follow 4 people, not itself, and should not have followed anyone again to make it 4
		
	}
	
	
	
	
	/**
	 * Tests whether a user liking a document is implemented correctly.
	 * As one cannot instantiate a User, this test is performed on a Consumer, which has no additions from the User class
	 */
	@Test
	public void testHasLikedAny() {
		
		//create a simulation to reference
		Simulation sim = new Simulation();
		
		 
		Document doc = new Document("name1", "taste1");					//create a document to add to the simulation with the consumer
		Consumer consumer = new Consumer("consumer", "taste1", sim);	//create a user to like the document
		
		//check if the user has liked any document
		assertEquals(false, consumer.hasLikedAny());		//it should not have at this point, so false
		
		//like the document in the simulation
		sim.addLike(consumer, doc);
		
		//check if the user has liked any document
		assertEquals(true, consumer.hasLikedAny());		//it should have like a document at this point
		
	}

}
