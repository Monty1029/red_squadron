import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Graphing panel for a consumer
 * @author Garrett Steele and Bronwyn Skelley
 *
 */
public class PayoffGraph extends JPanel{

	private ArrayList<Integer> arr;
	private JFrame frame;
	
	/**
	 * Constructor method
	 * @param arr the arraylist of values to graph
	 * @param frame the JFrame to print to
	 */
	public PayoffGraph(ArrayList<Integer> arr, JFrame frame) {
		this.arr = arr;
		this.frame = frame;
	}


	/**
	 * Overwrite for how to paint the Panel returned
	 */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);											//do a regular paint
		Double dub = frame.getSize().getHeight();							//height of the frame
		Double dubWidth = frame.getSize().getWidth();						//width of the frame
		
		int width = (dubWidth.intValue()-100) / arr.size();					//set the width to use, scales horizontally
		int height = 10;													//height of each increment for the bars
		int bottom = dub.intValue() - 100 ;									//the bottom of the graph
		
		//create the bars
		for(int i = 0;  i < arr.size(); i++)
		{
			g.drawRect(100 + i*(width), bottom - (arr.get(i) * height), width , arr.get(i) * height);
			
			g.drawString("" + (i + 1), 105 + i*(width), bottom + 20);		//draw the iteration numbers
			
			
		}
		
		//verticle numbers
		for(int i = 0; i <= Collections.max(arr); i++)
		{
			g.drawString("" + i, 80, bottom - i * height);
		}
		
		g.drawLine(100,bottom, 100, bottom - Collections.max(arr) * height - height);	//vertical line
		g.drawLine(100, bottom, 100 + arr.size() * 50, bottom);							//horizontal line
		
		//labels
		g.drawString("Acumulative Payoff vs. Iterations", 100, 50);
		g.drawString("Iterations (total: " + arr.size() + ")", 120, bottom + 40);
		g.drawString("Payoff (" + Collections.max(arr) + ")", 10, 200);
		
		
	}
	




	/**
	 * Test that the graph will build
	 * @param args not used
	 */
	public static void main(String[] args)
	{
		
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);
		arr.add(3);
		arr.add(2);
		arr.add(5);
		
		JFrame frame = new JFrame();
		frame.setSize(500, 700);
		
		
		PayoffGraph graph = new PayoffGraph(arr, frame);
		
		JScrollPane pane = new JScrollPane();
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.add(graph);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(graph);
		frame.setVisible(true);
		
		
		
	}
	
}
