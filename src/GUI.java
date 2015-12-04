/* SYSC3110 Software Design Project
 * Team redSquadron
 * GUI class by Monty Dhanani
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * The non-graph view of the MVC model.
 * @author Monty Dhanani
 *
 */
public class GUI implements Observer{
	
	private JLabel consumersLabel;
	private JSpinner consumersSpinner;
	private JLabel producersLabel;
	private JSpinner producersSpinner;
	private JLabel iterationsLabel;
	private JSpinner iterationsSpinner;
	private JLabel rankLabel;
	private JSpinner rankSpinner;
	private JLabel docLabel;
	private JSpinner docSpinner;
	private JLabel tagLabel;
	private JSpinner tagSpinner;
	private JButton startButton;
	private JButton selectButton;
	private JButton stepButton;
	private JMenuBar menuBar;
	private JMenu menu, loadState;
	private JMenuItem saveState, howToUse, stepBack;
	private JTextArea textArea;
	private JFrame frame;
	private ButtonListener bl;																				
	private MenuBarListener mbl;
	
	/**
	 * Creates the GUI
	 * @param sim is the reference to Simulation that will run the operations
	 */
	public GUI(Simulation sim) {

		bl = new ButtonListener(this, sim);
		mbl = new MenuBarListener(this, sim, bl);
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
    	
    	Color textColor = new Color(225,225,225);
    	
    	SpinnerModel sm1 = new SpinnerNumberModel(5, 1, 20, 1);
    	SpinnerModel sm2 = new SpinnerNumberModel(5, 1, 20, 1);
    	SpinnerModel sm3 = new SpinnerNumberModel(10, 1, 50, 1);
    	SpinnerModel sm4 = new SpinnerNumberModel(5, 1, 50, 1);
    	SpinnerModel sm5 = new SpinnerNumberModel(1, 1, 50, 1);
    	SpinnerModel sm6 = new SpinnerNumberModel(10, 1, 10, 1);
    	
    	menuBar = new JMenuBar();
    	menu = new JMenu("File");
    	saveState = new JMenuItem("Save State");
    	saveState.setEnabled(false);
    	saveState.setActionCommand(MenuBarListener.SAVE);
    	saveState.addActionListener(mbl);
    	menu.add(saveState);
    	loadState = new JMenu("Load State");
    	
    	menu.add(loadState);
    	menuBar.add(menu);
    	menu = new JMenu("Help");
    	howToUse = new JMenuItem("How To Use");
    	howToUse.setActionCommand(MenuBarListener.HELP);
    	howToUse.addActionListener(mbl);
    	stepBack = new JMenuItem("Step Back");
    	stepBack.setEnabled(false);
    	stepBack.setActionCommand(MenuBarListener.BACK);
    	stepBack.addActionListener(mbl);
    	loadState.add(stepBack);
    	loadState.addSeparator();
    	menu.add(howToUse);
    	menuBar.add(menu);
    	frame.setJMenuBar(menuBar);
    	    	
    	consumersLabel = new JLabel("Number of Consumers");
    	consumersLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 0;
    	pane.add(consumersLabel, c);
    	
    	//Allows user to enter the number of consumers in the simulation
    	consumersSpinner = new JSpinner(sm1);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 2;
    	c.gridy = 0;
    	pane.add(consumersSpinner, c);
    	
    	producersLabel = new JLabel("Number of Producers");
    	producersLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 1;
    	pane.add(producersLabel, c);
    	
    	//Allows user to enter the number of producers in the simulation
    	producersSpinner = new JSpinner(sm2);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 2;
    	c.gridy = 1;
    	pane.add(producersSpinner, c);
    	
    	docLabel = new JLabel("Number of Documents");
    	docLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 2;
    	pane.add(docLabel, c);
    	
    	//Allows user to enter the number of documents in the simulation
    	docSpinner = new JSpinner(sm3);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 2;
    	c.gridy = 2;
    	pane.add(docSpinner, c);
    	
    	tagLabel = new JLabel("Number of Tags");
    	tagLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 3;
    	pane.add(tagLabel, c);
    	
    	//Allows user to enter the number of tags in the simulation
    	tagSpinner = new JSpinner(sm4);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 2;
    	c.gridy = 3;
    	pane.add(tagSpinner, c);
    	
    	iterationsLabel = new JLabel("Number of Iterations");
    	iterationsLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 4;
    	pane.add(iterationsLabel, c);
    	
    	//Allows user to enter the number of iterations in the simulation
    	iterationsSpinner = new JSpinner(sm5);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.weightx = 3.0;
    	c.gridx = 2;
    	c.gridy = 4;
    	pane.add(iterationsSpinner, c);
    	
    	rankLabel = new JLabel("Max Top Ranks for Seed");
    	rankLabel.setForeground(textColor);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 5;
    	pane.add(rankLabel, c);
    	
    	//Allows user to enter the number of documents that get ranked in the simulation
    	rankSpinner = new JSpinner(sm6);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 2;
    	c.gridy = 5;
    	pane.add(rankSpinner, c);
    	
    	//Button to start the simulation
    	startButton = new JButton("<html><font color=#960000>Start Simulation</font></html>");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 0;
    	c.gridy = 6;
    	startButton.setBackground(new Color(255,200,200));
    	startButton.setActionCommand(ButtonListener.START);
    	startButton.addActionListener(bl);
    	pane.add(startButton, c);
    	
    	selectButton = new JButton("<html><font color=#960000>Select User to Graph</font></html>");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridx = 1;
    	c.gridy = 6;
    	selectButton.setBackground(new Color(255,200,200));
    	selectButton.setActionCommand(ButtonListener.SELECT);
    	selectButton.addActionListener(bl);
    	selectButton.setEnabled(false);//ADDED BUTTON LISTENER
    	pane.add(selectButton, c);
    	
    	//Button to step through the simulation
    	stepButton = new JButton("<html><font color=#960000>Next Step</font></html>");
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.gridwidth = 2;
    	c.gridx = 2;
    	c.gridy = 6;
    	stepButton.setBackground(new Color(255,200,200));
    	stepButton.setActionCommand(ButtonListener.STEP);
    	stepButton.addActionListener(bl);
    	stepButton.setEnabled(false);
    	pane.add(stepButton, c);    	
    	
    	//Text area that will display the output at each step of the simulation
    	textArea = new JTextArea(10,1);
    	c.fill = GridBagConstraints.HORIZONTAL;
    	c.ipady = 152;
    	c.gridwidth = 3;
    	c.gridx = 0;
    	c.gridy = 7;
    	textArea.setBackground(new Color(255,235,235));
    	textArea.setEditable(false);
    	JScrollPane scroll = new JScrollPane (textArea,
    			JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	
    	pane.add(scroll, c);
    	frame.setVisible(true);
    }

    /**
     * Creates the frame of the GUI and sets frame restrictions
     */
    private void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Social Network");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,372);
        Color bgColor = new Color(150,0,0);
        frame.getContentPane().setBackground(bgColor);
        frame.setResizable(false);
        
        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());
        
    }    
    
    //Accessor methods for each of the spinners, text area, and the "Next Step" button
    /**
     * 
     * @return the Spinner for number of consumers
     */
    public JSpinner getConsumersSpinner() {
		return consumersSpinner;
	}
	
    /**
     * 
     * @return the Spinner for number of producers
     */
	public JSpinner getProducersSpinner() {
		return producersSpinner;
	}
	
	/**
     * 
     * @return the Spinner for number of iterations
     */
	public JSpinner getIterationsSpinner() {
		return iterationsSpinner;
	}
	
	/**
     * 
     * @return the Spinner for max ranks
     */
	public JSpinner getRankSpinner() {
		return rankSpinner;
	}
	
	/**
     * 
     * @return the Spinner for number of documents
     */
	public JSpinner getDocumentsSpinner() {
		return docSpinner;
	}
	
	/**
     * 
     * @return the Spinner for number of tags
     */
	public JSpinner getTagSpinner() {
		return tagSpinner;
	}
	
	/**
     * 
     * @return the Button that allows you to select a user to graph
     */
	public JButton getSelectButton() {
		return selectButton;
	}
	
	/**
     * 
     * @return the Button that allows you to step through each iteration
     */
	public JButton getStepButton() {
		return stepButton;
	}
	
	/**
     * 
     * @return the TextArea for the output of the iteration
     */
	public JTextArea getTextArea() {
		return textArea;
	}
	
	/**
     * 
     * @return the Menu
     */
	public JMenu getMenu() {
		return menu;
	}
	
	/**
     * 
     * @return the loadState
     */
	public JMenu getLoadState() {
		return loadState;
	}
	
	/**
     * 
     * @return the saveState
     */
	public JMenuItem getSaveState() {
		return saveState;
	}
	
	/**
     * 
     * @return the stepBack
     */
	public JMenuItem getStepBack() {
		return stepBack;
	}
	
	/**
     * 
     * @return the buttonListener bl
     */
	public ButtonListener getBl() {
		return bl;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
		Simulation simRef = (Simulation) o;
		String toUpdate  = new String();
		
		//output the last step results
		toUpdate += simRef.getResults();
		
		//output all user details
		toUpdate += "\n\n";
		toUpdate += "=========================================================================================================";
		toUpdate += "\nUser details\n\n";
		for(User u: simRef.getAllUser())
		{
			toUpdate += u.details() + "\n";
		}
		
		//output all User-Doc likes
		toUpdate += "\n\n";
		toUpdate += "=========================================================================================================";
		toUpdate += "\nUsers and the Documents they like\n\n";
		//toUpdate += simRef.getHash().toString();
		for(User u: simRef.getHash().keySet())
		{
			toUpdate += "User: " + u.toString() + ", Likes: " + simRef.getHash().get(u).toString() + "\n";
		}
		
		//output all Doc-User pairs and Doc info
		toUpdate += "\n\n";
		toUpdate += "=========================================================================================================";
		toUpdate += "\nDocuments and the people who like them\n\n";
		for(Document d: simRef.getAllDoc())
		{
			toUpdate += "Document: " + d.getName() + ", Tag: " + d.getTag() + ", Liked: " + d.getLikes() + "\n";
			
			if(d.getLikedUsers() != null){
			toUpdate += "Users who liked this Document: \n\t" + d.getLikedUsers().toString() + "\n\n";}
		}
		
		textArea.setText(toUpdate);
		
		
		
	}

	
	
    /*public static void main(String[] args) {
        GUI g = new GUI(null);
    }*/
}