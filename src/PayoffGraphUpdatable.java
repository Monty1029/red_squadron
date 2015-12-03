import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * Observer to graph a User's cumulative payoff over time
 * @author Garrett Steele
 *
 */
public class PayoffGraphUpdatable implements Observer{

	//the graph to show
	private JFrame graph = null;
	
	
	/**
	 * Default Constructor
	 */
	public PayoffGraphUpdatable(){}

	@Override
	/**
	 * Create and display the graph
	 */
	public void update(Observable o, Object arg) {
		if (graph != null){graph.dispose();}
		
		graph = new JFrame("Graph: " + ((Simulation) o).getGraphable().getName());
		//graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Double dub = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		graph.setSize(dub.intValue(), 700);
		JTabbedPane pane = new JTabbedPane();
		if(((Simulation) o).getGraphable().getPayoffArr().size() != 0){
			
		pane.addTab("Payoff Graph: " + ((Simulation) o).getGraphable().toString(), new PayoffGraph(((Simulation) o).getGraphable().getPayoffArr(), graph));
		pane.addTab("Users to Documents", new UserDocMap(((Simulation) o).getAllUser(), ((Simulation) o).getAllDoc(), graph));
		//graph.add(new PayoffGraph(((Simulation) o).getGraphable().getPayoffArr(), graph));
		graph.add(pane);
		graph.setVisible(true);}
		
	}
	
	
	
	
	
	
}
