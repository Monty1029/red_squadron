import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	/**
	 * Method to test the step() method
	 */
	@Test
	public void testStep()
	{
		int count = 0;
		
		//create a new simulation and test a Producer step
		Simulation sim = new Simulation();
		
		//start the simulation
		sim.start(1, 0, 1, 10, 8);						//1 tag, no consumer, 1 producer, 10 seeded documents of the same tag, top 5 documents should be returned
		sim.step(1);
		for(ArrayList<Document> d: sim.getHash().values()){count += d.size();}
		assertTrue(count == 8);		//in this scenario 8 documents should be liked by the producer in the hashset
		
		//reset and test a consumer step
		count = 0;
		sim = new Simulation();
		sim.start(1, 1, 0, 10, 8);						//1 tag, no consumer, 1 producer, 10 seeded documents of the same tag, top 5 documents should be returned
		sim.step(1);
		for(ArrayList<Document> d: sim.getHash().values()){count += d.size();}
		assertTrue(count == 8);		//in this scenario one all of the top 8 documents should not be seen by the consumer
		
		
		
	}
	
	
	/**
	 * Method to test the start() method
	 */
	@Test
	public void testSimulation()
	{
		//create a simulation to check
		Simulation sim = new Simulation();
		
		//all of these things should not be null at simulation creation
		assertNotEquals(sim.getAvailableTags(), null);
		assertNotEquals(sim.getAllDoc(), null);
		assertNotEquals(sim.getAllUser(), null);
		assertEquals(sim.getGraphable(), null);
		assertNotEquals(sim.getHash(), null);
		assertNotEquals(sim.getResults(), null);
	}
	
	/**
	 * Method to test the step() method
	 */
	@Test
	public void testStart()
	{
		int consumerCount = 0;
		
		Simulation sim = new Simulation();
		
		//start the simulation
		sim.start(2, 1, 3, 4, 5);
		assertEquals(sim.getAvailableTags().size(),2);	//2 tags
		assertEquals(sim.getAllUser().size(),4);		//3 users
		assertEquals(sim.getAllDoc().size(), 4);		//5 documents
		
		//check that there is indeed only one consumer
		for(User u: sim.getAllUser()){if(u instanceof Consumer){consumerCount++;}}
		assertEquals(consumerCount, 1);
		
	}
	

}
