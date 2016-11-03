package client.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import client.model.Model;
import util.CalculateLocalScore;

public class Practice extends JFrame {
	private JPanel contentPane;
	private JLabel messageLabel;
	private JTextField expectscore;
	private JTextField submission;
	private JLabel myscore;
	private JPanel boardview;
	private JPanel leftPanel;	
	private JScrollPane jScrollPane1;	
	private JTextArea playersListArea;	
	private Model model;
	private Application app;
	private ArrayList<JButton> chosenCells;
	private ArrayList<JButton> allCells;
	private JButton cellBtn0; private JButton cellBtn1; private JButton cellBtn2; private JButton cellBtn3; private JButton cellBtn4; private JButton cellBtn5; 
	private JButton cellBtn6; private JButton cellBtn7; private JButton cellBtn8; private JButton cellBtn9; private JButton cellBtn10; private JButton cellBtn11; 
	private JButton cellBtn12; private JButton cellBtn13; private JButton cellBtn14; private JButton cellBtn15;

	public Practice(Model model, Application app) {
		this.model = model;
		this.app = app;
		initiate();
	}
	
	
	private void addAllCellsToList(){
		cellBtn0 = new JButton(); cellBtn1 = new JButton();
		cellBtn2 = new JButton(); cellBtn3 = new JButton();
		cellBtn4 = new JButton(); cellBtn5 = new JButton();
		cellBtn6 = new JButton(); cellBtn7 = new JButton();
		cellBtn8 = new JButton(); cellBtn9 = new JButton();
		cellBtn10 = new JButton(); cellBtn11 = new JButton();
		cellBtn12 = new JButton(); cellBtn13 = new JButton();
		cellBtn14 = new JButton(); cellBtn15 = new JButton();
		this.allCells = new ArrayList<JButton>();
		this.allCells.add(cellBtn0);this.allCells.add(cellBtn1);this.allCells.add(cellBtn2);this.allCells.add(cellBtn3);this.allCells.add(cellBtn4);this.allCells.add(cellBtn5);
		this.allCells.add(cellBtn6);this.allCells.add(cellBtn7);this.allCells.add(cellBtn8);this.allCells.add(cellBtn9);this.allCells.add(cellBtn10);this.allCells.add(cellBtn11);
		this.allCells.add(cellBtn12);this.allCells.add(cellBtn13);this.allCells.add(cellBtn14);this.allCells.add(cellBtn15);
		
        ActionListener cellChosenListener = new ActionListener(){
        	@Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource(); 
            	JButton chosenBtn = (JButton)source;  
                if(!isCellValidateToChoose(chosenBtn)){
                	Practice.this.messageLabel.setText("Cell chosen not valid!");
                	return;
                }
                Practice.this.messageLabel.setText("");
                chosenBtn.setBackground(Color.RED);
                Practice.this.chosenCells.add(chosenBtn);
                String lett = chosenBtn.getText();
                String lettDisplay = obtainChosenLettDisplay(chosenCells);
                Practice.this.submission.setText(lettDisplay);                 
                String previousScore = Practice.this.expectscore.getText();
                Integer currentScore = CalculateLocalScore.calculateLetterScore(lett); 
                Integer newScore = currentScore + Integer.parseInt(previousScore);
                Practice.this.expectscore.setText(newScore.toString());           
            }
        };       
        for(JButton cellBtn : allCells){
        	cellBtn.addActionListener(cellChosenListener);
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
		if(this.chosenCells.size() == 0){
			return true;
		}		
		int indexOfThisBtnInAll = this.allCells.indexOf(tempBtn);
		JButton previousChosenButton = this.chosenCells.get(this.chosenCells.size() - 1);
		int indexOfPreviousBtnInAll = this.allCells.indexOf(previousChosenButton);
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
		for (JButton previous : this.chosenCells){
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
	
	private void removeCellsColors(){
		for(int i=0 ; i<16 ; i++){
			Component c = this.boardview.getComponent(i);
			c.setBackground(Color.WHITE);
		}
		boardview.repaint();
	}
	
	private void clearAllChosen(){
		Practice.this.messageLabel.setText("");
		Practice.this.expectscore.setText("0");
		Practice.this.submission.setText("");
		Practice.this.chosenCells.removeAll(chosenCells);
		removeCellsColors();
	}
	
	private void refreshBoard(){
		String cellsInfo = this.model.getBoard().generateRandomCellInfo();
		char[] cellsInfoArray = cellsInfo.toCharArray();		
		for(int i=0;i<16;i++){			
			Character lettToBeAddChar = (Character)cellsInfoArray[i];
			String lettToBeAdd;
			if(lettToBeAddChar.equals('Q')){
				lettToBeAdd = "Qu";			
			}else{
				lettToBeAdd = lettToBeAddChar.toString();
			}						
			this.allCells.get(i).setText(lettToBeAdd);
		}
		removeCellsColors();
		boardview.repaint();
	}
	
		
	private Integer calculateWordScoreFromLib(String word){
		if(word.length() <= 1){
			messageLabel.setText("Choose at least 2 letters");
			return 0;
		}				
		Integer wordLength = chosenCells.size();
		return CalculateLocalScore.calculateWordScore(word, wordLength);		
	}
	
	private void submitPerformed(){
		String word = submission.getText();
		Integer expectedWordScore = calculateWordScoreFromLib(word);
		if(expectedWordScore==0){
			clearAllChosen();
			messageLabel.setText("Oh, No! Choose again !");
			return;
		}
		Integer currentScore = Integer.parseInt(myscore.getText());
		Integer newScore = (Integer)(expectedWordScore+currentScore);
		myscore.setText(newScore.toString());
		model.getBoard().updateBoard();
		chosenCells.removeAll(chosenCells);
		submission.setText("");
		expectscore.setText("0");
		refreshBoard();
	}

	/**
	 * Initiate the GUI.
	 */
	private void initiate(){
		
		setTitle("wordsweeper Practice");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		leftPanel = new JPanel();
		leftPanel.setBounds(5, 5, 370, 470);
		contentPane.add(leftPanel);
		leftPanel.setLayout(null);
		
		JButton btnUp = new JButton("^");
		btnUp.setBounds(130, 5, 100, 40);
		btnUp.setEnabled(false);
		leftPanel.add(btnUp);
		
		JButton btnLeft = new JButton("<");
		btnLeft.setBounds(10, 110, 45, 100);
		btnLeft.setEnabled(false);
		leftPanel.add(btnLeft);
		
		JButton btnRight = new JButton(">");
		btnRight.setBounds(320, 110, 45, 100);
		btnRight.setEnabled(false);
		leftPanel.add(btnRight);
		
		JButton btnDown = new JButton("v");
		btnDown.setBounds(130, 285, 100, 40);
		btnDown.setEnabled(false);
		leftPanel.add(btnDown);
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Practice.this.clearAllChosen();
				Practice.this.refreshBoard();		
			}		
		});
		
