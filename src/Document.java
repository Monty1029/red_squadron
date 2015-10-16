
import java.util.HashSet;

//Author: Bronwyn Skelley
//Version:1 Date: October 12th
public class Document implements Comparable<Document> {
	private int like;
	private String name;
	private String tag;
	private HashSet<User> list;
	
	
	/**
	 * Constructor using name and tag to make document
	 * @param name this is name of document
	 * @param tag this is tag of document
	 */
	public Document(String name, String tag){
		this.name = name;
		this.tag = tag;
		list = new HashSet<User>();
	}
	
	
	/**
	 * gets the tag of document
	 * @return tag of document 
	 */
	public String getTag(){
		return this.tag;
	}
	
	/**
	 * gets name of document
	 * @return name of document
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * gets how many times the document has been liked
	 * @return how many times the document has been liked
	 */
	public int getLikes(){
		return this.like;
	}
	
	
	/**
	 * increments by one the likes of the document
	 * @param u the user liking the document
	 */
	public void likeDoc(User u){
		if(!list.contains(u)){this.like++;list.add(u);}
	}

	/**
	 * determiens if user has liked document before
	 * @param u the user checking if it likes it
	 * @return if the user has liked it
	 */
	public boolean hasLiked(User u){
		return list.contains(u);
	}

	/**
	 * comparison method
	 * @param arg0 the document to compare to
	 * @return
	 */
	public int compareTo(Document arg0) {
		return arg0.getLikes() - this.getLikes() ;
	}
}
