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
	private Model model;
	private Application app;
	private ArrayList<JButton> chosenCells;
	private ArrayList<JButton> allCells;

	public MultiGame(Model model, Application app) {
		this.model = model;
		this.app = app;
		initiate();
	}
	
	private String obtainChosenLettDisplay(ArrayList<JButton> chosenCells){
		String lettDisplay = "";
		for(JButton tempbtn : chosenCells){
			String s = tempbtn.getText();
			lettDisplay += s;
		}
		return lettDisplay;
	}

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
	
	private boolean hasBeenChosen(JButton tempBtn){
		for (JButton previous : this.chosenCells){
			if(tempBtn.equals(previous)){
				return true;
			}
		}
		return false;
	}
	private boolean isAdjacent(int A, int B){
		int[] arrayWithBorder = new int[] {-1, -1, -1, -1, -1, -1, 
										   -1,  0,  1,  2,  3, -1, 
										   -1,  4,  5,  6,  7, -1,
										   -1,  8,  9,  10, 11, -1, 
										   -1, 12,  13, 14, 15, -1
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

	private void updateBoard(){
        ActionListener cellChosenListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source instanceof Component) {           	
//                    ((Component)source).setBackground(Color.RED);
                    JButton tempBtn = (JButton)source;                   
                    if(!isCellValidateToChoose(tempBtn)){
                    	MultiGame.this.messageLabel.setText("Cell chosen not valid!");
                    	return;
                    }
                    MultiGame.this.messageLabel.setText("");
                    tempBtn.setBackground(Color.RED);
                    MultiGame.this.chosenCells.add(tempBtn);
                    String lett = tempBtn.getText();
                    String lettDisplay = obtainChosenLettDisplay(chosenCells);
                    MultiGame.this.submission.setText(lettDisplay);                 
                    String previousScore = MultiGame.this.expectscore.getText();
                    Integer currentScore = CalculateLocalScore.calculateLetterScore(lett); 
                    Integer newScore = currentScore + Integer.parseInt(previousScore);
                    MultiGame.this.expectscore.setText(newScore.toString());
                }  
            }
        };
		this.allCells = new ArrayList<JButton>();		
		String cellsInfo = this.model.getBoard().generateRandomCellInfo();
		char[] cellsInfoArray = cellsInfo.toCharArray();
		
		for(int i=0;i<16;i++){			
			Character lettToBeAdd = (Character)cellsInfoArray[i];
			JButton tmp = new JButton(lettToBeAdd.toString());
			tmp.setBackground(Color.WHITE);
			tmp.addActionListener(cellChosenListener);		
			allCells.add(tmp);
		}
		updateBoardView(allCells);
	}
	
	private void updateBoardView(ArrayList<JButton> allCells){
		this.boardview.removeAll();
		for(int i=0; i<16; i++){
			this.boardview.add(allCells.get(i));
		}
	}
	
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
		leftPanel.add(btnUp);
		
		JButton btnLeft = new JButton("<");
		btnLeft.setBounds(10, 110, 45, 100);
		leftPanel.add(btnLeft);
		
		JButton btnRight = new JButton(">");
		btnRight.setBounds(320, 110, 45, 100);
		leftPanel.add(btnRight);
		
		JButton btnDown = new JButton("v");
		btnDown.setBounds(130, 285, 100, 40);
		leftPanel.add(btnDown);
			
		boardview = new JPanel();
		boardview.setBackground(Color.WHITE);
		boardview.setBorder(new LineBorder(Color.WHITE));
		boardview.setBounds(70, 50, 230, 234);
		leftPanel.add(boardview);
		boardview.setLayout(new GridLayout(4, 4, 0, 0));

		this.chosenCells = new ArrayList<JButton>();
		updateBoard();
	
		messageLabel = new JLabel();
		messageLabel.setFont(new Font("����", Font.BOLD, 12));
		messageLabel.setBounds(100, 340, 150, 21);
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
				MultiGame.this.expectscore.setText("0");
				MultiGame.this.submission.setText("");
				MultiGame.this.chosenCells.removeAll(chosenCells);
				for(int i=0 ; i<16 ; i++){
					Component c = MultiGame.this.boardview.getComponent(i);
					c.setBackground(Color.WHITE);
				}
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
				String word = MultiGame.this.submission.getText();
				Integer expectedWordScore = CalculateLocalScore.calculateWordScore(word);
				Integer currentScore = Integer.parseInt(MultiGame.this.myscore.getText());
				Integer newScore = (Integer)(expectedWordScore+currentScore);
				MultiGame.this.myscore.setText(newScore.toString());
				MultiGame.this.model.getBoard().updateBoard();
				MultiGame.this.chosenCells.removeAll(chosenCells);
				MultiGame.this.submission.setText("");
				MultiGame.this.expectscore.setText("0");
				updateBoard();
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
				MultiGame.this.setVisible(false);
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
		lock.setBackground(Color.WHITE);
		lock.setForeground(Color.GREEN);
		lock.setFont(new Font("����", Font.BOLD, 12));
		lock.setBounds(80, 40, 110, 30);
		managerPower.add(lock);
		
		JButton reset = new JButton("Reset game");
		reset.setForeground(Color.GREEN);
		reset.setBackground(Color.WHITE);
		reset.setFont(new Font("����", Font.BOLD, 12));
		reset.setBounds(80, 70, 110, 30);
		managerPower.add(reset);
	}
}
