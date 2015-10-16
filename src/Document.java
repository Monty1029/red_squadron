//Author: Bronwyn Skelley
//Version:1 Date: October 12th
public class Document {
	private int like;
	private String name;
	private String tag;
	
	
	/**
	 * Constructor using name and tag to make document
	 * @param name this is name of document
	 * @param tag this is tag of document
	 */
	public Document(String name, String tag){
		this.name = name;
		this.tag = tag;
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
	 */
	public void likeDoc(){
		this.like++;
	}
}
