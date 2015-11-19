/* SYSC3110 Software Design Project
 * Team redSquadron
 * RankingGUI class by Monty Dhanani
 */

import java.awt.*;
import javax.swing.*;

/**
 * The ranking strategy selection GUI.
 * @author Monty Dhanani
 *
 */
public class RankingGUI {
	
	private JRadioButton strategy1;
	private JRadioButton strategy2;
	private JRadioButton strategy3;
	private JRadioButton strategy4;
	private JButton finalizeRanking;
	private JFrame frame;
	private Simulation sim;																				
	private ButtonListener bl;	
	/**
	 * Creates the GUI
	 * @param sim is the reference to Simulation that will run the operations
	 */
	public RankingGUI(/*Simulation sim*/) {																			
		//this.sim = sim;																						
		//bl = new ButtonListener(this, sim);																	
		createAndShowGUI();
		
	}
	/**
	 * Creates and adds all of the components of the GUI to the Container
	 * @param pane is the Container that holds all of the components of the GUI
	 */
	//Adds all the components of the GUI to a container
    public void addComponentsToPane(Container pane) {
    	pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	pane.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	Color bgColor = new Color(150,0,0);
    	
    	strategy1 = new JRadioButton("<html><font color=#E1E1E1>Strategy 1</font></html>");
    	strategy1.setBackground(bgColor);
    	strategy1.setSelected(true);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 0;
    	pane.add(strategy1, c);
    	
    	strategy2 = new JRadioButton("<html><font color=#E1E1E1>Strategy 2</font></html>");
    	strategy2.setBackground(bgColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 1;
    	pane.add(strategy2, c);
    	
    	strategy3 = new JRadioButton("<html><font color=#E1E1E1>Strategy 3</font></html>");
    	strategy3.setBackground(bgColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	pane.add(strategy3, c);
    	
    	strategy4 = new JRadioButton("<html><font color=#E1E1E1>Strategy 4</font></html>");
    	strategy4.setBackground(bgColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 3;
    	pane.add(strategy4, c);
    	
    	ButtonGroup group = new ButtonGroup();
    	group.add(strategy1);
    	group.add(strategy2);
    	group.add(strategy3);
    	group.add(strategy4);
    	
    	finalizeRanking = new JButton("<html><font color=#960000>Select Ranking Strategy</font></html>");
    	finalizeRanking.setBackground(new Color(255,200,200));
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 4;
    	pane.add(finalizeRanking, c);
    	
    	frame.setVisible(true);
    	frame.pack();
    }

    /**
     * Creates the frame of the GUI and sets frame restrictions
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Ranking Strategy Select");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Color bgColor = new Color(150,0,0);
        frame.getContentPane().setBackground(bgColor);
        frame.setResizable(false);
        
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        
    }
	
    /*public static void main(String[] args) {
        RankingGUI rg = new RankingGUI();
    }*/
}