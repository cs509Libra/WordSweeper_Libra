package client.model;


import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;



public class TestGame {
	Game game1=new Game();

	@Test
	public void TestPlayerInforMap()
	//the test for set&get Players Information map function, it's used HashMap
	{
		Map<String, Integer> playersInfoMap1 = new HashMap<String, Integer>();
		playersInfoMap1.put("Mike", 150);
		playersInfoMap1.put("Lisa",1500);
		playersInfoMap1.put("Mary",3000);
		game1.setPlayersInfoMap(playersInfoMap1);
		assertEquals(playersInfoMap1,game1.getPlayersInfoMap());
	}
	
	@Test
	public void TestGameID()
	//the test for set&get Game ID function
	{
		assertEquals("",game1.getGameID());
		game1.setGameID("game1");
		assertEquals("game1",game1.getGameID());
	}
	
	@Test
	public void TestManagingUser()
	//the test for set&get managing user function
	{
		assertEquals(null,game1.getManagingUser());
		game1.setManagingUser("Mike");
		assertEquals("Mike",game1.getManagingUser());
	}
	
	@Test
	public void TestLockGame()
	//the test for lock game function
	{
		assertEquals(false,game1.isLocked());
		game1.setLocked(true);
		assertEquals(true,game1.isLocked());
	}
	
	@Test
	public void TestGetPlayersByTime()
	//the test for get the players' names by joining time function, the users 
	//will be arranged by the time they joined. The earlier they joined, the
	//more front his/her name will be
	{
		assertEquals("",game1.getPlayersListByJoinTime());
		Map<String, Integer> playersInfoMap1 = new HashMap<String, Integer>();
		playersInfoMap1.put("Mike", 150);
		playersInfoMap1.put("Lisa",11000);
		playersInfoMap1.put("Mary",3000);
		game1.setPlayersInfoMap(playersInfoMap1);
		System.out.print(game1.getPlayersListByJoinTime());
	}
	
	@Test
	public void TestGetPlayersByScore()
	//the test for get players' names by scores function. the higher score
	//the player get, the more front his/her name will be
	{
		assertEquals("",game1.getPlayersListByScore());
		Map<String, Integer> playersInfoMap1 = new HashMap<String, Integer>();
		playersInfoMap1.put("Mike", 150);
		playersInfoMap1.put("Lisa",11000);
		playersInfoMap1.put("Mary",3000);
		game1.setPlayersInfoMap(playersInfoMap1);
		System.out.print(game1.getPlayersListByScore());
	}
	
	@Test
	public void TestGetPlayersByName()
	//the test for get players' names by their name, arranged by alphabet
	{
		assertEquals("",game1.getPlayersListByName());
		Map<String, Integer> playersInfoMap1 = new HashMap<String, Integer>();
		playersInfoMap1.put("Mike", 150);
		playersInfoMap1.put("Lisa",11000);
		playersInfoMap1.put("Nancy",3000);
		game1.setPlayersInfoMap(playersInfoMap1);
		System.out.print(game1.getPlayersListByName());
	}
	
	@Test
	public void TestPlayersPosition()
	//the test for set&get players position map, also using HashMap, the 
	//key is string, and the value is tring too
	{
		Map<String,String> playersPositionMap =new HashMap<String,String>();
		playersPositionMap.put("4", "5");
		game1.setPlayersPositionMap(playersPositionMap);
		assertEquals(playersPositionMap,game1.getPlayersPositionMap());
	}
}
//end of TestGame