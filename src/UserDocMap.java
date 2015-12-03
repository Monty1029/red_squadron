import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class UserDocMap extends JPanel{

	private JFrame frame;
	private List<User> users;
	private List<Document> docs;
	private HashMap<User, Pos> userPos;
	private HashMap<Document, Pos> docPos;
	
	private int verticleUserOffset = 30;
	private int verticleDocumentOffset = 500;
	private int horizontalOffset = 20;
	private int size = 70;
	private int userSpacing, docSpacing;
	
	
	/**
	 * 
	 * @param users list of all users
	 * @param docs list of all documents
	 * @param frame the JFrame to draw in
	 */
	public UserDocMap(List<User> users, List<Document> docs, JFrame frame) 
	{
		//set required references
		this.users = users;
		this.docs = docs;
		this.frame = frame;
		userPos = new HashMap<>();
		docPos = new HashMap<>();
		
		//calculate spacings
		userSpacing = (frame.getWidth() - 2*horizontalOffset)/users.size();
		docSpacing = (frame.getWidth() - 2*horizontalOffset)/docs.size();
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
		
		//loop through the users and draw their circles and determine their line positions
		for(User u: users){
			
			g.drawOval(2*horizontalOffset + counter*userSpacing + size/2, verticleUserOffset, size, size);
			userPos.put(u, new Pos( 2*horizontalOffset + counter*userSpacing + size, verticleUserOffset + size));
			//g.drawLine(userPos.get(u).getX(), userPos.get(u).getY(), dubWidth.intValue()/2, dub.intValue()/2);		//check for positions being created properly
			counter++;
		}
		
		counter = 0;
		
		
		for(Document d: docs)
		{
			//g.drawOval(2*horizontalOffset + counter*userSpacing + size/2, verticleDocumentOffset, size, size);
			g.drawRect(2*horizontalOffset + counter*docSpacing + size/2, verticleDocumentOffset, size, size);
			docPos.put(d, new Pos( 2*horizontalOffset + counter*docSpacing + size, verticleDocumentOffset));
			//g.drawLine(docPos.get(d).getX(), docPos.get(d).getY(), dubWidth.intValue()/2, dub.intValue()/2);		//check for positions being created properly
			counter++;
		}
		for(Document d: docs)
		{
			for (User u: d.getLikedUsers()) {
				g.drawLine(docPos.get(d).getX(), docPos.get(d).getY(), userPos.get(u).getX(), userPos.get(u).getY());
			}
		}
		
		
	}
	
	
	
}
