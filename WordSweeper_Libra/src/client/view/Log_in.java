package client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JLayeredPane;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.Graphics;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import client.ServerAccess;
import client.controller.CreateGameController;
import client.controller.JoinGameController;
import client.model.*;
import javax.swing.JTextArea;

public class Log_in extends JFrame {
	public final Model model;
	ServerAccess serverAccess;
	
	JPanel contentPane;
	JButton Practice;
	JButton Multiplay;
	JButton Join;
	JTextField multi_PlayerName;
	JTextField join_PlayerName;
	JTextField join_gameid;
//	JTextField indication1;
//	JTextField indication2;
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Log_in(Model model) {
		this.model=model;
		setForeground(Color.RED);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Wordsweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 399);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(Color.WHITE);
		menu.setBorder(new LineBorder(Color.ORANGE));
		menu.setForeground(Color.YELLOW);
		menu.setBounds(22, 10, 213, 328);
		menu.setLayout(null);
		contentPane.add(menu);
		
		Practice = new JButton("Practice Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Practice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateGameController(Log_in.this, model).process(null);
				Log_in.this.setVisible(false);
				Practice practice= new Practice(model);
				practice.setVisible(true);
			}
		});
		Practice.setBounds(30, 35, 155, 52);
		menu.add(Practice);
		
		JTextField indication1 = new JTextField();
		indication1.setText("Please click the \"Multiplay Game\"");
		indication1.setBounds(263, 159, 213, 21);
		contentPane.add(indication1);
		indication1.setColumns(10);
		
		Multiplay = new JButton("Multiplay Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Multiplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(multi_PlayerName.getText().length()!=0){
				new CreateGameController(Log_in.this, model).process(multi_PlayerName.getText());	
				Log_in.this.setVisible(false);
				Managing_User multi= new Managing_User(model);
				multi.setVisible(true);
				}
				else{
				  indication1.setText("Please input your name!");
				  indication1.setForeground(Color.RED);
				}
			}
		});
		Multiplay.setBounds(30, 140, 155, 52);
		menu.add(Multiplay);
		
		JTextField indication2 = new JTextField();
		indication2.setText("Please click the \"Join Game\"");
		indication2.setBounds(263, 311, 220, 21);
		contentPane.add(indication2);
		indication2.setColumns(10);
		
		Join = new JButton("Join Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(join_gameid.getText().length()!=0&&join_PlayerName.getText().length()!=0){
				new JoinGameController(Log_in.this, model).process(join_gameid.getText(),join_PlayerName.getText());
				Log_in.this.setVisible(false);
				Player join= new Player(model);
				join.setVisible(true);
				}
				else{
				  indication2.setText("Please input your name and game id!");
				  indication2.setForeground(Color.RED);
				}
			}
		});
		Join.setBounds(30, 243, 155, 52);
		menu.add(Join);
		
		JPanel multi = new JPanel();
		multi.setBorder(new LineBorder(new Color(0, 0, 0)));
		multi.setBounds(269, 80, 191, 69);
		multi.setLayout(null);
		contentPane.add(multi);
		
		multi_PlayerName = new JTextField();
		multi_PlayerName.setBounds(87, 10, 78, 21);
		multi.add(multi_PlayerName);
		multi_PlayerName.setColumns(10);
		
		JLabel multi_PlayerName = new JLabel("Player Name:");
		multi_PlayerName.setBounds(10, 13, 78, 15);
		multi.add(multi_PlayerName);
		
		JLabel multi_Password = new JLabel("Password:");
		multi_Password.setForeground(Color.BLACK);
		multi_Password.setBounds(10, 38, 78, 15);
		multi.add(multi_Password);
		
		TextField multi_password = new TextField();
		multi_password.setText("optional");
		multi_password.setBounds(87, 37, 78, 23);
		multi.add(multi_password);
		
		JPanel join = new JPanel();
		join.setLayout(null);
		join.setBorder(new LineBorder(new Color(0, 0, 0)));
		join.setBounds(269, 210, 191, 91);
		contentPane.add(join);
		
		JLabel join_Gameid = new JLabel("Game id:");
		join_Gameid.setBounds(10, 13, 78, 15);
		join.add(join_Gameid);
		
		JLabel join_PlayerName = new JLabel("Player Name:");
		join_PlayerName.setForeground(Color.BLACK);
		join_PlayerName.setBounds(10, 38, 78, 15);
		join.add(join_PlayerName);
		
		this.join_PlayerName = new JTextField();
		this.join_PlayerName.setColumns(10);
		this.join_PlayerName.setBounds(87, 35, 78, 21);
		join.add(this.join_PlayerName);
		
		JLabel join_Password = new JLabel("Password:");
		join_Password.setForeground(Color.BLACK);
		join_Password.setBounds(10, 61, 78, 15);
		join.add(join_Password );
		
		join_gameid = new JTextField();
		join_gameid.setBounds(87, 10, 78, 21);
		join.add(join_gameid);
		join_gameid.setColumns(10);
		
		TextField join_Passwordtext= new TextField();
		join_Passwordtext.setText("optional");
		join_Passwordtext.setBounds(87, 59, 78, 23);
		join.add(join_Passwordtext);
	}
	/** Record the means to communicate with server. */
	public void setServerAccess(ServerAccess access) {
		this.serverAccess = access;
	}
	
	/** Get the server access object. */
	public ServerAccess getServerAccess() {
		return serverAccess;
	}
}
