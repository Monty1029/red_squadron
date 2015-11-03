//Name: Garrett Steele
//Date: Nov 3, 2015
//Class: SYSC3110 - Software Development Project
//Git Repository: redSquadron

	//////////////////////////////////////////////
	//											//
	//					Imports					//
	//											//
	//////////////////////////////////////////////


import static org.junit.Assert.*;
import org.junit.Test;



/**
 * JUnit test class for User objects.
 * @author Garrett Steele
 *
 */
public class UserTest {

	//This class does not test it's own constructors as it is an abstract class and can not be instantiated

	@Test
	/**
	 * Tests whether a User successfully follows another user only once, this also tests getFollowing() and getFollowed() as part of the process.
	 * This also checks that the user in question was followed.
	 * As one cannot instantiate a User, this test is performed on a Consumer, which has no additions from the User class
	 */
	public void testFollow() {
		
		//create a simulation to reference
		Simulation sim = new Simulation();
		
		//create the consumers to test with
		Consumer consumer1 = new Consumer("Consumer 1", "Taste 1", sim);	//will be followed by 0 people
		Consumer consumer2 = new Consumer("Consumer 2", "Taste 1", sim);	//will be followed by the other 4 people
		Consumer consumer3 = new Consumer("Consumer 3", "Taste 1", sim);	//will be followed by 2 other people, but those people shall try to follow that person twice
		Consumer consumer4 = new Consumer("Consumer 4", "Taste 1", sim);	//followed by 1 person
		Consumer consumer5 = new Consumer("Consumer 5", "Taste 1", sim);	//followed by 3 people
		
		//do the following for consumer 2
		consumer1.follow(consumer2);
		consumer3.follow(consumer2);
		consumer4.follow(consumer2);
		consumer5.follow(consumer2);
		
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
		assertEquals(4,consumer2.getFollowed());					//consumer2 is followed 4
		assertEquals(2,consumer3.getFollowed());					//consumer3 is followed 2
		assertEquals(1,consumer4.getFollowed());					//consumer4 is followed 1
		assertEquals(3,consumer5.getFollowed());					//consumer5 is followed 3
		
	}


	
	@Test
	/**
	 * Tests whether a user liking a document is implemented correctly.
	 * As one cannot instantiate a User, this test is performed on a Consumer, which has no additions from the User class
	 */
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
		assertEquals(false, consumer.hasLikedAny());		//it should have like a document at this point
		
	}

}
