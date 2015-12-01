import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Listens to the start and step buttons, controlling the Simulation.
 * @author Evan Bottomley, Monty Dhanani
 *
 */
public class ButtonListener implements ActionListener {
	
	public static final String SELECT = "select";
	public static final String START = "start";
	public static final String STEP = "step";
	
	private GUI gui; 				//made this private as per required fix from Milestone 2 - Monty
	private Simulation sim;			//made this private as per required fix from Milestone 2 - Monty
	private JList<User> userList;
	private JFrame list;
	private RankingGUI rg;
	private int stackCounter;
	
	/**
	 * Constructor
	 * @param g gui to reference
	 * @param s simulation to reference
	 */
	public ButtonListener(GUI g, Simulation s) {
		gui = g;
		sim = s;
		stackCounter = 0;
	}
	
	public void actionPerformed(ActionEvent e)
    {
		JButton but = (JButton) e.getSource(); //Get the button pressed and the action command (button number).
        String command = but.getActionCommand();
        if (command.equals(START)) { //Do something
        	gui.getSaveState().setEnabled(true);
        	int n1 = (int) gui.getTagSpinner().getValue();
        	int n2 = (int) gui.getConsumersSpinner().getValue();
        	int n3 = (int) gui.getProducersSpinner().getValue();
        	int n4 = (int) gui.getDocumentsSpinner().getValue();       	
        	int n5 = (int) gui.getRankSpinner().getValue();				//implement later
        	gui.getStepButton().setEnabled(true);

        	gui.getSelectButton().setEnabled(true);						//this on line added nov 6 by Garrett and Monty

        	sim.start(n1,n2,n3,n4, n5);
        }
        else if (command.equals(STEP)) {
        	int n = (int) gui.getIterationsSpinner().getValue();
        	sim.step(n);
        	sim.saveSim(stackCounter, Simulation.STACK);
        	stackCounter++;
        	gui.getStepBack().setEnabled(true);
        }
        else if (command.equalsIgnoreCase(SELECT))
        {
        	list = new JFrame("List of Users");

    		DefaultListModel<User> addList = new DefaultListModel<User>();
    		for (User u : sim.getAllUser()) {
    			addList.addElement(u);
    		}
    		userList = new JList<User>(addList);
    		list.add(userList);
    		list.pack();
    		list.setVisible(true);

    		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    		MouseListener mouseListener = new MouseAdapter() {
    			public void mouseClicked(MouseEvent mouseEvent) {
    				JList theList = (JList) mouseEvent.getSource();
    				if (mouseEvent.getClickCount() == 2) {
    					sim.setGraphable(userList.getSelectedValue());
    					list.dispose();
    					rg = new RankingGUI();
        				rg.setListener(new RankingListener(rg, userList.getSelectedValue()));
    				}
    				
    			}
    		};
    		userList.addMouseListener(mouseListener);
        }
        
    }
	
	
	public void setSim(Simulation sim){this.sim = sim;}
	
	public int getStackCounter() {
		return stackCounter;
	}
	
	public void decrementStack() {
		if (stackCounter > 0) {
			stackCounter--;
		}
		else {
			stackCounter = 0;
		}
	}
	
}
