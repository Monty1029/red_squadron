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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class ConsumerTest {

	@Test
	/**
	 * Unit test to ensure the act method returns as expected for a number of test cases.
	 * This will be the documents in the list that match the Consumer's taste
	 */
	public void testAct() {
		
		
		Simulation sim = new Simulation();									//create a simulation to reference
		List<Document> docs = createDocList();								//create a list of pre-fab documents
		
		//create 4 consumers to test a variety of cases for the return
		Consumer consumer1 = new Consumer("consumer1", "taste1",sim);
		Consumer consumer2 = new Consumer("consumer2", "taste2",sim);
		Consumer consumer3 = new Consumer("consumer3", "taste3",sim);
		Consumer consumer4 = new Consumer("consumer4", "taste5",sim);		//no documents will match this taste
		
		
		//act for each consumer
		List<Document> list1 = consumer1.act(docs);				//size 1
		List<Document> list2 = consumer2.act(docs);				//size 2
		List<Document> list3 = consumer3.act(docs);				//size 4
		List<Document> list4 = consumer4.act(docs);				//no documents, size 0
		
		//check list 1
		assertEquals(1, list1.size());							//size is 1
		assertEquals(true, list1.contains(docs.get(0)));		//it contains the first element
		
		//check list 2
		assertEquals(2, list2.size());							//size is 2
		assertEquals(true, list2.contains(docs.get(1)));		//it contains the second element
		assertEquals(true, list2.contains(docs.get(2)));		//it contains the third element
		
		//check list 3
		assertEquals(4, list3.size());							//size is 4
		assertEquals(true, list3.contains(docs.get(3)));		//it contains the fourth element
		assertEquals(true, list3.contains(docs.get(4)));		//it contains the fifth element
		assertEquals(true, list3.contains(docs.get(5)));		//it contains the sixth element
		assertEquals(true, list3.contains(docs.get(6)));		//it contains the seventh element
		
		//check that list 4 has size 0
		assertEquals(0, list4.size());
		
	}

	@Test
	/**
	 * Tests whether the payoff works as expected for a consumer for pre-made lists of documents.
	 */
	public void testPayoff() {
		
		Simulation sim = new Simulation();									//create a simulation to reference
		List<Document> docs = createDocList();								//create a list of pre-fab documents
		
		//create 4 consumers to test a variety of cases for the return
		Consumer consumer1 = new Consumer("consumer1", "taste1",sim);
		Consumer consumer2 = new Consumer("consumer2", "taste2",sim);
		Consumer consumer3 = new Consumer("consumer3", "taste3",sim);
		Consumer consumer4 = new Consumer("consumer4", "taste5",sim);		//no documents will match this taste
		
		assertEquals(1, consumer1.payoff(docs));								//payoff is 1
		assertEquals(2, consumer2.payoff(docs));								//payoff is 2
		assertEquals(4, consumer3.payoff(docs));								//payoff is 4
		assertEquals(0, consumer4.payoff(docs));								//payoff is 0
		
		
	}

	@Test
	/**
	 * Tests whether the Consumer constructor acts as expected. Tests the getName() and getTaste() methods of User at the same time
	 */
	public void testConsumer() {
		
		//create a simulation to reference
		Simulation sim = new Simulation();									
		
		//create some consumers
		Consumer consumer1 = new Consumer("consumer1", "taste1",sim);
		Consumer consumer2 = new Consumer("", "taste2",sim);
		Consumer consumer3 = new Consumer("consumer3", "",sim);
		Consumer consumer4 = new Consumer("", "",sim);
		
		//check that the names are correct
		assertEquals("consumer1", consumer1.getName());
		assertEquals("", consumer2.getName());
		assertEquals("consumer3", consumer3.getName());
		assertEquals("", consumer4.getName());
		
		//check that the tastes are correct
		assertEquals("taste1",consumer1.getTaste());
		assertEquals("taste2",consumer2.getTaste());
		assertEquals("",consumer3.getTaste());
		assertEquals("",consumer4.getTaste());
		
	}

	@Test
	/**
	 * Tests whether the popularity ranking acts as intended for a User on a list of pre-made documents.
	 */
	public void testPopularityRank() {
		
		Simulation sim = new Simulation();									//create a simulation to reference
		List<Document> docs = createDocList();								//create a list of pre-fab documents, there are 10 in this list
		List<Document> ranked;
		
		//create 4 consumers to test a variety of cases for the return
		Consumer consumer1 = new Consumer("consumer1", "taste1",sim);
		Consumer consumer2 = new Consumer("consumer2", "taste2",sim);
		Consumer consumer3 = new Consumer("consumer3", "taste3",sim);
		Consumer consumer4 = new Consumer("consumer4", "taste4",sim);
		
		//any User can like any document regardless of their taste and the tag of the document, this is used in the next steps
		
		//////////////////////////////////////////////////////////////
		//						Ranking test 1						//
		//////////////////////////////////////////////////////////////
		//7th document on will have no likes
		
		//1st document has 4 likes, this will be the top ranked document
		docs.get(0).likeDoc(consumer1);
		docs.get(0).likeDoc(consumer2);
		docs.get(0).likeDoc(consumer3);
		docs.get(0).likeDoc(consumer4);
		
		//2nd, 3rd, and 4th have 3 likes
		docs.get(1).likeDoc(consumer1);			//2nd document
		docs.get(1).likeDoc(consumer2);
		docs.get(1).likeDoc(consumer3);
		docs.get(2).likeDoc(consumer1);			//3rd document
		docs.get(2).likeDoc(consumer2);
		docs.get(2).likeDoc(consumer3);
		docs.get(3).likeDoc(consumer1);			//4th document
		docs.get(3).likeDoc(consumer2);
		docs.get(3).likeDoc(consumer3);
		
		//5th has 2 likes
		docs.get(4).likeDoc(consumer1);
		docs.get(4).likeDoc(consumer2);
		
		//6th has 1 like
		docs.get(5).likeDoc(consumer1);
		
		
		ranked = consumer1.popularityRank(docs);			//rank the documents
		assertEquals(4, ranked.get(0).getLikes());			//1st position is liked 4 times
		assertEquals(3, ranked.get(1).getLikes());			//2nd position has 3 likes
		assertEquals(3, ranked.get(3).getLikes());			//4th position has 3 likes
		assertEquals(2, ranked.get(4).getLikes());			//5th has 2 likes
		assertEquals(1, ranked.get(5).getLikes());			//6th has 1 like
		assertEquals(0, ranked.get(6).getLikes());			//7th has no likes
		assertEquals(0, ranked.get(9).getLikes());			//10th has no likes
		
		
		//////////////////////////////////////////////////////////////
		//						Ranking test 2						//
		//////////////////////////////////////////////////////////////
		//all document have no likes
		
		docs = createDocList();							//reset document list
		ranked = consumer1.popularityRank(docs);		//rank the documents
		
		assertEquals(0, ranked.get(0).getLikes());		//1st position has no likes
		assertEquals(0, ranked.get(4).getLikes());		//5th position has no likes
		assertEquals(0, ranked.get(9).getLikes());		//10th position has no likes
		
		
	}
	
	
	/**
	 * Create a pre-fabricated list of documents to perform actions with.
	 * @return a prefab list of documents
	 */
	private List<Document> createDocList()
	{
		//create the list for documents to use.
		List<Document> docs = new ArrayList<>();
		
		//create the documents
		docs.add(new Document("name1", "taste1"));
		docs.add(new Document("name2", "taste2"));
		docs.add(new Document("name3", "taste2"));
		docs.add(new Document("name4", "taste3"));
		docs.add(new Document("name5", "taste3"));
		docs.add(new Document("name6", "taste3"));
		docs.add(new Document("name7", "taste3"));
		docs.add(new Document("name8", "taste4"));
		docs.add(new Document("name9", "taste4"));
		docs.add(new Document("name10", "taste4"));
		
		
		return docs;
	}
	
	
	

}
