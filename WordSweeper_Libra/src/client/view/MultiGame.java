package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import client.controller.MGMouseListener;
import client.controller.requestController.LockGameController;
import client.controller.requestController.RepositionBoardController;
import client.controller.requestController.ResetGameController;
import client.model.Model;
import util.CalculateLocalScore;

public class MultiGame extends JFrame {
	private JPanel contentPane;
	private JLabel messageLabel;
	private JTextField expectscore;
	private JTextField submission;
	private JLabel myscore;
	private JPanel boardview;
	private JPanel leftPanel;	
	private JScrollPane jScrollPane1;	
	private JTextArea playersListArea;
	
	JLabel gameIdLabel;
	JLabel managerName;
	JLabel gameLockResetLabel;
	JButton lockBtn;
	JButton resetBtn;

	
	
	private Model model;
	private Application app;
	private ArrayList<JButton> chosenCellBtns;
	private ArrayList<JButton> allCellBtns;
	private JButton cellBtn0; private JButton cellBtn1; private JButton cellBtn2; private JButton cellBtn3; private JButton cellBtn4; private JButton cellBtn5; 
	private JButton cellBtn6; private JButton cellBtn7; private JButton cellBtn8; private JButton cellBtn9; private JButton cellBtn10; private JButton cellBtn11; 
	private JButton cellBtn12; private JButton cellBtn13; private JButton cellBtn14; private JButton cellBtn15;

	public MultiGame(Model model, Application app) {
		this.model = model;
		this.app = app;
		initiate();
	}
	
	public Model getMultiGameModel(){
		return this.model;
	}
	
	private void addAllCellBtnsToCellBtnsList(){

        ActionListener cellChosenListener = new ActionListener(){
        	@Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource(); 
            	JButton chosenBtn = (JButton)source;  
                if(!isCellValidateToChoose(chosenBtn)){
                	MultiGame.this.messageLabel.setText("Cell chosen not valid!");
                	return;
                }
                MultiGame.this.messageLabel.setText("");
                chosenBtn.setBackground(Color.RED);
                
                int indexOfThisBtnInAll = allCellBtns.indexOf(chosenBtn);
                
//                Update model chosen cells info for submission purpose.
                model.getBoard().addToChosenCellsByCellIndex(indexOfThisBtnInAll);
                
//                Update GUI chosenCellBtns for display purpose (maintain this bks everything is local).
                MultiGame.this.chosenCellBtns.add(chosenBtn);
                
                String lett = chosenBtn.getText();
                String lettDisplay = obtainChosenLettDisplay(chosenCellBtns);
                MultiGame.this.submission.setText(lettDisplay);                 
                String previousScore = MultiGame.this.expectscore.getText();
                Integer currentScore = CalculateLocalScore.calculateLetterScore(lett); 
                Integer newScore = currentScore + Integer.parseInt(previousScore);
                MultiGame.this.expectscore.setText(newScore.toString());           
            }
        }; 
        
		cellBtn0 = new JButton(); cellBtn1 = new JButton();
		cellBtn2 = new JButton(); cellBtn3 = new JButton();
		cellBtn4 = new JButton(); cellBtn5 = new JButton();
		cellBtn6 = new JButton(); cellBtn7 = new JButton();
		cellBtn8 = new JButton(); cellBtn9 = new JButton();
		cellBtn10 = new JButton(); cellBtn11 = new JButton();
		cellBtn12 = new JButton(); cellBtn13 = new JButton();
		cellBtn14 = new JButton(); cellBtn15 = new JButton();
		this.allCellBtns = new ArrayList<JButton>();
		this.allCellBtns.add(cellBtn0);this.allCellBtns.add(cellBtn1);this.allCellBtns.add(cellBtn2);this.allCellBtns.add(cellBtn3);this.allCellBtns.add(cellBtn4);this.allCellBtns.add(cellBtn5);
		this.allCellBtns.add(cellBtn6);this.allCellBtns.add(cellBtn7);this.allCellBtns.add(cellBtn8);this.allCellBtns.add(cellBtn9);this.allCellBtns.add(cellBtn10);this.allCellBtns.add(cellBtn11);
		this.allCellBtns.add(cellBtn12);this.allCellBtns.add(cellBtn13);this.allCellBtns.add(cellBtn14);this.allCellBtns.add(cellBtn15);
		
