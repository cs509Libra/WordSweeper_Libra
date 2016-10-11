package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

import client.ServerAccess;
import client.controller.CreateGameController;
import client.controller.JoinGameController;
import client.model.*;

import javax.swing.JLabel;

public class Application extends JFrame {
	public final Model model;
	ServerAccess serverAccess;
	/**
	 * Create the frame.
	 */
	public Application(Model model) {
		this.model = model;
		setForeground(Color.RED);
		setFont(new Font("Dialog", Font.BOLD, 12));
		setTitle("Wordsweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 548, 399);
		Log_in login=new Log_in(model);
		this.add(login.getContentPane());
		
		login.Multiplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateGameController(Application.this, model).process(login.multi_PlayerName.getText());
				Application.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Application.this.setVisible(false);
			//	Board board=new Board();   之后看board需要在哪里操作
				Managing_User multi= new Managing_User();
				multi.setVisible(true);
			}
		});
		
		login.Practice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateGameController(Application.this, model).process(null);
				Application.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Application.this.setVisible(false);
			//	Board board=new Board();   之后看board需要在哪里操作
				Practice practice= new Practice();
				practice.setVisible(true);
			}
		});
		
		login.Join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinGameController(Application.this, model).process(login.join_gameid.getText(),login.join_PlayerName.getText());
				Application.this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Application.this.setVisible(false);
			//	Board board=new Board();
				Player join= new Player();
				join.setVisible(true);
			}
		});
		
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
