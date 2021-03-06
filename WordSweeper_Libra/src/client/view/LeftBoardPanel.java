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

/**
 * This is the left panel GUI showing board information, letters and chosen
 * letters information as well as the submission process GUIs.
 * 
 *
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute
 *         equally)
 *
 */
public class LeftBoardPanel extends JPanel {

	private Model model;
	private Application app;
	private RightGameInfoBoard rightGameInfoBoard;
	private Boolean isBoardChanged;
	private String previousBoard;

	private JLabel messageLabel;

	private JTextField localExpectedScoreField;
	private JTextField scoreFromServerField;
	private JTextField submissionField;
	private JPanel boardview;

	JButton btnUp;
	JButton btnLeft;
	JButton btnDown;
	JButton btnRight;
	JButton clear;
	JButton submit;

	private ArrayList<JButton> chosenCellBtns;
	private ArrayList<JButton> allCellBtns;

	/** LeftBoardPanel constructor */
	public LeftBoardPanel(Model model, Application app, JPanel rightGameInfoBoard) {
		this.model = model;
		this.app = app;
		this.rightGameInfoBoard = (RightGameInfoBoard) rightGameInfoBoard;
		this.isBoardChanged = false;
		this.previousBoard = "";
		initiate();
	}

	public JLabel getMessageLabel() {
		return this.messageLabel;
	}

	/** This method initiate the swing units in LeftBoardPanel */
	private void initiate() {
		setBounds(5, 5, 370, 470);
		setLayout(null);

		btnUp = new JButton("^");
		btnUp.addMouseListener(new MGMouseListener() {
			int previousRow;
			int newRow;

			/**
			 * control the function of reposition to upper place
			 * 
			 * @param e
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousRow = model.getBoard().getRow();
				model.getBoard().setRequestRowChange(-1);
				model.getBoard().setRequestColChange(0);
				new RepositionBoardController(app, model).process();
				messageLabel.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isDisconnected() == true) {
					messageLabel.setText("Disconnected  from  the  server !!!");
				} else {
					newRow = model.getBoard().getRow();
					if (previousRow == newRow) {
						messageLabel.setText("No More Up !");
					}
				}
			}
		});

		btnUp.setBounds(130, 5, 100, 40);
		btnUp.setEnabled(true);
		add(btnUp);

		btnLeft = new JButton("<");
		btnLeft.addMouseListener(new MGMouseListener() {
			int previousCol;
			int newCol;

			/**
			 * control the function of reposition to left place
			 * 
			 * @param e
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousCol = model.getBoard().getCol();
				model.getBoard().setRequestColChange(-1);
				model.getBoard().setRequestRowChange(0);
				new RepositionBoardController(app, model).process();
				messageLabel.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isDisconnected() == true) {
					messageLabel.setText("Disconnected  from  the  server !!!");
				} else {
					newCol = model.getBoard().getCol();
					if (previousCol == newCol) {
						messageLabel.setText("No More Left!");
					}
				}
			}
		});
		btnLeft.setBounds(10, 110, 45, 100);
		btnLeft.setEnabled(true);
		add(btnLeft);

		btnRight = new JButton(">");
		btnRight.addMouseListener(new MGMouseListener() {
			int previousCol;
			int newCol;

			/**
			 * control the function of reposition to right place
			 * 
			 * @param e
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousCol = model.getBoard().getCol();
				model.getBoard().setRequestColChange(1);
				model.getBoard().setRequestRowChange(0);
				new RepositionBoardController(app, model).process();
				messageLabel.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isDisconnected() == true) {
					messageLabel.setText("Disconnected  from  the  server !!!");
				} else {
					newCol = model.getBoard().getCol();
					if (previousCol == newCol) {
						messageLabel.setText("No More Right!");
					}
				}
			}
		});
		btnRight.setBounds(320, 110, 45, 100);
		btnRight.setEnabled(true);
		add(btnRight);

		btnDown = new JButton("v");
		btnDown.addMouseListener(new MGMouseListener() {
			int previousRow;
			int newRow;

			/**
			 * control the function of reposition to down place
			 * 
			 * @param e
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				clearAllChosen();
				previousRow = model.getBoard().getRow();
				model.getBoard().setRequestRowChange(1);
				model.getBoard().setRequestColChange(0);
				new RepositionBoardController(app, model).process();
				messageLabel.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isDisconnected() == true) {
					messageLabel.setText("Disconnected  from  the  server !!!");
				} else {
					newRow = model.getBoard().getRow();
					if (previousRow == newRow) {
						messageLabel.setText("No More Down!");
					}
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

		messageLabel = new JLabel();
		messageLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		messageLabel.setBounds(41, 340, 324, 21);
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

		clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllChosen();
			}
		});
		clear.setBackground(Color.WHITE);
		clear.setForeground(Color.RED);
		clear.setFont(new Font("Tahoma", Font.BOLD, 12));
		clear.setToolTipText("Clear all the letters!");
		clear.setBounds(69, 430, 93, 23);
		add(clear);

		submit = new JButton("Submit");
		submit.addMouseListener(new MGMouseListener() {
			long playerNewScore;
			Integer localExpectedWordScore;

			/**
			 * control the function of submit, when player click submit, client
			 * will define the word he chose.
			 * 
			 * @param e
			 */
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
				new FindWordController(app, model).process();
				messageLabel.setText("");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (isDisconnected() == true) {
					messageLabel.setText("Disconnected  from  the  server!!!");
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					playerNewScore = model.getPlayer().getWordscore();
					scoreFromServerField.setText(String.valueOf(playerNewScore));
					rightGameInfoBoard.updateGameInfoBoard();
					clearAllChosen();
				}
			}
		});
		;

