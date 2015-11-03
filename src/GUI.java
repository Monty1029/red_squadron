/* SYSC3110 Software Design Project
 * Team redSquadron
 * GUI class by Monty Dhanani
 */

import java.awt.*;
import javax.swing.*;

public class GUI {
	
	public JLabel consumersLabel;
	public JTextField consumersField;
	public JLabel producersLabel;
	public JTextField producersField;
	public JLabel iterationsLabel;
	public JTextField iterationsField;
	public JLabel rankLabel;
	public JTextField rankField;
	public JButton startButton;
	public JButton stepButton;
	public JTextArea textArea;
	public JFrame frame;
	
	public GUI() {
		createAndShowGUI();
	}

    public void addComponentsToPane(Container pane) {
    	pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    	pane.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	Color textColor = new Color(225,225,225);
    	
    	consumersLabel = new JLabel("Number of Consumers");
    	consumersLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 0;
    	pane.add(consumersLabel, c);
    	
    	consumersField = new JTextField();
    	consumersField.setColumns(8);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 1;
    	c.gridy = 0;
    	pane.add(consumersField, c);
    	
    	producersLabel = new JLabel("Number of Producers");
    	producersLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 1;
    	pane.add(producersLabel, c);
    	
    	producersField = new JTextField();
    	producersField.setColumns(8);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 1;
    	c.gridy = 1;
    	pane.add(producersField, c);
    	
    	iterationsLabel = new JLabel("Number of Iterations");
    	iterationsLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	pane.add(iterationsLabel, c);
    	
    	iterationsField = new JTextField();
    	iterationsField.setColumns(8);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 1;
    	c.gridy = 2;
    	pane.add(iterationsField, c);
    	
    	rankLabel = new JLabel("Max Top Ranks");
    	rankLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 3;
    	pane.add(rankLabel, c);
    	
    	rankField = new JTextField();
    	rankField.setColumns(8);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 3;
    	pane.add(rankField, c);
    	
    	startButton = new JButton("<html><font color=#960000>Start Simulation</font></html>");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 4;
    	startButton.setBackground(new Color(255,200,200));
    	pane.add(startButton, c);
    	
    	stepButton = new JButton("<html><font color=#960000>Next Step</font></html>");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridwidth = 2;
    	c.gridx = 1;
    	c.gridy = 4;
    	stepButton.setBackground(new Color(255,200,200));
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
        /*javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });*/
    	GUI g = new GUI();
    }
}