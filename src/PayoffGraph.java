import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class PayoffGraph extends JPanel{

	private ArrayList<Integer> arr;
	private JFrame frame;
	
	public PayoffGraph(ArrayList<Integer> arr, JFrame frame) {
		this.arr = arr;
		this.frame = frame;
	}






	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Double dub = frame.getSize().getHeight();
		System.out.println("" + dub);
		
		//create the bars
		for(int i = 0; i < arr.size(); i++)
		{
			g.drawRect(100 + i*(40/arr.size()), 400 - (arr.get(i) * 50), 40 / arr.size(), arr.get(i) * 50);
			
			g.drawString("" + (i + 1), 105 + i*(40/arr.size()), 420);		//iterations
			
			
		}
		
		for(int i = 0; i <= Collections.max(arr); i++)
		{
			g.drawString("" + i, 90, 400 - i * 50);
		}
		
		g.drawLine(100,400, 100, 400 - Collections.max(arr) * 50 - 50);
		g.drawLine(100, 400, 100 + arr.size() * 50, 400);	//horizontal
		
		g.drawString("Acumulative Payoff vs. Iterations", 100, 50);
		g.drawString("Iterations", 120, 440);
		g.drawString("Payoff", 10, 200);
		
		
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
		frame.setSize(500, 500);
		
		
		PayoffGraph graph = new PayoffGraph(arr, frame);
		
		JScrollPane pane = new JScrollPane();
		pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pane.add(graph);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(graph);
		frame.setVisible(true);
		
		
		
	}
	
}
