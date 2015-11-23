import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for Ranking StrategyTwo
 * 
 * @author Monty Dhanani
 *
 */
public class StrategyTwoTest {
	/**
	 * Tests if the size of the sorted array in StrategyTwo is correct.
	 * Also tests if the document with user with the highest number of followers is the #1 ranked document
	 */
	@Test
	public void testRank() {

		Producer pro = new Producer("pro1","taste1", new Simulation());
		Document doc1 = new Document("doc1", "tag", pro);
		Document doc2 = new Document("doc2", "tag", pro);
		List<Document> list = new ArrayList<Document>();
		list.add(doc1);
		Simulation sim = new Simulation();
		Consumer con1 = new Consumer("con1", "tag", sim);
		Consumer con2 = new Consumer("con2", "tag", sim);
		Consumer con3 = new Consumer("con3", "tag", sim);
		con1.follow(con2);
		con1.follow(con3);
		con2.follow(con3);
		doc1.likeDoc(con2);
		sim.addDoc(doc1);
		sim.addLike(con2, doc1);
		StrategyTwo strat = new StrategyTwo();
		assertEquals(1, strat.rank(null, list, 1).size());
		assertEquals(doc1, strat.rank(null, list, 1).get(0));
	}

	/**
	 * Return the name
	 */
	public String toString(){return "User Popularity";}
	
	
}