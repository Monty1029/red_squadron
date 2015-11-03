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
        	int n1 = getConsumersSpinner().getValue();
        	int n2 = getProducersSpinner().getValue();
        	int n3 = getRankSpinner().getValue();
        	sim.start(1,1,1,1);
        }
        else if (command.equals("step")) {
        	int n = getIterationsSpinner().getValue();
        	sim.step(n);
        }
        
    }
}