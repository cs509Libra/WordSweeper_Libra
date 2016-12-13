package client.view;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import javax.swing.JButton;

import org.junit.Before;
import org.junit.Test;

import client.MockServerAccess;
import client.controller.responseController.BoardResponseController;
import client.model.Model;
import xml.Message;

public class TestGUI {
	Model model = new Model();
	Application app = new Application(model);
	MockServerAccess mockServer = new MockServerAccess("localhost");

	@Before
	public void set() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail("unable to configure protocol");
		}
		app.setVisible(true);
		app.setServerAccess(mockServer);
	}

	@Test
	public void gui() {
		String name = "player1";
		int col = 2, row = 1;
		String bonus = "3,3";
		String gameId = "idid";
		String managingUser = name;
		String board = "A,B,A,B,A,B,B,B,C,B,C,D,B,A,B,R";
		String boardm = "ABABABBBCBCDBABR";
		String pos = col + "," + row;
		int score = 100;
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"someMessageID\" success=\"true\">"
				+ "<boardResponse bonus=\"%s\" gameId=\"%s\" managingUser=\"%s\">"
				+ "<player board=\"%s\" name=\"%s\" position=\"%s\" score=\"%s\"/>" + "</boardResponse></response>";
		xml = String.format(xml, bonus, gameId, managingUser, board, name, pos, score);
		model.updateInfo("something different", "I don't know", "haha", 6, 7, "A,B,C,D,F,E,E,G,J,I,J,O,P,B,I,M", 55,
				"6,6");
		model.getPlayer().setName(name);

		Message m = new Message(xml);
		new BoardResponseController(app, model).process(m);
		app.setMg(new MultiGame(model, app));
		app.getMg().setVisible(true);
		LeftBoardPanel left = (LeftBoardPanel) app.getMultiGame().getLeftBoardPanel();
		RightGameInfoBoard right = (RightGameInfoBoard) app.getMultiGame().getRightGameInfoPanel();
		left.refreshBoard();
		right.updateGameInfoBoard();
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"a77b2344-a6bc-4dc2-ad88-f93f2fd8f038\" success=\"true\" version=\"1.0\"><boardResponse bonus=\"1,2\" contents=\"\" gameId=\"cdbopkk\" managingUser=\"player1\" size=\"7\"><player board=\"D,A,A,O,E,T,E,I,Y,O,N,A,K,A,O,T\" name=\"player1\" position=\"1,3\" score=\"0\"/><player board=\"A,O,T,A,E,I,C,L,N,A,T,K,O,T,A,S\" name=\"player2\" position=\"3,3\" score=\"0\"/></boardResponse></response>";
		m = new Message(xml);
		new BoardResponseController(app, model).process(m);
		left.refreshBoard();
		right.updateGameInfoBoard();

		left.btnDown.doClick();
		left.btnLeft.doClick();
		left.btnRight.doClick();
		left.btnUp.doClick();

		ArrayList<JButton> chosenCells = left.getAllCellBtns();
		chosenCells.get(0).doClick();
		chosenCells.get(1).doClick();
		chosenCells.get(4).doClick();
		left.clear.doClick();

		chosenCells.get(0).doClick();
		left.submit.doClick();
		left.clear.doClick();

		chosenCells.get(0).doClick();
		chosenCells.get(1).doClick();
		left.submit.doClick();
	}

}
