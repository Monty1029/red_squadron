import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Users to Document mapping
 * @author Evan Bottomley, Bronwyn Skelley, Garrett Steele
 *
 */
public class UserDocMap extends JPanel{

	//references
	private JFrame frame;
	private List<User> users;
	private List<Document> docs;
	private HashMap<User, Pos> userPos;
	private HashMap<Document, Pos> docPos;
	private User graphable;
	
	//offsets for graphing
	private int verticleUserOffset = 30;
	private int verticleDocumentOffset = 500;
	private int horizontalOffset = 20;
	private int docSize = 70;
	private int userSize = 70;
	private int size = 16;
	private int userSpacing, docSpacing;
	
	
	/**
	 * Constructor
	 * @param users list of all users
	 * @param docs list of all documents
	 * @param frame the JFrame to draw in
	 */
	public UserDocMap(List<User> users, List<Document> docs, JFrame frame, User g) 
	{
		//set required references
		this.users = users;
		this.docs = docs;
		this.frame = frame;
		userPos = new HashMap<>();
		docPos = new HashMap<>();
		graphable = g;
		
		//calculate spacings
		userSpacing = (frame.getWidth() - 2*horizontalOffset)/users.size();
		docSpacing = (frame.getWidth() - 2*horizontalOffset)/docs.size();
		if(docs.size() > 15){docSize = docSize/(docs.size()/5);}
		if(users.size() > 15){userSize = userSize/(users.size()/5);}
	}
	
	
	
	/**
	 * Overwrite for how to paint the Panel returned
	 */
	protected void paintComponent(Graphics g)
	{
		int counter = 0;
		super.paintComponent(g);											//do a regular paint
		Double dub = frame.getSize().getHeight();							//height of the frame
		Double dubWidth = frame.getSize().getWidth();						//width of the frame
		//x and y are based on the top and left of the frame
		if(users.size() > 25){size = size/2;}
		g.setFont(new Font("TimesRoman", Font.PLAIN, size));
		this.setBackground(Color.white);
		
		//loop through the users and draw their circles and determine their line positions
		for(User u: users){
			

			//set the circle color
			g.setColor(Color.BLUE);
			if (u instanceof Producer) {
				g.setColor(Color.red);
			}
			if(u == graphable){g.setColor(Color.ORANGE);}
			
			//determine the string to print
			String label = u.toString();
			label = u.toString().charAt(0) + u.toString().substring(8, u.toString().length());			
			
			//draw User
			g.fillOval(2*horizontalOffset + counter*userSpacing + userSize/2, verticleUserOffset, userSize, userSize);
			g.drawString(label, 2*horizontalOffset + counter*userSpacing + userSize/2, verticleUserOffset - 15);
			
			//place the User in the hashmap of Users
			userPos.put(u, new Pos( 2*horizontalOffset + counter*userSpacing + userSize, verticleUserOffset + userSize));
			
			//set up for next user
			g.setColor(Color.BLUE);
			counter++;
		}
		
		//reset for doc drawing
		counter = 0;
		g.setColor(Color.MAGENTA);
		
		//draw the documents all the same color
		for(Document d: docs)
		{
			String label = d.toString();		//create the label	
			g.fillRect(2*horizontalOffset + counter*docSpacing + docSize/2, verticleDocumentOffset, docSize, docSize);
			
			//if less than 15 documents display the name of the document
			if(docs.size() < 15){
			g.drawString(label, 2*horizontalOffset + counter*docSpacing + docSize/2, verticleDocumentOffset + 20 + docSize);
			}
			
			//place the document in the hashmap of Docs
			docPos.put(d, new Pos( 2*horizontalOffset + counter*docSpacing + docSize, verticleDocumentOffset));
			counter++;
		}
		
		//black lines
		g.setColor(Color.black);
		
		//draw lines
		for(Document d: docs)
		{
			for (User u: d.getLikedUsers()) {
				g.drawLine(docPos.get(d).getX(), docPos.get(d).getY(), userPos.get(u).getX(), userPos.get(u).getY());
			}
		}
		
		
	}
	
	
	
}
