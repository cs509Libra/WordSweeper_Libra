package client.model;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TestModel {

	Model testmodel1=new Model();
//updateInfo and startPracticeGame in model is related to private attributes,
//so we can't test it.
//any ideas???

	@Test
	public void TestGameinModel()
	//the test for set&get game in model
	{
		Game testGame1=new Game();
		testGame1.setGameID("testGame1");
		testGame1.setLocked(true);
		testGame1.setManagingUser("Mike");
		Map<String, Integer> playersInfoMap1 = new HashMap<String, Integer>();
		playersInfoMap1.put("Mike", 150);
		playersInfoMap1.put("Lisa",1500);
		playersInfoMap1.put("Mary",3000);
		testGame1.setPlayersInfoMap(playersInfoMap1);
		testmodel1.setGame(testGame1);
		assertEquals(testGame1,testmodel1.getGame());
	}
	
	@Test
	public void TestBoardinModel()
	//the test for set&get board in model and part of reset game
	{
		Board testBoard1=new Board();
		testBoard1.setBonusCell("E");
		testBoard1.addToChosenCellsByCellIndex(5);
		System.out.print(testBoard1.getChosenCellsLetters());
		testmodel1.setBoard(testBoard1);
		assertEquals(testBoard1,testmodel1.getBoard());
		testmodel1.resetGame();
		testBoard1.empltyChosenCells();
		assertEquals(testBoard1,testmodel1.getBoard());
	}
	
	@Test
	public void TestPlayerinModel()
	//the test for set&get player in model and part of reset game
	{
		Player testPlayer1=new Player();
		testPlayer1.setAsManager();
		testPlayer1.setName("Mike");
		testPlayer1.setScore(1234);
		testmodel1.setPlayer(testPlayer1);
		assertEquals(testPlayer1,testmodel1.getPlayer());
		testmodel1.resetGame();
		testPlayer1.setScore(0);
		assertEquals(testPlayer1,testmodel1.getPlayer());
		
	}
}
//end of TestModel