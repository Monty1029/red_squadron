import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class PopularityStrategyTest {


	@Test
	/**
	 * Test that the ranking strategy works as planned for a User, list of documents, adn number of documents to return
	 */
	public void testRank() {
		
		
		Simulation sim = new Simulation();	//create Simulation to reference				
		PopularityStrategy strat = new PopularityStrategy();
		Producer pro = new Producer("name1", "taste1", sim);
		
		//create the Consumers to test with
		Consumer consumer1 = new Consumer("Consumer 1", "taste1", sim);
		Consumer consumer2 = new Consumer("Consumer 2", "taste1", sim);
		
		//create 3 documents, 1 with a different taste
		Document doc1 = new Document("doc1", "taste1",pro);		//1 like
		Document doc2 = new Document("doc2", "taste1",pro);		//no likes
		Document doc3 = new Document("doc3", "taste1",pro);		//2 likes
		
		//like the documents
		doc3.likeDoc(consumer1);
		doc3.likeDoc(consumer2);
		doc1.likeDoc(consumer1);
		
		//create list of the documents
		List<Document> list1 = new ArrayList<Document>();
		list1.add(doc1);
		list1.add(doc2);
		list1.add(doc3);
		
		List<Document> returned = strat.rank(consumer1, list1, 2);
		assertEquals(2, returned.size());								//make sure it is of size 2
		assertEquals(doc3, returned.get(0));							//make sure doc3 is in the highest position
		
		returned = strat.rank(consumer1, list1, 5);
		assertEquals(3, returned.size());								//make sure it is of size 3, the max size of the list originaly passed, to check out of bounds stopping
		assertEquals(doc2, returned.get(2));							//make sure doc2 is in the lowest position
		
		
	}

}
