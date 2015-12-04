import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;

public class MenuBarListener implements ActionListener {
	
	private Simulation sim;
	private GUI gui;
	private int saveCounter;
	private ButtonListener bl;
	public static final String SAVE = "save";
	public static final String LOAD = "load";
	public static final String BACK = "back";
	public static final String HELP = "help";
	
	
	public MenuBarListener(GUI g, Simulation s, ButtonListener bl) {
		sim = s;
		gui = g;
		saveCounter = 0;
		this.bl = bl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem menuItem = (JMenuItem) e.getSource();
        String command = menuItem.getActionCommand();
        if (command.equals(HELP)) {
        	JFrame howToFrame = new JFrame("How To Use");
			howToFrame.setResizable(false);
			
			JTextArea howToText = new JTextArea();
			howToText.setBackground(new Color(255,235,235));
			howToText.setEditable(false);
			howToFrame.add(howToText);
			
			String howTo = "Set the number of Consumers, Producers, Documents, Tags, Iterations, and top number of documents to be\n"
					+ "ranked (this applies to all steps in the current seed). The minimum values allowed for the first 5 spinners\n"
					+ "are 1 and the miniumum value allowed for the last spinner is 10. The maximum values allowed for the first 5\n"
					+ "spinners are 50 and the maximum value allowed for the last spinner is 10.\n\n"
					+ "Click the \"Start Simulation\" button when you have set your desired parameters for the simulation.\n\n"
					+ "Next click the \"Select User to Graph\" button to bring up a list of Users. Double click on a user to select their\n"
					+ "ranking strategy via radio buttons. Only one strategy can be selected at a time. The strategy numbers are\n"
					+ "short for the following ranking criteria:\n"
					+ "Strategy 1 - Rank based on the number of 'likes' the Document has.\n"
					+ "Strategy 2 - Rank based on the number of times the User is 'followed'\n"
					+ "Strategy 3 - Rank based on the distance of the User in the social network.\n"
					+ "Strategy 4 - Rank based on the 'like' similarity of others Users liking the same kinds of Documents.\n\n"
					+ "If a producer was selected to be graphed, then select either Strategy A or Strategy B for that producer.\n"
					+ "The strategies for the producer are as follows:\n"
					+ "Strategy A - Producer creates a document of their own preferred tag.\n"
					+ "Strategy B - Producer creates a document of another tag.\n\n"
					+ "Click the \"Select Ranking Strategy\" button to set that User's ranking strategy to what you selected.\n\n"
					+ "Click the \"Next Step\" button to step through the simulation and bring up a graph for the selected user\n"
					+ "that shows their activity.";
			howToText.setText(howTo);
			howToFrame.pack();
			howToFrame.setVisible(true);
        }
        else if (command.equals(SAVE)) {
			saveCounter++;
			sim.saveSim(saveCounter, Simulation.FILENAMES);
			JMenuItem savedState = new JMenuItem("serialized"+saveCounter+".txt");
			savedState.setActionCommand(LOAD);
			savedState.addActionListener(this);
			gui.getLoadState().add(savedState);
			gui.getStepBack().setEnabled(true);
		}
        else if (command.equals(LOAD)) {
        	String filename = menuItem.getText();
        	Simulation newSim = Simulation.loadSim(filename, gui);
        	gui.getBl().setSim(newSim);
        	newSim.update();
        }
        else if (command.equals(BACK)) {
        	bl.decrementStack();
        	String filename = Simulation.STACK.replaceAll("#", "" + bl.getStackCounter());
        	Simulation newSim = Simulation.loadSim(filename, gui);
        	gui.getBl().setSim(newSim);
        	newSim.update();
        }
	}
}
