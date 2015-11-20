import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests simulation
 * @author Bronwyn Skelley
 *
 */
public class SimulationTest {
	/**
	 * gets a tag to add to active simulation, checks to see how many tags in the simulation
	 */
	@Test
	public void testSelectTags() {
		Simulation sim = new Simulation();
		sim.selectTags(1);
		assertTrue(sim.getAvailableTags().size()==1);
	}
	
	/**
	 * tells simulation that a user has liked a document, checks size of hashmap that maps document to a user
	 */
	@Test
	public void testAddLike() {
		Simulation sim = new Simulation();
		Producer pro = new Producer("none","none", sim);
		Document doc = new Document("name", "tag", pro);
		Consumer con = new Consumer("name", "tag", sim);
		sim.addLike(con, doc);
		assertEquals(1, sim.getHash().size());
	}

	/**
	 * seeds a test simulation, checks how many users and documents are in the simulation
	 */
	@Test
	public void testSeed() {
		Simulation sim = new Simulation();
		sim.selectTags(1);
		sim.seed(1, 1, 1);
		assertTrue(sim.getAllUser().size()==2);
		assertTrue(sim.getAllDoc().size()==1);
		
	}


}
