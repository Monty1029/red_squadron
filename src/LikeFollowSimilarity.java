import java.util.ArrayList;
import java.util.List;

/**
 * look through people I follow, like any documents they have liked that I haven't, 
 * and if my people i follow follow others, follow them
 * @author Bronwyn
 *
 */
public class LikeFollowSimilarity implements Strategy {

	/**
	 * look through people I follow, like any documents they have liked that I haven't, 
	 * and if my people i follow follow others, follow them
	 */
	public List<Document> rank(User u, List<Document> docs, int n){
		ArrayList<Document> newRank = new ArrayList<Document>();
		
		
		
		for(User user: u.getFollowing()){
			ArrayList<Document> liked= 	u.getSim().getHash().get(user);
			if(liked == null){return newRank;}
			
			for(Document d: liked){
				d.likeDoc(u);
				if(newRank.size() < n){
					newRank.add(d);
				}
				else{
					return newRank;
				}
			}
			ArrayList<User> follows = (ArrayList<User>) user.getFollowing();
			for(User newUser: follows){
				u.follow(newUser);
			}
		}
		
		return newRank;
	}
	
	
	/**
	 * Overwrite, gives strategy name
	 */
	public String toString(){ return "Like/Follow Similarity";}
	
	
}
