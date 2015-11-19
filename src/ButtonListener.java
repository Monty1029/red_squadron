import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Listens to the start and step buttons, controlling the Simulation.
 * @author Evan Bottomley
 *
 */
public class ButtonListener implements ActionListener {
	GUI gui;
	Simulation sim;
	private JList<User> userList;
	private JFrame list;
	
	/**
	 * Constructor
	 * @param g gui to reference
	 * @param s simulation to reference
	 */
	public ButtonListener(GUI g, Simulation s) {
		gui = g;
		sim = s;
	}
	
	public void actionPerformed(ActionEvent e)
    {
		JButton but = (JButton) e.getSource(); //Get the button pressed and the action command (button number).
        String command = but.getActionCommand();
        if (command.equals("start")) { //Do something
        	int n1 = (int) gui.getTagSpinner().getValue();
        	int n2 = (int) gui.getConsumersSpinner().getValue();
        	int n3 = (int) gui.getProducersSpinner().getValue();
        	int n4 = (int) gui.getDocumentsSpinner().getValue();       	
        	int n5 = (int) gui.getRankSpinner().getValue();				//implement later
        	gui.getStepButton().setEnabled(true);

        	gui.getSelectButton().setEnabled(true);						//this on line added nov 6 by Garrett and Monty

        	sim.start(n1,n2,n3,n4, n5);
        }
        else if (command.equals("step")) {
        	int n = (int) gui.getIterationsSpinner().getValue();
        	sim.step(n);
        }
        else if (command.equalsIgnoreCase("select"))
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
    					RankingGUI ranker = new RankingGUI();
    				}
    			}
    		};
    		userList.addMouseListener(mouseListener);
        }
        
    }
}
