//Name: Garrett Steele
//Date: Oct 9, 2015
//Class: SYSC3110 - Software Development Project
//Git Repository: redSquadron

	//////////////////////////////////////////////
	//											//
	//					Imports					//
	//											//
	//////////////////////////////////////////////

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * Abstract class with Consumer and Producer inheriting from class User
 */
public abstract class User {

	
	//////////////////////////////////////////////
	//											//
	//			Private Variables				//
	//											//
	//////////////////////////////////////////////
	
	private String username;			//name of the user, not unique to a user
	private String taste;				//a single tag they are interested in
	private HashSet<User> following;		//list of users this user is following, list is implemented as an ArrayList
	private int followed;				//number of users this user has followed
	private Simulation sim;
	
	//////////////////////////////////////////////
	//											//
	//				Abstract Methods			//
	//											//
	//////////////////////////////////////////////
	
	/**
	 * Method to separately define the action for each subclass of User
	 * @param documents list of Document objects to perform user's action with
	 */
	public abstract void act(List<Document> documents);
	
	/**
	 * Method to separately define the payoff calculation for each subclass of User
	 * @param documents list of Document objects to perform user's action with
	 */
	public abstract void payoff(List<Document> documents);
	
	
	//////////////////////////////////////////////
	//											//
	//				Constructors				//
	//											//
	//////////////////////////////////////////////
	
	/**
	 * Constructor for when
	 * @param username the username to assign this user
	 * @param taste the tag the user is most interested in
	 */
	User(String username, String taste, Simulation sim)
	{
		this.username = username;			//set user's username
		this.taste = taste;					//set user's taste
		following = new HashSet<>();		//empty ArrayList to maintain who they follow
		followed = 0;						//user has not followed anyone as soon as the account is created
		this.sim = sim;
	}
	
	/**
	 * Default constructor, setting the username and taste to "none" as no parameters are passed, call stronger constructor
	 */
	User(Simulation sim){this("none","none", sim);}
	
	
	//////////////////////////////////////////////
	//											//
	//				Accessors					//
	//											//
	//////////////////////////////////////////////
	
	/**
	 * Method to access the username of the User object
	 * @return the username of the User object
	 */
	public String getName(){return username;}
	
	/**
	 * Method to access the preferred taste of the User object
	 * @return the preferred taste of the User object
	 */
	public String getTaste(){return taste;}
	
	/**
	 * Method to access the list of Users the User is following, as an ArrayList
	 * @return a new ArrayList of the Users the User is following
	 */
	public List getFollowing(){return new ArrayList<User>(following);}
	
	
	//////////////////////////////////////////////
	//											//
	//			Concrete Methods				//
	//											//
	//////////////////////////////////////////////
	
	/**
	 * Method to add a User to the list of followed Users
	 * @param user the User that the user the method is called on wishes to follow
	 */
	public void follow(User user){following.add(user);}
	
	/**
	 * If the User is followed, increment the number of people who have followed this User
	 */
	public void wasFollowed(){followed++;}
	
	/**
	 * Method to determine if the User has liked any documents
	 * @return whether this user has liked any documents
	 */
	public boolean hasLiked()
	{
		if(this.sim.getHash().get(this) != null){return true;}
		return false;
	}
	
}