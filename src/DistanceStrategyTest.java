import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class
 * @author Evan Bottomley
 *
 */
public class DistanceStrategyTest {


	@Test
	/**
	 * Test that the ranking strategy works as planned for a User, list of documents, and number of documents to return
	 */
	public void testRank() {
		
		
		Simulation sim = new Simulation();	//create Simulation to reference				
		DistanceStrategy strat = new DistanceStrategy();
		Producer pro = new Producer("name1", "taste1", sim);
		
		//create the Consumers to test with
		Consumer consumer1 = new Consumer("Consumer 1", "taste1", sim); //Ranking user
		Consumer consumer2 = new Consumer("Consumer 2", "taste1", sim); //Friend
		Consumer consumer3 = new Consumer("Consumer 3", "taste1", sim); //Friend of friend
		Consumer consumer4 = new Consumer("Consumer 4", "taste1", sim); //Friend of friend
		Consumer consumer5 = new Consumer("Consumer 5", "taste1", sim); //Unrelated
		Consumer consumer6 = new Consumer("Consumer 6", "taste1", sim); //Unrelated
		Consumer consumer7 = new Consumer("Consumer 7", "taste1", sim); //Unrelated
		
		//Get Consumer 1 to follow 2, consumer 2 to follow 3 and 4
		consumer1.follow(consumer2);
		consumer2.follow(consumer3);
		consumer2.follow(consumer4);
		
		//create 5 documents
		Document doc1 = new Document("doc1", "taste1",pro);		//1 like by friend
		Document doc2 = new Document("doc2", "taste1",pro);		//2 likes by friend of friend
		Document doc3 = new Document("doc3", "taste1",pro);		//3 likes by unrelated
		Document doc4 = new Document("doc4", "taste1",pro);		//1 like by friend of friend
		Document doc5 = new Document("doc5", "taste1",pro);		//1 like by unrelated
		
		//like the documents
		doc1.likeDoc(consumer2);
		doc2.likeDoc(consumer3);
		doc2.likeDoc(consumer4);
		doc3.likeDoc(consumer5);
		doc3.likeDoc(consumer6);
		doc3.likeDoc(consumer7);
		doc4.likeDoc(consumer3);
		doc5.likeDoc(consumer5);
		
		//Create list of the documents, adding them in a disorganized manner to ensure sorting works
		List<Document> list1 = new ArrayList<Document>();
		list1.add(doc2);
		list1.add(doc1);
		list1.add(doc4);
		list1.add(doc5);	
		list1.add(doc3);

		List<Document> returned = strat.rank(consumer1, list1, 4);
		
		assertEquals(4, returned.size());								//make sure it is of size 4
		assertEquals(doc4, returned.get(3));							//make sure doc4 is in the lowest position
		
		returned = strat.rank(consumer1, list1, 10);
		assertEquals(5, returned.size());								//make sure it is of size 5, the max size of the list originally passed, to check out of bounds stopping
		assertEquals(doc1, returned.get(0));							//make sure doc1 is in the highest position
		assertEquals(doc2, returned.get(1));							//make sure doc2 is in the second position
		assertEquals(doc3, returned.get(2));							//make sure doc3 is in the third position
		assertEquals(doc4, returned.get(3));							//make sure doc4 is in the fourth position
		assertEquals(doc5, returned.get(4));							//make sure doc5 is in the lowest position
		
		
	}

}