		btnRefresh.setBounds(250, 290, 100, 30);
		btnRefresh.setEnabled(true);
		leftPanel.add(btnRefresh);
			
		boardview = new JPanel();
		boardview.setBackground(Color.WHITE);
		boardview.setBorder(new LineBorder(Color.WHITE));
		boardview.setBounds(70, 50, 230, 234);
		leftPanel.add(boardview);
		boardview.setLayout(new GridLayout(4, 4, 0, 0));
		
		chosenCells = new ArrayList<JButton>();
		addAllCellsToList();
		for(int i=0; i<16; i++){
			allCells.get(i).setBackground(Color.WHITE);
			this.boardview.add(allCells.get(i));
		}
		
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
				Practice.this.clearAllChosen();
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
				Practice.this.submitPerformed();
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
		
		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Practice.this.setVisible(false);
				app.setVisible(true);	
			}
		});
		quit.setForeground(Color.BLUE);
		quit.setFont(new Font("����", Font.BOLD, 12));
		quit.setSize(getPreferredSize());
		quit.setBounds(100, 20, 100, 25);
		rightPanel.add(quit);
		
		JLabel myScoreLabel = new JLabel("My Score:");
		myScoreLabel.setBounds(10, 60, 65, 28);
		rightPanel.add(myScoreLabel);
		
		this.myscore = new JLabel("0");
		this.myscore.setEnabled(false);
		this.myscore.setBounds(100, 65, 120, 21);
		this.myscore.setFont(new Font("����", Font.BOLD, 14));;
		rightPanel.add(this.myscore);
		
		JLabel playerListLabel = new JLabel("Players List: ");
		playerListLabel.setBounds(10, 100, 120, 28);
		rightPanel.add(playerListLabel);
		
		playersListArea = new JTextArea();
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
		
		JLabel managerName = new JLabel();
		managerName.setText("You");
		managerName.setBounds(130, 10, 100, 15);
		managerPower.add(managerName);

		JButton lock = new JButton("Lock game");
		lock.setEnabled(false);
		lock.setBackground(Color.WHITE);
		lock.setForeground(Color.GREEN);
		lock.setFont(new Font("����", Font.BOLD, 12));
		lock.setBounds(80, 40, 110, 30);
		managerPower.add(lock);
		
		JButton reset = new JButton("Reset game");
		reset.setEnabled(false);
		reset.setForeground(Color.GREEN);
		reset.setBackground(Color.WHITE);
		reset.setFont(new Font("����", Font.BOLD, 12));
		reset.setBounds(80, 70, 110, 30);
		managerPower.add(reset);
	}
}
