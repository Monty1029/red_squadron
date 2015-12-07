import java.awt.Color;
import java.awt.Graphics;
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
	private HashMap<User, Pos> userPos;
	private User graphable;
	
	private int mapRadius = 250;
	private int spacingDegree = 360;
	private int width = 10;
	private Pos center;
	private int verticleOffset = 30;
	
	public UserToUserMap(List<User> users, JFrame frame, User g) 
	{
		//set required references
		this.users = users;
		userPos = new HashMap<>();
		graphable = g;
		
		//calculate spacings and track the center of the 
		spacingDegree = spacingDegree/users.size();
		center = new Pos(frame.getWidth()/2 - verticleOffset, frame.getHeight()/2 - verticleOffset);
	}
	
	/**
	 * Overwrite for how to paint the Panel returned
	 */
	protected void paintComponent(Graphics g)
	{
		//positioning variables for calculations
		double degree = 0;
		int x, y;
		
		super.paintComponent(g); 				//paint JPanel to draw on top of it
		this.setBackground(Color.BLACK);		//black backdrop
		g.setColor(Color.white);				//white graphics by default
		
		//loop through the users and calculate their position
		for(User u: users)
		{
			//change color for the person selected for graphing
			if(u == graphable){g.setColor(Color.ORANGE);}
			else if(u instanceof Consumer){g.setColor(Color.cyan);}
			else if(u instanceof Producer){g.setColor(Color.red);}
			
			//determine the label to print for the user
			String label = u.toString();
			label = u.toString().charAt(0) + u.toString().substring(8, u.toString().length());	
			
			
			//////////////////////////////////////////////////////////
			//														//
			//				Positions, Circles, & Labels			//
			//														//
			//////////////////////////////////////////////////////////
			
			//the calculation for the position differs based on the degrees around the circle of Users
			//it is set up in quadrants in terms of calculating x and y position as if (0,0) like in math 2D graphing
			//these must then be translated by the coordinates of the "center" of the JFrame to figure out what the actual position to draw lines to/from is
			//this position is not the same and is "inwards" to the circle of Users from the circle drawn for each User
			y = (int) ((int) mapRadius * Math.sin(Math.toRadians(degree)));
			x = (int) ((int) mapRadius * Math.cos(Math.toRadians(degree)));
			
			
			if(degree == 0)										//its at the top
			{
				userPos.put(u, new Pos(center.getX(), center.getY() - mapRadius));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY() - width/2, width, width);
				g.drawString(label,userPos.get(u).getX() - width/2, userPos.get(u).getY() - width);
			}
			else if(degree > QUAD1_start && degree < QUAD2)		//its in the top right
			{
				
				userPos.put(u, new Pos(center.getX() + x, center.getY() - y));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY() - width/2, width, width);
				g.drawString(label,userPos.get(u).getX() + width, userPos.get(u).getY() - width);
			}
			else if(degree == QUAD2)							//its to the right
			{
				//straight forward calculation to position it to the right of the center
				userPos.put(u, new Pos(center.getX() + mapRadius, center.getY()));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY(), width, width);
				g.drawString(label,userPos.get(u).getX() + width, userPos.get(u).getY());
			}
			else if(degree < QUAD3 && degree > QUAD2)			//its to the bottom right
			{
				userPos.put(u, new Pos(center.getX() - x, center.getY() + y));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY() - width/2, width, width);
				g.drawString(label,userPos.get(u).getX() + width, userPos.get(u).getY() + width);
			}
			else if(degree == QUAD3)							//its to the bottom
			{//straight forward calculation to position it directly down from the center
				userPos.put(u, new Pos(center.getX(), center.getY() + mapRadius));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY() - width/2, width, width);
				g.drawString(label,userPos.get(u).getX() - width/2, userPos.get(u).getY() + width + width/2);
			}
			else if(degree < QUAD4 && degree > QUAD3)			//its to the bottom left
			{
				userPos.put(u, new Pos(center.getX() + x, center.getY() - y));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY() - width/2, width, width);
				g.drawString(label,userPos.get(u).getX() - 2*width, userPos.get(u).getY() + width);
			}
			else if(degree == QUAD4)							//its to the left
			{
				//straight forward calculation to position it to the left of the center
				userPos.put(u, new Pos(center.getX() - mapRadius, center.getY()));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY(), width, width);
				g.drawString(label,userPos.get(u).getX() - 3*width, userPos.get(u).getY() + width/2);
			}
			else												//it must then be to the top left
			{
				userPos.put(u, new Pos(center.getX() - x, center.getY() + y));
				g.fillOval(userPos.get(u).getX() - width/2, userPos.get(u).getY() - width/2, width, width);
				g.drawString(label,userPos.get(u).getX() - width - width, userPos.get(u).getY() - width);
			}
			
			//increment the degree around the circle
			degree += spacingDegree;
			g.setColor(Color.white);
		}
		
		
		//////////////////////////////////////////////////////////
		//														//
		//					Lines between Users					//
		//														//
		//////////////////////////////////////////////////////////
		
		//for every User, draw a line to the person they follow
		for(User u: users)
		{
			if(u == graphable){g.setColor(Color.ORANGE);}
			else if(u instanceof Consumer){g.setColor(Color.cyan);}
			else if(u instanceof Producer){g.setColor(Color.red);}
			
			
			for(User f: u.getFollowing())
			{
				connection(g, userPos.get(u), userPos.get(f));
			}
		}
		
		//make sure the orange lines are on top
		g.setColor(Color.ORANGE);
		for(User u: graphable.getFollowing()){connection(g, userPos.get(graphable), userPos.get(u));}
		
	
		
	}
	
	/**
	 * Method to draw a line between users
	 * @param g the Graphics object to draw with
	 * @param from the start of the line
	 * @param to the end of the line
	 */
	private void connection(Graphics g, Pos from, Pos to)
	{
		//draw the basic line
		g.drawLine(from.getX(), from.getY(), to.getX(), to.getY());	
	}
	
}