		submit.setToolTipText("submit the word you have chosen");
		submit.setForeground(Color.GREEN);
		submit.setFont(new Font("Tahoma", Font.BOLD, 12));
		submit.setBackground(Color.WHITE);
		submit.setBounds(198, 430, 93, 23);
		add(submit);

		refreshBoard();
	}

	/**
	 * refresh the board GUI part.
	 */
	public void refreshBoard() {
		this.isBoardChanged = !this.previousBoard.equals(this.model.getBoard().getBoardInfo());
		this.previousBoard = this.model.getBoard().getBoardInfo();
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
		if (this.isBoardChanged) {
			this.model.getBoard().clearChosenCells();
			clearAllChosen();
		} else {
			overlapArea();
			changeBonusColor();
		}
		boardview.repaint();
		messageLabel.setText("");
	}

	/**
	 * remove all the cell buttons' colors.
	 */
	private void removeCellBtnsColors() {
		for (int i = 0; i < 16; i++) {
			Component c = this.boardview.getComponent(i);
			c.setBackground(Color.WHITE);
		}
		boardview.repaint();
	}

	/**
	 * clear all the chosen cell buttons.
	 */
	private void clearAllChosen() {
		// messageLabel.setText("");
		localExpectedScoreField.setText("0");
		submissionField.setText("");
		chosenCellBtns.removeAll(chosenCellBtns);
		model.getBoard().clearChosenCells();
		removeCellBtnsColors();
		overlapArea();
		changeBonusColor();
	}

	/**
	 * add all the cell buttons to cell button list.
	 */
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
	 * @return String
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
	 * @return boolean
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
	 * @return boolean
	 */
	private boolean hasBeenChosen(JButton tempBtn) {
		for (JButton previous : this.chosenCellBtns) {
			if (tempBtn.equals(previous)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check if the target cell is bonus.
	 * 
	 * @param index
	 * @return boolean
	 */
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

	/**
	 * Change the color of bonus cell into yellow
	 */
	private void changeBonusColor() {
		String bonusCell = this.model.getBoard().getBonusCell();
		int x = Character.getNumericValue(bonusCell.charAt(0)) - this.model.getBoard().getCol() + 1;
		int y = Character.getNumericValue(bonusCell.charAt(2)) - this.model.getBoard().getRow() + 1;
		if (x >= 1 && x <= 4 && y >= 1 && y <= 4) {
			int index = (y - 1) * 4 + x - 1;
			this.allCellBtns.get(index).setBackground(Color.YELLOW);
		}
	}

	/**
	 * Find the overlapped cells, and change the color of overlapped cells. The
	 * more players in one cell, the darker color of the cell.
	 */
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
		for (JButton previous : this.chosenCellBtns) {
			previous.setBackground(Color.RED);
		}
	}

	/**
	 * One sub_method to determine if a button clicked is valid. Determine if
	 * the clicked button is adjacent to previous chosen one.
	 * 
	 * @param A
	 * @param B
	 * @return boolean
	 */
	private boolean isAdjacent(int A, int B) {
		int[] arrayWithBorder = new int[] { -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, -1, -1, 4, 5, 6, 7, -1, -1, 8, 9,
				10, 11, -1, -1, 12, 13, 14, 15, -1, -1, -1, -1, -1, -1, -1, };
		ArrayList<Integer> arrayListWithBorder = new ArrayList<Integer>();
		for (int i : arrayWithBorder) {
			arrayListWithBorder.add(i);
		}
		int rankOfA = arrayListWithBorder.indexOf(A);
		int[] aroundA = { arrayWithBorder[rankOfA - 7], arrayWithBorder[rankOfA - 6], arrayWithBorder[rankOfA - 5],
				arrayWithBorder[rankOfA - 1], arrayWithBorder[rankOfA + 1], arrayWithBorder[rankOfA + 5],
				arrayWithBorder[rankOfA + 6], arrayWithBorder[rankOfA + 7] };
		for (int i : aroundA) {
			if (B == i) {
				return true;
			}
		}
		return false;
	}

	/**
	 * calculate the local score for chosen word.
	 * 
	 * @param word
	 * @return Integer
	 */
	private Integer calculateWordScoreFromLocalLib(String word) {
		if (word.length() <= 1) {
			messageLabel.setText("Choose at least 2 letters");
			return 0;
		}
		Integer wordLength = chosenCellBtns.size();
		return CalculateLocalScore.calculateWordScore(word, wordLength);
	}

	/**
	 * check if it is disconnected now.
	 * 
	 * @return boolean
	 */
	public boolean isDisconnected() {
		for (int i = 0; i < 40; i++) {
			if (model.isWaitingResponse == false)
				return false;
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	public ArrayList<JButton> getAllCellBtns() {
		return this.allCellBtns;
	}

}