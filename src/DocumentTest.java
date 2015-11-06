import static org.junit.Assert.*;

import org.junit.Test;


public class DocumentTest {

	@Test
	public void testDocument() {
		Document doc = new Document("name", "tag");
		assertTrue(doc.getName().equals("name")&&doc.getTag().equals("tag"));
	}


	@Test
	public void testGetLikeData() {
		Document doc = new Document("name", "tag");
		Simulation sim = new Simulation();
		Consumer con = new Consumer("user", "tag", sim);
		doc.likeDoc(con);
		assertTrue(doc.hasLiked(con));
		assertTrue(doc.getLikes()==1);
		assertTrue(doc.getLikedUsers().contains(con));
	}


	@Test
	public void testCompareTo() {
		Document doc = new Document("name", "tag");
		Document doc1 = new Document("name", "tag");
		assertTrue(doc.compareTo(doc1)==0);
	}

}
