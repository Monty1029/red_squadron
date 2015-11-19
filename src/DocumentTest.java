import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the Document class
 * @author Bronwyn
 * Tests for Document class
 */
public class DocumentTest {
	/**
	 * tests to see if constructor works
	 */
	@Test
	public void testDocument() {
		Document doc = new Document("name", "tag");
		assertTrue(doc.getName().equals("name")&&doc.getTag().equals("tag"));
	}

	/**
	 * tests to see if null constructor works
	 */
	@Test
	public void testNullDocument() {
		Document doc = new Document(null, null);
		assertTrue(doc.getName().equals("none")&&doc.getTag().equals("none"));
	}

	/**
	 * tests to see if document has been liked by a consumer, and is contained in the documents lists of which user liked it
	 */
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

	/**	
	 * makes two documents, see if they have same amount of likes
	 */
	@Test
	public void testCompareTo() {
		Document doc = new Document("name", "tag");
		Document doc1 = new Document("name", "tag");
		assertTrue(doc.compareTo(doc1)==0);
	}

}
