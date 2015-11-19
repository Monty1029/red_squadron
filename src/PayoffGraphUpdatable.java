import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;


public class PayoffGraphUpdatable implements Observer{

	//the graph to show
	private JFrame graph = null;
	
	
	//default constructor
	public PayoffGraphUpdatable(){}

	@Override
	public void update(Observable o, Object arg) {
		if (graph != null){graph.dispose();}
		
		graph = new JFrame("Graph: " + ((Simulation) o).getGraphable().getName());
		//graph.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Double dub = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		
		graph.setSize(dub.intValue(), 700);
		
		graph.add(new PayoffGraph(((Simulation) o).getGraphable().getPayoffArr(), graph));
		graph.setVisible(true);
		
	}
	
	
	
	
	
	
}