        for(JButton cellBtn : allCellBtns){
        	cellBtn.addActionListener(cellChosenListener);
        	cellBtn.setBackground(Color.WHITE);
        	this.boardview.add(cellBtn);
        }
	}
	
	
	/**
	 * Extract letters of chosenCells in order, and convert to string for display
	 * @param chosenCells
	 * @return
	 */	
	private String obtainChosenLettDisplay(ArrayList<JButton> chosenCells){
		String lettDisplay = "";
		for(JButton tempbtn : chosenCells){
			String s = tempbtn.getText();
			lettDisplay += s;
		}
		return lettDisplay;
	}
	
	/**
	 * Determine if a button clicked is valid.
	 * @param tempBtn
	 * @return
	 */
	private boolean isCellValidateToChoose(JButton tempBtn){
		if(this.chosenCellBtns.size() == 0){
			return true;
		}		
		int indexOfThisBtnInAll = this.allCellBtns.indexOf(tempBtn);
		JButton previousChosenButton = this.chosenCellBtns.get(this.chosenCellBtns.size() - 1);
		int indexOfPreviousBtnInAll = this.allCellBtns.indexOf(previousChosenButton);
		if(isAdjacent(indexOfPreviousBtnInAll, indexOfThisBtnInAll) && !hasBeenChosen(tempBtn)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * One sub_method to determine if a button clicked is valid.
	 * Determine if the clicked button has been chosen before or not.
	 * @param tempBtn
	 * @return
	 */
	private boolean hasBeenChosen(JButton tempBtn){
		for (JButton previous : this.chosenCellBtns){
			if(tempBtn.equals(previous)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * One sub_method to determine if a button clicked is valid.
	 * Determine if the clicked button is adjacent to previous chosen one.
	 * @param A
	 * @param B
	 * @return
	 */
	private boolean isAdjacent(int A, int B){
		int[] arrayWithBorder = new int[] {-1, -1, -1, -1, -1, -1, 
										   -1,  0,  1,  2,  3, -1, 
										   -1,  4,  5,  6,  7, -1,
										   -1,  8,  9,  10, 11, -1, 
										   -1, 12,  13, 14, 15, -1,
										   -1, -1, -1, -1, -1, -1, };
		ArrayList<Integer> arrayListWithBorder = new ArrayList<Integer>();
		for(int i: arrayWithBorder){
			arrayListWithBorder.add((Integer)i);
		}
		int rankOfA = arrayListWithBorder.indexOf(A);
		int[] aroundA = {
						 arrayWithBorder[rankOfA-7],arrayWithBorder[rankOfA-6],arrayWithBorder[rankOfA-5],
						 arrayWithBorder[rankOfA-1],                           arrayWithBorder[rankOfA+1], 
						 arrayWithBorder[rankOfA+5],arrayWithBorder[rankOfA+6],arrayWithBorder[rankOfA+7]
						};	
		for(int i : aroundA){
			if(B == i){
				return true;
			}
		}
		return false;
	}
	
	private void removeCellBtnsColors(){
		for(int i=0 ; i<16 ; i++){
			Component c = this.boardview.getComponent(i);
			c.setBackground(Color.WHITE);
		}
		boardview.repaint();
	}
	
	private void clearAllChosen(){
		MultiGame.this.messageLabel.setText("");
		MultiGame.this.expectscore.setText("0");
		MultiGame.this.submission.setText("");
		MultiGame.this.chosenCellBtns.removeAll(chosenCellBtns);
		removeCellBtnsColors();
	}
	
	private void refreshBoard(){
		char[] LettersToBeAdd = this.model.getBoard().getBoardInfo().toCharArray();
		for(int i=0;i<16;i++){			
			String lettToBeAdd = String.valueOf(LettersToBeAdd[i]);
			if(lettToBeAdd.equals("Q")){
				lettToBeAdd = "Qu";			
			}						
			this.allCellBtns.get(i).setText(lettToBeAdd);
		}
		removeCellBtnsColors();
		boardview.repaint();
	}
		
	private Integer calculateWordScoreFromLocalLib(String word){
		if(word.length() <= 1){
			messageLabel.setText("Choose at least 2 letters");
			return 0;
		}				
		Integer wordLength = chosenCellBtns.size();
		return CalculateLocalScore.calculateWordScore(word, wordLength);		
	}
	
	private void submitPerformed(){
		String word = submission.getText();
		String wordFromModelChosenCells = model.getBoard().getChosenCellsLetters();
		System.out.println(word + ":" + wordFromModelChosenCells);
		Integer expectedWordScore = calculateWordScoreFromLocalLib(word);
//		if(expectedWordScore==0){
//			clearAllChosen();
//			messageLabel.setText("Oh, No! Choose again !");
//			return;
//		}
		Integer currentScore = Integer.parseInt(myscore.getText());
		Integer newScore = (Integer)(expectedWordScore+currentScore);
		myscore.setText(newScore.toString());
		chosenCellBtns.removeAll(chosenCellBtns);
		submission.setText("");
		expectscore.setText("0");
		refreshBoard();
	}
	
	public void updateGameInfoBoard(){
		gameIdLabel.setText(model.getGame().getGameID());
		myscore.setText(String.valueOf(model.getPlayer().getScore()));
		playersListArea.setText(model.getGame().getPlayersListByName());
		managerName.setText(model.getGame().getManagingUser());
		lockBtn.setEnabled(model.getPlayer().isManager());
		resetBtn.setEnabled(model.getPlayer().isManager());
		if(model.getGame().isLocked()){
			gameLockResetLabel.setText("Game has been Locked!");
			lockBtn.setEnabled(false);
		}
	}

	/**
	 * Initiate the GUI.
	 */
	private void initiate(){
		
		setTitle("wordsweeper MultiGame");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		leftPanel = new JPanel();
		leftPanel.setBounds(5, 5, 370, 470);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		JButton btnUp = new JButton("^");
		btnUp.addMouseListener(new MGMouseListener(){
			int previousRow;
			int newRow;
			@Override
			public void mousePressed(MouseEvent e) {
				messageLabel.setText("");
				previousRow = model.getBoard().getGlobalStartingRow();
				model.getBoard().setRequestRowChange(-1);
				new RepositionBoardController(app, model).process();			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();
				newRow = model.getBoard().getGlobalStartingRow();
				if(previousRow == newRow){
					messageLabel.setText("No More Up!");
				}
			}
		});

		btnUp.setBounds(130, 5, 100, 40);
		btnUp.setEnabled(true);
		leftPanel.add(btnUp);
		
		JButton btnLeft = new JButton("<");
		btnLeft.addMouseListener(new MGMouseListener(){
			int previousCol;
			int newCol;
			@Override
			public void mousePressed(MouseEvent e) {
				messageLabel.setText("");
				previousCol = model.getBoard().getGlobalStartingCol();
				model.getBoard().setRequestColChange(-1);
				new RepositionBoardController(app, model).process();			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();
				newCol = model.getBoard().getGlobalStartingCol();
				if(previousCol == newCol){
					messageLabel.setText("No More Left!");
				}
			}
		});
		btnLeft.setBounds(10, 110, 45, 100);
		btnLeft.setEnabled(true);
		leftPanel.add(btnLeft);
		
		JButton btnRight = new JButton(">");
		btnRight.addMouseListener(new MGMouseListener(){
			int previousCol;
			int newCol;
			@Override
			public void mousePressed(MouseEvent e) {
				messageLabel.setText("");
				previousCol = model.getBoard().getGlobalStartingCol();
				model.getBoard().setRequestColChange(1);
				new RepositionBoardController(app, model).process();			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();	
				newCol = model.getBoard().getGlobalStartingCol();
				if(previousCol == newCol){
					messageLabel.setText("No More Right!");
				}
			}
		});
		btnRight.setBounds(320, 110, 45, 100);
		btnRight.setEnabled(true);
		leftPanel.add(btnRight);
		
		JButton btnDown = new JButton("v");
		btnDown.addMouseListener(new MGMouseListener(){
			int previousRow;
			int newRow;
			@Override
			public void mousePressed(MouseEvent e) {
				messageLabel.setText("");
				previousRow = model.getBoard().getGlobalStartingRow();
				model.getBoard().setRequestRowChange(1);
				new RepositionBoardController(app, model).process();			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				refreshBoard();	
				newRow = model.getBoard().getGlobalStartingRow();
				if(previousRow == newRow){
					messageLabel.setText("No More Down!");
				}
			}
		});
		btnDown.setBounds(130, 285, 100, 40);
		btnDown.setEnabled(true);
		leftPanel.add(btnDown);

		boardview = new JPanel();
		boardview.setBackground(Color.WHITE);
		boardview.setBorder(new LineBorder(Color.WHITE));
		boardview.setBounds(70, 50, 230, 234);
		leftPanel.add(boardview);
		boardview.setLayout(new GridLayout(4, 4, 0, 0));
		
		chosenCellBtns = new ArrayList<JButton>();
		addAllCellBtnsToCellBtnsList();	
		refreshBoard();
		
		messageLabel = new JLabel();
		messageLabel.setFont(new Font("����", Font.BOLD, 12));
		messageLabel.setBounds(100, 340, 200, 21);
		messageLabel.setText("");
		messageLabel.setForeground(Color.RED);
		leftPanel.add(messageLabel);
		
		JLabel expectscore = new JLabel("Expected Score:");
		expectscore.setBounds(54, 370, 105, 21);
		leftPanel.add(expectscore);
		
		this.expectscore = new JTextField("0");
		this.expectscore.setBounds(169, 370, 66, 21);
		this.expectscore.setEditable(false);
		leftPanel.add(this.expectscore);
		this.expectscore.setColumns(10);
		
		JLabel submission = new JLabel("Submission Bar:");		
		submission.setBounds(54, 400, 105, 21);
		leftPanel.add(submission);
		
		this.submission = new JTextField();
		this.submission.setBounds(169, 400, 130, 21);
		this.submission.setEditable(false);
		leftPanel.add(this.submission);
		this.submission.setColumns(10);
		
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MultiGame.this.clearAllChosen();
			}			
		});
		clear.setBackground(Color.WHITE);
		clear.setForeground(Color.RED);
		clear.setFont(new Font("����", Font.BOLD, 12));
		clear.setToolTipText("Clear all the letters!");
		clear.setBounds(69, 430, 93, 23);
		leftPanel.add(clear);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!model.getGame().isLocked()){
					gameLockResetLabel.setText("");
				}
				MultiGame.this.submitPerformed();
			}	
		});
		submit.setToolTipText("submit the word you have chosen");
		submit.setForeground(Color.GREEN);
		submit.setFont(new Font("����", Font.BOLD, 12));
		submit.setBackground(Color.WHITE);
		submit.setBounds(198, 430, 93, 23);
		leftPanel.add(submit);
		
		
		JPanel rightPanel = new JPanel();
		rightPanel.setBorder(BorderFactory.createTitledBorder("Game Information"));
		rightPanel.setBounds(380, 5, 300, 470);
		rightPanel.setLayout(null);
		contentPane.add(rightPanel);
		
		gameLockResetLabel = new JLabel("");
		gameLockResetLabel.setForeground(Color.RED);
		gameLockResetLabel.setFont(new Font("����", Font.BOLD, 12));
		gameLockResetLabel.setBounds(10, 20, 200, 30);
		rightPanel.add(gameLockResetLabel);
		
		JLabel gameIdTitle = new JLabel("Game ID:");
		gameIdTitle.setBounds(10, 50, 65, 28);
		rightPanel.add(gameIdTitle);
		
		gameIdLabel = new JLabel("");
		gameIdLabel.setBounds(80, 50, 65, 28);
		rightPanel.add(gameIdLabel);
		
		
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				MultiGame.this.dispose();
//				app.setVisible(true);	
				app.enableInputs();
			}
		});
		quit.setForeground(Color.BLUE);
		quit.setFont(new Font("����", Font.BOLD, 12));
		quit.setSize(getPreferredSize());
		quit.setBounds(190, 20, 100, 25);
		rightPanel.add(quit);
		
		JLabel myScoreLabel = new JLabel("My Score:");
		myScoreLabel.setBounds(10, 75, 65, 28);
		rightPanel.add(myScoreLabel);
		
		myscore = new JLabel("0");
		myscore.setBounds(80, 78, 120, 21);
		myscore.setFont(new Font("����", Font.BOLD, 14));;
		rightPanel.add(myscore);
		
		JLabel playerListLabel = new JLabel("Players List: ");
		playerListLabel.setBounds(10, 100, 120, 28);
		rightPanel.add(playerListLabel);
		
		playersListArea = new JTextArea("");
		playersListArea.setForeground(new Color(245, 0, 0));
		playersListArea.setColumns(20);
		playersListArea.setRows(10);
		playersListArea.setEditable(false);
		
		jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(10, 130, 280, 200);
		jScrollPane1.setViewportView(playersListArea);		
		rightPanel.add(jScrollPane1);
		
		JPanel managerPower = new JPanel();
		managerPower.setBounds(10, 350, 280, 110);
		managerPower.setLayout(null);
		rightPanel.add(managerPower);
		
		JLabel managerNameLabel = new JLabel("Manager Name: ");
		managerNameLabel.setBounds(10, 10, 120, 15);
		managerPower.add(managerNameLabel);
		
		managerName = new JLabel();
		managerName.setText("You");
		managerName.setBounds(130, 10, 100, 15);
		managerPower.add(managerName);

		lockBtn = new JButton("Lock game");
		lockBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameLockResetLabel.setText("Game has been Locked!");
				new LockGameController(app, model).process();
			}			
		});

		lockBtn.setEnabled(false);
		lockBtn.setBackground(Color.WHITE);
		lockBtn.setForeground(Color.BLUE);
		lockBtn.setFont(new Font("����", Font.BOLD, 12));
		lockBtn.setBounds(80, 40, 110, 30);
		managerPower.add(lockBtn);
		
		
		// resetGame button has some problems.
		resetBtn = new JButton("Reset game");
		resetBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				gameLockResetLabel.setText("Game has been RESET!");
				new ResetGameController(app, model).process();
				updateGameInfoBoard();
			}			
		});

		resetBtn.setEnabled(false);
		resetBtn.setForeground(Color.BLUE);
		resetBtn.setBackground(Color.WHITE);
		resetBtn.setFont(new Font("����", Font.BOLD, 12));
		resetBtn.setBounds(80, 70, 110, 30);
		managerPower.add(resetBtn);
		
		updateGameInfoBoard();
	}

}
