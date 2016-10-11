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
import client.model.*;

public class Log_in extends JFrame {
	Model model;
	JPanel contentPane;
	JButton Practice;
	JButton Multiplay;
	JButton Join;
	JTextField multi_PlayerName;
	JTextField join_PlayerName;
	JTextField join_gameid;
	
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
		
		Practice = new JButton("Practice Mode");
	//	btnNewButton.addActionListener(new ActionListener() {
	//		public void actionPerformed(ActionEvent e) {
	//		}
	//	});
		Practice.setBounds(30, 35, 155, 52);
		menu.add(Practice);
		
		Multiplay = new JButton("Multiplay Mode");
		Multiplay.setBounds(30, 140, 155, 52);
		menu.add(Multiplay);
		
		Join = new JButton("Join Target");
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
		
		JLabel indication1= new JLabel("Please click the \"Multiplay Mode\"");
		indication1.setBounds(269, 161, 229, 21);
		contentPane.add(indication1);
		
		JLabel indication2 = new JLabel("Please click the \"Join Target\"");
		indication2.setBounds(269, 311, 231, 21);
		contentPane.add(indication2);
	}
}
