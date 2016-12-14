package client.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Game entity class, which contains all the info and functions about the game.  
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class Game {

	private String gameID;
	private String managingUser;
	private Map<String, Integer> playersInfoMap;
	private Map<String, String> playersPositionMap;
	private boolean isLocked;

	/**Game constructor*/
	public Game() {
		gameID = "";
		managingUser = null;
		isLocked = false;
		playersInfoMap = new HashMap<String, Integer>();
		;
	}

	public Map<String, Integer> getPlayersInfoMap() {
		return playersInfoMap;
	}

	public void setPlayersInfoMap(Map<String, Integer> playersInfoMap) {
		this.playersInfoMap = playersInfoMap;
	}

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public String getManagingUser() {
		return managingUser;
	}

	public void setManagingUser(String managingUser) {
		this.managingUser = managingUser;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * Sort the player list by join time, player who joins early will be on the front part.  
	 * @return String
	 */
	public String getPlayersListByJoinTime() {
		String allPlayersList = "";
		int i = 1;
		for (Map.Entry<String, Integer> playerInfoSet : playersInfoMap.entrySet()) {
			allPlayersList += (String.format("  %s\t", String.valueOf(i++)) + playerInfoSet.getKey() + "\t  "
					+ playerInfoSet.getValue().toString() + "\n");
		}
		System.out.println(allPlayersList);
		return allPlayersList;
	}

	/**
	 * Sort the player list by temporary total score in descending order.  
	 * @return String
	 */
	public String getPlayersListByScore() {
		String allPlayersInfoSortedByScore = "";

		List<Entry<String, Integer>> allPlayersListSortedByScore = new LinkedList<Entry<String, Integer>>(this.playersInfoMap.entrySet());
		Collections.sort(allPlayersListSortedByScore, new Comparator<Object>() {
			@Override
			@SuppressWarnings("unchecked")
			public int compare(Object o1, Object o2) {
				return ((Comparable<Integer>) ((Map.Entry<String, Integer>) (o2)).getValue())
						.compareTo(((Map.Entry<String, Integer>) (o1)).getValue());
			}
		});

		for (int i = 0; i < allPlayersListSortedByScore.size(); i++) {
			allPlayersInfoSortedByScore += (String.format("  %s\t", String.valueOf(i + 1))
					+ allPlayersListSortedByScore.get(i).getKey() + "\t  "
					+ allPlayersListSortedByScore.get(i).getValue().toString() + "\n");
		}

		return allPlayersInfoSortedByScore;
	}
    
	/**
	 * Sort the player list by letters of name in order of ascll.  
	 * @return String
	 */
	public String getPlayersListByName() {
		String allPlayersInfoSortedByName = "";

		List<Entry<String, Integer>> allPlayersListSortedByScore = new LinkedList<Entry<String, Integer>>(this.playersInfoMap.entrySet());
		Collections.sort(allPlayersListSortedByScore, new Comparator<Object>() {
			@Override
			@SuppressWarnings("unchecked")
			public int compare(Object o1, Object o2) {
				return ((Comparable<String>) ((Map.Entry<String, Integer>) (o1)).getKey())
						.compareTo(((Map.Entry<String, Integer>) (o2)).getKey());
			}
		});

		for (int i = 0; i < allPlayersListSortedByScore.size(); i++) {
			allPlayersInfoSortedByName += (String.format("  %s\t", String.valueOf(i + 1))
					+ allPlayersListSortedByScore.get(i).getKey() + "\t  "
					+ allPlayersListSortedByScore.get(i).getValue().toString() + "\n");
		}

		return allPlayersInfoSortedByName;
	}

	public Map<String, String> getPlayersPositionMap() {
		return playersPositionMap;
	}

	public void setPlayersPositionMap(Map<String, String> playersPositionMap) {
		this.playersPositionMap = playersPositionMap;
	}

}