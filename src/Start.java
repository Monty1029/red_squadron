
public class Start {

	/**
	 * Main function creates a simulation and GUI
	 * @param args not used currently
	 */
	public static void main(String[] args) {
		//Create a new simulation and seed the simulation
		
		Simulation sim = new Simulation();
		GUI g = new GUI(sim);
		sim.setGUI(g);
		

	}
	
}
