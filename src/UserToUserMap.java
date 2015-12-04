import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Class to output user to User relationships
 * @author Garrett Steele
 *
 */
public class UserToUserMap extends JPanel{
	
	
	private static int QUAD1_start = 0;
	private static int QUAD2 = 90;
	private static int QUAD3 = 180;
	private static int QUAD4 = 270;
	
	
	private List<User> users;
	private JFrame frame;
	private HashMap<User, Pos> userPos;
	private User graphable;
	
	private int mapRadius = 100;
	private int radiusOffset = 20;
	private int spacingDegree = 360;
	private int width = 20;
	private Pos center;
	
	public UserToUserMap(List<User> users, JFrame frame, User g) 
	{
		//set required references
		this.users = users;
		this.frame = frame;
		userPos = new HashMap<>();
		graphable = g;
		
		//calculate spacings and track the center of the 
		spacingDegree = spacingDegree/users.size();
		center = new Pos(frame.getWidth()/2, frame.getHeight()/2);
	}
	
	/**
	 * Overwrite for how to paint the Panel returned
	 */
	protected void paintComponent(Graphics g)
	{
		int degree = 0;
		int x, y;
		
		super.paintComponent(g); 				//paint JPanel to draw on top of it
		this.setBackground(Color.BLACK);		//black backdrop
		g.setColor(Color.white);				//white graphics by default
		
		//loop through the users and calculate their position
		for(User u: users)
		{
			//the calculation for the position differs based on the degrees around the circle of Users
			//it is set up in quadrants in terms of calculating x and y position as if (0,0) like in math 2D graphing
			//these must then be translated by the coordinates of the "center" of the JFrame to figure out what the actual position to draw lines to/from is
			//this position is not the same and is "inwards" to the circle of Users from the circle drawn for each User
			if(degree == 0)										//its at the top
			{
				userPos.put(u, new Pos(center.getX(), center.getY() - mapRadius));
				g.fillOval(userPos.get(u).getX(), userPos.get(u).getY() - mapRadius - width, width, width);
				degree += spacingDegree;
			}
			else if(degree > QUAD1_start && degree < QUAD2)		//its in the top right
			{
				
			}
			else if(degree == QUAD2)							//its to the right
			{
				//straight forward calculation to position it to the right of the center
			}
			else if(degree < QUAD3 && degree > QUAD2)			//its to the bottom right
			{
				
			}
			else if(degree == QUAD3)							//its to the bottom
			{//straight forward calculation to position it directly down from the center
				
			}
			else if(degree < QUAD4 && degree > QUAD3)			//its to the bottom left
			{
				
			}
			else if(degree == QUAD4)							//its to the left
			{
				//straight forward calculation to position it to the left of the center
			}
			else												//it must then be to the top left
			{
				
			}
		}
	}
	
	
}
