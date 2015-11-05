import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ButtonListener implements ActionListener {
	GUI gui;
	Simulation sim;
	public ButtonListener(GUI g, Simulation s) {
		gui = g;
		sim = s;
	}
	
	public void actionPerformed(ActionEvent e)
    {
		JButton but = (JButton) e.getSource(); //Get the button pressed and the action command (button number).
        String command = but.getActionCommand();
        if (command.equals("start")) { //Do something
        	int n1 = getTagSpinner().getValue();
        	int n2 = getConsumersSpinner().getValue();
        	int n3 = getProducersSpinner().getValue();
        	int n4 = getDocumentsSpinner().getValue();       	
        	int n5 = getRankSpinner().getValue();
        	getStepButton().setEnabled(true);
        	sim.start(n1,n2,n3,n4);
        }
        else if (command.equals("step")) {
        	int n = getIterationsSpinner().getValue();
        	sim.step(n);
        }
        
    }
}