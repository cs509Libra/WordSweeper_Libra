package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import client.controller.MGMouseListener;
import client.controller.requestController.FindWordController;
import client.controller.requestController.RepositionBoardController;
import client.model.Model;
import util.CalculateLocalScore;

public class LeftBoardPanel extends JPanel {

	private Model model;
	private Application app;
	private RightGameInfoBoard rightGameInfoBoard;

	private JLabel messageLabel;
	private JTextField localExpectedScoreField;
	private JTextField scoreFromServerField;
	private JTextField submissionField;
	private JPanel boardview;

	private ArrayList<JButton> chosenCellBtns;
	private ArrayList<JButton> allCellBtns;

	public LeftBoardPanel(Model model, Application app, JPanel rightGameInfoBoard) {
		this.model = model;
		this.app = app;
		this.rightGameInfoBoard = (RightGameInfoBoard) rightGameInfoBoard;
		initiate();
	}

	private void initiate() {
		setBounds(5, 5, 370, 470);
		setLayout(null);

		JButton btnUp = new JButton("^");
		btnUp.addMouseListener(new MGMouseListener() {
			int previousRow;
			int newRow;

			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousRow = model.getBoard().getGlobalStartingRow();
				model.getBoard().setRequestRowChange(-1);
				new RepositionBoardController(app, model).process();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();
				newRow = model.getBoard().getGlobalStartingRow();
				if (previousRow == newRow) {
					messageLabel.setText("No More Up!");
				}
			}
		});

		btnUp.setBounds(130, 5, 100, 40);
		btnUp.setEnabled(true);
		add(btnUp);

