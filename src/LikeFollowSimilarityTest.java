import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * test class
 * @author Bronwyn
 *
 */
public class LikeFollowSimilarityTest {

	/**
	 * Consumer follows consumer 1, who likes doc and follows con2
	 * therefore consumer should like one document and follow con2
	 */
	@Test
	public void testRank() {
		Producer pro = new Producer("name1","taste1", new Simulation());
		Document doc = new Document("name", "tag", pro);
		List<Document> list = new ArrayList<Document>();
		list.add(doc); //make a document and adds it to list
		Simulation sim = new Simulation();
		Consumer con = new Consumer("name", "tag", sim); //make 3 consumers, con follows con1, con1 follows con2
		Consumer con1 = new Consumer("name", "tag", sim);
		Consumer con2 = new Consumer("name", "tag", sim);
		con.follow(con1);
		con1.follow(con2);
		doc.likeDoc(con1); //con1 likes document
		sim.addDoc(doc);
		sim.addLike(con1, doc);
		LikeFollowSimilarity strat = new LikeFollowSimilarity(); //therefore con should like doc and follow con2
		assertEquals(1, strat.rank(con, list, 1).size());
		assertTrue(con.following.contains(con2));
	}

}
