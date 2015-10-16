//Author: Bronwyn Skelley
//Version:1 Date: October 12th
public class Document {
	private int like;
	private String name;
	private String tag;
	
	
	//Contrsuctor
	
	public Document(String name, String tag){
		this.name = name;
		this.tag = tag;
	}
	
	
	//accessor methods
	public String getTag(){
		return this.tag;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getLikes(){
		return this.like;
	}
	
	//increases like count of document
	public void likeDoc(){
		this.like++;
	}
}
