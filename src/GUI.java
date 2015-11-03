/* SYSC3110 Software Design Project
 * Team redSquadron
 * GUI class by Monty Dhanani
 */

import java.awt.*;
import javax.swing.*;

public class GUI {
	
	private JLabel consumersLabel;
	private JSpinner consumersSpinner;
	private JLabel producersLabel;
	private JSpinner producersSpinner;
	private JLabel iterationsLabel;
	private JSpinner iterationsSpinner;
	private JLabel rankLabel;
	private JSpinner rankSpinner;
	private JButton startButton;
	private JButton stepButton;
	private JTextArea textArea;
	private JFrame frame;
	
	public GUI() {
		createAndShowGUI();
	}

    public void addComponentsToPane(Container pane) {
    	pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	pane.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	Color textColor = new Color(225,225,225);
    	
    	SpinnerModel sm1 = new SpinnerNumberModel(0, 0, 2000000, 1);
    	SpinnerModel sm2 = new SpinnerNumberModel(0, 0, 2000000, 1);
    	SpinnerModel sm3 = new SpinnerNumberModel(0, 0, 2000000, 1);
    	SpinnerModel sm4 = new SpinnerNumberModel(0, 0, 2000000, 1);
    	
    	consumersLabel = new JLabel("Number of Consumers");
    	consumersLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 0;
    	pane.add(consumersLabel, c);
    	
    	consumersSpinner = new JSpinner(sm1);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 1;
    	c.gridy = 0;
    	pane.add(consumersSpinner, c);
    	
    	producersLabel = new JLabel("Number of Producers");
    	producersLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 1;
    	pane.add(producersLabel, c);
    	
    	producersSpinner = new JSpinner(sm2);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 1;
    	c.gridy = 1;
    	pane.add(producersSpinner, c);
    	
    	iterationsLabel = new JLabel("Number of Iterations");
    	iterationsLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	pane.add(iterationsLabel, c);
    	
    	iterationsSpinner = new JSpinner(sm3);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 1;
    	c.gridy = 2;
    	pane.add(iterationsSpinner, c);
    	
    	rankLabel = new JLabel("Max Top Ranks");
    	rankLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 3;
    	pane.add(rankLabel, c);
    	
    	rankSpinner = new JSpinner(sm4);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 3;
    	pane.add(rankSpinner, c);
    	
    	startButton = new JButton("<html><font color=#960000>Start Simulation</font></html>");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 4;
    	startButton.setBackground(new Color(255,200,200));
    	startButton.setActionCommand("start");
    	pane.add(startButton, c);
    	
    	stepButton = new JButton("<html><font color=#960000>Next Step</font></html>");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridwidth = 2;
    	c.gridx = 1;
    	c.gridy = 4;
    	stepButton.setBackground(new Color(255,200,200));
    	stepButton.setActionCommand("step");
    	pane.add(stepButton, c);
    	
    	textArea = new JTextArea(10,1);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridwidth = 2;
    	c.gridx = 0;
    	c.gridy = 5;
    	textArea.setBackground(new Color(255,235,235));
    	textArea.setEditable(false);
    	JScrollPane scroll = new JScrollPane (textArea,
    			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	pane.add(scroll, c);
    	frame.setVisible(true);
    }

    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Social Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,312);
        Color bgColor = new Color(150,0,0);
        frame.getContentPane().setBackground(bgColor);
        frame.setResizable(false);
        
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        
    }    

    public static void main(String[] args) {
        GUI g = new GUI();
    }
}