		JButton btnLeft = new JButton("<");
		btnLeft.addMouseListener(new MGMouseListener() {
			int previousCol;
			int newCol;

			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousCol = model.getBoard().getGlobalStartingCol();
				model.getBoard().setRequestColChange(-1);
				new RepositionBoardController(app, model).process();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();
				newCol = model.getBoard().getGlobalStartingCol();
				if (previousCol == newCol) {
					messageLabel.setText("No More Left!");
				}
			}
		});
		btnLeft.setBounds(10, 110, 45, 100);
		btnLeft.setEnabled(true);
		add(btnLeft);

		JButton btnRight = new JButton(">");
		btnRight.addMouseListener(new MGMouseListener() {
			int previousCol;
			int newCol;

			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousCol = model.getBoard().getGlobalStartingCol();
				model.getBoard().setRequestColChange(1);
				new RepositionBoardController(app, model).process();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();
				newCol = model.getBoard().getGlobalStartingCol();
				if (previousCol == newCol) {
					messageLabel.setText("No More Right!");
				}
			}
		});
		btnRight.setBounds(320, 110, 45, 100);
		btnRight.setEnabled(true);
		add(btnRight);

		JButton btnDown = new JButton("v");
		btnDown.addMouseListener(new MGMouseListener() {
			int previousRow;
			int newRow;

			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousRow = model.getBoard().getGlobalStartingRow();
				model.getBoard().setRequestRowChange(1);
				new RepositionBoardController(app, model).process();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();
				newRow = model.getBoard().getGlobalStartingRow();
				if (previousRow == newRow) {
					messageLabel.setText("No More Down!");
				}
			}
		});
		btnDown.setBounds(130, 285, 100, 40);
		btnDown.setEnabled(true);
		add(btnDown);

		boardview = new JPanel();
		boardview.setBackground(Color.WHITE);
		boardview.setBorder(new LineBorder(Color.WHITE));
		boardview.setBounds(70, 50, 230, 234);
		boardview.setLayout(new GridLayout(4, 4, 0, 0));
		add(boardview);

		chosenCellBtns = new ArrayList<JButton>();
		addAllCellBtnsToCellBtnsList();
		refreshBoard();

		messageLabel = new JLabel();
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		messageLabel.setBounds(100, 340, 200, 21);
		messageLabel.setText("");
		messageLabel.setForeground(Color.RED);
		add(messageLabel);

		JLabel localExpectedScoreLabel = new JLabel("local Score:");
		localExpectedScoreLabel.setBounds(30, 370, 105, 21);
		add(localExpectedScoreLabel);

		localExpectedScoreField = new JTextField("0");
		localExpectedScoreField.setBounds(100, 370, 70, 21);
		localExpectedScoreField.setEditable(false);
		localExpectedScoreField.setColumns(10);
		add(localExpectedScoreField);

		JLabel scoreFromServerLabel = new JLabel("Server Score:");
		scoreFromServerLabel.setBounds(210, 370, 105, 21);
		add(scoreFromServerLabel);

		scoreFromServerField = new JTextField("0");
		scoreFromServerField.setBounds(290, 370, 80, 21);
		scoreFromServerField.setEditable(false);
		scoreFromServerField.setColumns(10);
		add(scoreFromServerField);

		JLabel submissionBar = new JLabel("Submission Bar:");
		submissionBar.setBounds(30, 400, 105, 21);
		add(submissionBar);

		submissionField = new JTextField();
		submissionField.setBounds(169, 400, 130, 21);
		submissionField.setEditable(false);
		submissionField.setColumns(10);
		add(submissionField);

		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllChosen();
				overlapArea();
				changeBonusColor();
			}
		});
		clear.setBackground(Color.WHITE);
		clear.setForeground(Color.RED);
		clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear.setToolTipText("Clear all the letters!");
		clear.setBounds(69, 430, 93, 23);
		add(clear);

		JButton submit = new JButton("Submit");
		submit.addMouseListener(new MGMouseListener() {
			long playerPreviousScore;
			long playerNewScore;
			Integer localExpectedWordScore;

			@Override
			public void mousePressed(MouseEvent e) {

				// clear information shown in gameLockResetLabel on the
				// rightBoard
				if (!model.getGame().isLocked()) {
					rightGameInfoBoard.gameLockResetLabel.setText("");
				}
				// calculate local word score.
				String word = submissionField.getText();
				if (word.length() <= 1) {
					messageLabel.setText("Choose at least 2 letters");
					return;
				}
				localExpectedWordScore = calculateWordScoreFromLocalLib(word);
				localExpectedScoreField.setText(localExpectedWordScore.toString());

				// remove all chosen btns information
				chosenCellBtns.removeAll(chosenCellBtns);
				submissionField.setText("");
				refreshBoard();

				// call findWordRequestController
				playerPreviousScore = model.getPlayer().getScore();
				new FindWordController(app, model).process();
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				playerNewScore = model.getPlayer().getScore();
				if (playerNewScore == playerPreviousScore) {
					messageLabel.setText("Word Picked By Others");
				} else {
					String wordScoreFromServer = String.valueOf(playerNewScore - playerPreviousScore);
					scoreFromServerField.setText(wordScoreFromServer);
					if (localExpectedWordScore != Integer.valueOf(wordScoreFromServer)) {
						messageLabel.setText("You get a bonus");
					}
				}
				rightGameInfoBoard.updateGameInfoBoard();
				clearAllChosen();
			}
		});
		;

		submit.setToolTipText("submit the word you have chosen");
		submit.setForeground(Color.GREEN);
		submit.setFont(new Font("Tahoma", Font.BOLD, 12));
		submit.setBackground(Color.WHITE);
		submit.setBounds(198, 430, 93, 23);
		add(submit);

	}

	public void refreshBoard() {
		char[] LettersToBeAdd = this.model.getBoard().getBoardInfo().toCharArray();
		int j = 0;
		for (int i = 0; i < 16; i++) {
			String cellToBeAdded = String.valueOf(LettersToBeAdd[i + j]);
			if (cellToBeAdded.equals("Q")) {
				cellToBeAdded = "Qu";
				j += 1;
			}
			this.allCellBtns.get(i).setText(cellToBeAdded);
		}
		removeCellBtnsColors();
		overlapArea();
		changeBonusColor();
		boardview.repaint();
	}

	private void removeCellBtnsColors() {
		for (int i = 0; i < 16; i++) {
			Component c = this.boardview.getComponent(i);
			c.setBackground(Color.WHITE);
		}
		boardview.repaint();
	}

	private void clearAllChosen() {
		messageLabel.setText("");
		localExpectedScoreField.setText("0");
		submissionField.setText("");
		chosenCellBtns.removeAll(chosenCellBtns);
		model.getBoard().clearChosenCells();
		removeCellBtnsColors();
	}

	private void addAllCellBtnsToCellBtnsList() {

		ActionListener cellChosenListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();
				JButton chosenBtn = (JButton) source;
				if (!isCellValidateToChoose(chosenBtn)) {
					messageLabel.setText("Cell chosen not valid!");
					return;
				}
				messageLabel.setText("");
				chosenBtn.setBackground(Color.RED);

				int indexOfThisBtnInAll = allCellBtns.indexOf(chosenBtn);
				if (isBonusCell(indexOfThisBtnInAll))
					chosenBtn.setBackground(Color.ORANGE);
				System.out.println(indexOfThisBtnInAll);

				// Update model chosen cells info for submission purpose.
				model.getBoard().addToChosenCellsByCellIndex(indexOfThisBtnInAll);
				System.out.println("chosenLetterfromModel:" + model.getBoard().getChosenCellsLetters());
				System.out.println("word is: " + model.getBoard().getWord().getContent());

				// Update GUI chosenCellBtns for display purpose (maintain this
				// bks everything is local).
				chosenCellBtns.add(chosenBtn);

				String lett = chosenBtn.getText();
				String lettDisplay = obtainChosenLettDisplay(chosenCellBtns);
				submissionField.setText(lettDisplay);
				String previousScore = localExpectedScoreField.getText();
				Integer currentScore = CalculateLocalScore.calculateLetterScore(lett);
				Integer newScore = currentScore + Integer.parseInt(previousScore);
				localExpectedScoreField.setText(newScore.toString());
			}
		};

		this.allCellBtns = new ArrayList<JButton>();
		for (int i = 0; i < 16; i++)
			this.allCellBtns.add(new JButton());

		for (JButton cellBtn : allCellBtns) {
			cellBtn.addActionListener(cellChosenListener);
			cellBtn.setBackground(Color.WHITE);
			this.boardview.add(cellBtn);
		}
		this.overlapArea();
		this.changeBonusColor();
	}

	/**
	 * Extract letters of chosenCells in order, and convert to string for
	 * display
	 * 
	 * @param chosenCells
	 * @return
	 */
	private String obtainChosenLettDisplay(ArrayList<JButton> chosenCells) {
		String lettDisplay = "";
		for (JButton tempbtn : chosenCells) {
			String s = tempbtn.getText();
			lettDisplay += s;
		}
		return lettDisplay;
	}

	/**
	 * Determine if a button clicked is valid.
	 * 
	 * @param tempBtn
	 * @return
	 */
	private boolean isCellValidateToChoose(JButton tempBtn) {
		if (this.chosenCellBtns.size() == 0) {
			return true;
		}
		int indexOfThisBtnInAll = this.allCellBtns.indexOf(tempBtn);
		JButton previousChosenButton = this.chosenCellBtns.get(this.chosenCellBtns.size() - 1);
		int indexOfPreviousBtnInAll = this.allCellBtns.indexOf(previousChosenButton);
		if (isAdjacent(indexOfPreviousBtnInAll, indexOfThisBtnInAll) && !hasBeenChosen(tempBtn)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * One sub_method to determine if a button clicked is valid. Determine if
	 * the clicked button has been chosen before or not.
	 * 
	 * @param tempBtn
	 * @return
	 */
	private boolean hasBeenChosen(JButton tempBtn) {
		for (JButton previous : this.chosenCellBtns) {
			if (tempBtn.equals(previous)) {
				return true;
			}
		}
		return false;
	}

	private boolean isBonusCell(int index) {
		String bonusCell = this.model.getBoard().getBonusCell();
		int x = Character.getNumericValue(bonusCell.charAt(0)) - this.model.getBoard().getCol() + 1;
		int y = Character.getNumericValue(bonusCell.charAt(2)) - this.model.getBoard().getRow() + 1;
		int i = -1;
		if (x >= 1 && x <= 4 && y >= 1 && y <= 4)
			i = (y - 1) * 4 + x - 1;
		if (i == index)
			return true;
		else
			return false;

	}

	private void changeBonusColor() {
		String bonusCell = this.model.getBoard().getBonusCell();
		int x = Character.getNumericValue(bonusCell.charAt(0)) - this.model.getBoard().getCol() + 1;
		int y = Character.getNumericValue(bonusCell.charAt(2)) - this.model.getBoard().getRow() + 1;
		if (x >= 1 && x <= 4 && y >= 1 && y <= 4) {
			int index = (y - 1) * 4 + x - 1;
			this.allCellBtns.get(index).setBackground(Color.YELLOW);
		}
	}

	private void overlapArea() {
		if (this.model.getBoard().positions.size() == 0)
			return;
		int x = 0;
		int y = 0;
		ArrayList<Integer> index;
		int[] times = new int[16];
		for (int i = 0; i < 16; i++)
			times[i] = 0;
		for (String str : this.model.getBoard().positions) {
			index = new ArrayList<Integer>();
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++) {
					x = Character.getNumericValue(str.charAt(0)) - this.model.getBoard().getCol() + 1 + i;
					y = Character.getNumericValue(str.charAt(2)) - this.model.getBoard().getRow() + 1 + j;
					if (x >= 1 && x <= 4 && y >= 1 && y <= 4) {
						index.add((y - 1) * 4 + x - 1);
					}
				}
			for (Integer n : index) {
				this.allCellBtns.get(n)
						.setBackground(new Color(255 - times[n] * 30, 255 - times[n] * 30, 255 - times[n] * 30));
				if (times[n] < 7)
					times[n] += 1;
			}
		}
	}

	/**
	 * One sub_method to determine if a button clicked is valid. Determine if
	 * the clicked button is adjacent to previous chosen one.
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	private boolean isAdjacent(int A, int B) {
		int C = Math.abs(A - B);
		if (C == 1 || C == 3 || C == 4 || C == 5)
			return true;
		else
			return false;
	}

	private Integer calculateWordScoreFromLocalLib(String word) {
		if (word.length() <= 1) {
			messageLabel.setText("Choose at least 2 letters");
			return 0;
		}
		Integer wordLength = chosenCellBtns.size();
		return CalculateLocalScore.calculateWordScore(word, wordLength);
	}
}
