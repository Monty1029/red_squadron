
import java.util.HashSet;

//Author: Bronwyn Skelley
//Version:1 Date: October 12th

/**
 * A Document object, that has a tag, name, and people who like it
 * @author Bronwyn Skelley
 *
 */
public class Document implements Comparable<Document> {
	private int like;
	private String name;
	private String tag;
	private HashSet<User> list;
	private Producer producer; 																//TODO
	
	/**
	 * Constructor using name and tag to make document
	 * @param name this is name of document
	 * @param tag this is tag of document
	 */
	public Document(String name, String tag, Producer producer){							//TODO
		if(name!=null){
			this.name = name;
		}
		else{
			this.name = "none";
		}
		if(tag != null){
			this.tag = tag;
		}else{
			this.tag = "none";
		}

		this.producer = producer;															//TODO
		list = new HashSet<User>();
	}
	

	
	
	/**
	 * Gets the tag of document
	 * @return tag of document 
	 */
	public String getTag(){
		return this.tag;
	}
	
	/**
	 * Gets name of document
	 * @return name of document
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Gets how many times the document has been liked
	 * @return how many times the document has been liked
	 */
	public int getLikes(){
		return this.like;
	}
	
	
	/**
	 * Increments by one the likes of the document
	 * @param u the user liking the document
	 */
	public void likeDoc(User u){
		if(!list.contains(u)){this.like++;list.add(u); producer.incrementPayoff();}			//TODO
	}

	/**
	 * Determines if user has liked document before
	 * @param u the user checking if it likes it
	 * @return if the user has liked it
	 */
	public boolean hasLiked(User u){
		
		if(u == null){System.out.println("user is null");}
		if(list == null){System.out.println("list is null");}
		
		return list.contains(u);
	}
	
	/**
	 * Gets the users who have liked the document
	 * @return the HashSet of users who like the document
	 */
	public HashSet<User> getLikedUsers()
	{
		return list;
	}

	/**
	 * Comparison method
	 * @param arg0 the document to compare to
	 * @return comparison value
	 */
	public int compareTo(Document arg0) {
		return arg0.getLikes() - this.getLikes() ;
	}
	
	/**
	 * Overwrite of the toString method()
	 */
	public String toString(){
		return name;
	}
	
}
