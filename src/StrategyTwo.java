import java.util.*;

public class StrategyTwo implements Strategy {
	public StrategyTwo() {

	}

	public List<Document> rank(User u, List<Document> docs, int n) {
		HashSet<User> likedUsers = new HashSet<User>();
		HashMap<Document, Integer> docLikes = new HashMap<Document, Integer>();
		LinkedHashMap<Document, Integer> sortedMap = new LinkedHashMap<Document, Integer>();
		ArrayList<Document> sorted = new ArrayList<Document>();
		for (Document d : docs) {
			likedUsers = d.getLikedUsers();
			int userFollowers = 0;
			for (User lu : likedUsers) {
				userFollowers += lu.getFollowed();
			}
			docLikes.put(d, userFollowers);
		}
		sortedMap = (LinkedHashMap<Document, Integer>) sortDocuments(docLikes);
		sorted.addAll(sortedMap.keySet());
		for (int i=sorted.size(); i>n; i--) {
			sorted.remove(i);
		}
		return sorted;
	}

	public Map<Document, Integer> sortDocuments(Map<Document, Integer> unsortedMap) {
		List<Map.Entry<Document, Integer>> list = new LinkedList<Map.Entry<Document, Integer>>(unsortedMap.entrySet());

		// Sort list with comparator, to compare the Map values
		Collections.sort(list, new Comparator<Map.Entry<Document, Integer>>() {
			public int compare(Map.Entry<Document, Integer> o1, Map.Entry<Document, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// Convert sorted map back to a Map
		Map<Document, Integer> sortedMap = new LinkedHashMap<Document, Integer>();
		for (Iterator<Map.Entry<Document, Integer>> it = list.iterator(); it.hasNext();) {
			Map.Entry<Document, Integer> entry = it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
}
