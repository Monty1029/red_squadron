import static org.junit.Assert.*;

import org.junit.Test;

/**
 * tests simulation
 * @author Bronwyn Skelley, testSaveLoadSim() added by Garrett Steele
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
	 * seeds a test simulation, checks how many users and documents are in the simulation. 
	 * Can't get a null to see in practice because the values of the JSpinners are never null.
	 */
	@Test
	public void testSeed() {
		Simulation sim = new Simulation();
		sim.selectTags(1);
		sim.seed(1, 1, 1);
		assertTrue(sim.getAllUser().size()==2);
		assertTrue(sim.getAllDoc().size()==1);
		
	}

	/**
	 * method to test if the save an dload features for a simulation work
	 */
	@Test
	public void testSaveLoadSim()
	{
		//create a simulation
		Simulation sim = new Simulation();
		sim.selectTags(1);
		sim.seed(1, 1, 1);
		
		//save it to a file and read the simulation back in under a new reference
		sim.saveSim(9000, Simulation.FILENAMES);
		Simulation copySim = Simulation.loadSim(Simulation.FILENAMES.replaceAll("#", "" + 9000), null);
		
		assertEquals(sim.getAllUser().size(), copySim.getAllUser().size());					//should be same number of users
		assertEquals(sim.getAllDoc().size(), copySim.getAllDoc().size());					//should be same number of documents
		assertEquals(sim.getAvailableTags().size(), copySim.getAvailableTags().size());		//should be same number of available tags
		assertEquals("" + sim.getResults(),"" + copySim.getResults());						//should have the same results text
	}
	
	

}
