import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

/**
 * Observer to grapg a User's cumulative payoff over time
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
		if(((Simulation) o).getGraphable().getPayoffArr().size() != 0){
		graph.add(new PayoffGraph(((Simulation) o).getGraphable().getPayoffArr(), graph));
		graph.setVisible(true);}
		
	}
	
	
	
	
	
	
}
