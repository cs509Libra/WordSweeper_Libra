package client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import client.model.*;

public class Managing_User extends JFrame {

	private JPanel contentPane;
	private JTextField output;
	private JTextField expectscore;
	private JTextField submission;
	private JTextField myscore;
	private JTextField gameid;
	private JTextField current;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Managing_User() {
		setTitle("wordsweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 558, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel left = new JPanel();
		left.setForeground(Color.RED);
		left.setBackground(Color.WHITE);
		left.setBorder(new LineBorder(new Color(0, 0, 0)));
		left.setBounds(0, 0, 367, 430);
		left.setLayout(null);
		contentPane.add(left);
		
		JPanel boardview = new JPanel();
		boardview.setBackground(Color.WHITE);
		boardview.setBorder(new LineBorder(Color.ORANGE));
		boardview.setBounds(69, 37, 230, 234);
		left.add(boardview);
		boardview.setLayout(new GridLayout(4, 4, 0, 0));
		
		Board board=new Board();   
		JButton[][] jbuttons= new JButton[4][4];
		for(int i=0;i<4;i++)
			for(int j=0;j<4;j++)
			{
				jbuttons[i][j]=new JButton("unknown");
				boardview.add(jbuttons[i][j]);
			}
		
		JButton up = new JButton("U");
		up.setBounds(147, 10, 76, 23);
		left.add(up);
		
		JButton down = new JButton("D");
		down.setBounds(147, 275, 76, 23);
		left.add(down);
		
		output = new JTextField();
		output.setFont(new Font("宋体", Font.BOLD, 12));
		output.setBackground(Color.WHITE);
		output.setForeground(Color.BLACK);
		output.setText("Submit succeed!");
		output.setBounds(69, 309, 167, 21);
		left.add(output);
		output.setColumns(10);
		
		JLabel expectscore = new JLabel("Expected Score:");
		expectscore.setBounds(54, 340, 105, 21);
		left.add(expectscore);
		
		this.expectscore = new JTextField();
		this.expectscore.setBounds(169, 340, 66, 21);
		left.add(this.expectscore);
		this.expectscore.setColumns(10);
		
		JLabel submission = new JLabel("Submission Bar:");
		submission.setBounds(54, 371, 105, 21);
		left.add(submission);
		
		this.submission = new JTextField();
		this.submission.setBounds(169, 371, 130, 21);
		left.add(this.submission);
		this.submission.setColumns(10);
		
		JButton clear = new JButton("Clear!");
		clear.setBackground(Color.WHITE);
		clear.setForeground(Color.RED);
		clear.setFont(new Font("宋体", Font.BOLD, 12));
		clear.setToolTipText("Clear all the letters!");
		clear.setBounds(69, 397, 93, 23);
		left.add(clear);
		
		JButton submit = new JButton("Submit");
		submit.setToolTipText("submit the word you have chosen");
		submit.setForeground(Color.GREEN);
		submit.setFont(new Font("宋体", Font.BOLD, 12));
		submit.setBackground(Color.WHITE);
		submit.setBounds(198, 397, 93, 23);
		left.add(submit);
		
		JButton leftB = new JButton("L");
		leftB.setBounds(0, 144, 63, 23);
		left.add(leftB);
		
		JButton rightB = new JButton("R");
		rightB.setBounds(308, 144, 59, 23);
		left.add(rightB);
		
		
		JPanel right = new JPanel();
		right.setBorder(new LineBorder(new Color(0, 0, 0)));
		right.setBounds(366, 0, 176, 430);
		right.setLayout(null);
		contentPane.add(right);
		
		JButton quit = new JButton("Quit");
		quit.setToolTipText("");
		quit.setForeground(Color.BLUE);
		quit.setFont(new Font("宋体", Font.BOLD, 12));
		quit.setBackground(Color.WHITE);
		quit.setBounds(48, 10, 86, 23);
		right.add(quit);
		
		JLabel myscore = new JLabel("My Score:");
		myscore.setBounds(10, 43, 65, 28);
		right.add(myscore);
		
		this.myscore = new JTextField();
		this.myscore.setBounds(85, 47, 81, 21);
		right.add(this.myscore);
		this.myscore.setColumns(10);
		
		JPanel manager_power = new JPanel();
		manager_power.setBorder(new LineBorder(Color.ORANGE));
		manager_power.setBounds(21, 303, 145, 106);
		manager_power.setLayout(null);
		right.add(manager_power);
		
		JLabel good = new JLabel("You are game manager!");
		good.setBounds(10, 10, 136, 15);
		manager_power.add(good);
		
		JButton lock = new JButton("Lock game");
		lock.setBackground(Color.WHITE);
		lock.setForeground(Color.GREEN);
		lock.setFont(new Font("宋体", Font.BOLD, 12));
		lock.setBounds(20, 35, 101, 23);
		manager_power.add(lock);
		
		JButton reset = new JButton("Reset game");
		reset.setForeground(Color.GREEN);
		reset.setBackground(Color.WHITE);
		reset.setFont(new Font("宋体", Font.BOLD, 12));
		reset.setBounds(10, 73, 111, 23);
		manager_power.add(reset);
		
		JPanel all_info = new JPanel();
		all_info.setBorder(new LineBorder(new Color(0, 0, 0)));
		all_info.setBounds(30, 81, 121, 201);
		right.add(all_info);
		
		JLabel lblGameId = new JLabel("Game id:");
		lblGameId.setBounds(28, 436, 84, 15);
		contentPane.add(lblGameId);
		
		this.gameid = new JTextField();
		this.gameid.setColumns(10);
		this.gameid.setBounds(106, 433, 66, 21);
		contentPane.add(this.gameid);
		
		JLabel lblCurrentPlayer = new JLabel("Current player:");
		lblCurrentPlayer.setBounds(228, 436, 96, 15);
		contentPane.add(lblCurrentPlayer);
		
		this.current = new JTextField();
		this.current.setBounds(334, 433, 66, 21);
		contentPane.add(this.current);
		this.current.setColumns(10);

		/*JScrollPane jScrollPane1 = new JScrollPane();    //滚动条panel
        jScrollPane1.setPreferredSize(new java.awt.Dimension(218, 164));
        ListModel jList1Model =  new DefaultComboBoxModel(new String[] { "张三","李四","李四","李四","李四","李四","李四","李四","李四","李四","李四" });
        JList myJlist = new JList();
        myJlist.setModel(jList1Model);            //设置数据
        jScrollPane1.setViewportView(myJlist);    //不能直接add
        */
	}
}
