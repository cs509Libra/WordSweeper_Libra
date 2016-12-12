package client.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client.controller.MGMouseListener;
import client.controller.requestController.ExitGameController;
import client.controller.requestController.LockGameController;
import client.controller.requestController.ResetGameController;
import client.controller.responseController.SampleClientMessageHandler;
import client.model.Model;
import client.view.LeftBoardPanel;

/**
 * Right part of the GUI. This class is initialized in {@link MultiGame}.
 * <p>
 * When initialize this class, the constructor uses {@link #initiate()} to
 * initialize
 * <p>
 * When {@link SampleClientMessageHandler}(Controllers) receive responses from
 * the server, Controllers will first change the according attributes in models,
 * then call {@link #updateGameInfoBoard()} to update the GUI.
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 * 
 */
public class RightGameInfoBoard extends JPanel {

	private Model model;
	private Application app;

	private JScrollPane jScrollPane1; /* display players' names and scores */
	private JTextArea playersListArea;/* display players' names and scores */
	private JLabel myScoreFromSever; /* display local player's score */
	private JLabel gameIdLabel; /* display Game ID */
	private JLabel managerName; /* display manager players name */
	private JButton lockBtn; /* lock game button */
	private JButton resetBtn; /* reset game button */
	private JLabel playersNamesLabel; /* column name for player name */
	private JLabel playersScoresLabel;/* column name for player score */
	JLabel gameLockResetLabel;/* the status of reset game and lock game */

	/**
	 * Constructor of {@link RightGameInfoBoard}
	 * 
	 * @param model
	 * @param app
	 */
	public RightGameInfoBoard(Model model, Application app) {
		this.model = model;
		this.app = app;
		initiate();
	}

	/**
	 * Initialization of {@link RightGameInfoBoard}
	 */
	private void initiate() {
		setBorder(BorderFactory.createTitledBorder("Game Information"));
		setBounds(380, 5, 300, 470);
		setLayout(null);

		gameLockResetLabel = new JLabel("");
		gameLockResetLabel.setForeground(Color.RED);
		gameLockResetLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		gameLockResetLabel.setBounds(10, 20, 200, 30);
		add(gameLockResetLabel);

		JLabel gameIdTitle = new JLabel("Game ID:");
		gameIdTitle.setBounds(10, 40, 65, 28);
		add(gameIdTitle);

		gameIdLabel = new JLabel("");
		gameIdLabel.setBounds(80, 40, 65, 28);
		add(gameIdLabel);

		JButton quit = new JButton("Quit");
		quit.addActionListener(new ActionListener() {// quit game button
			@Override
			public void actionPerformed(ActionEvent e) {
				new ExitGameController(app, model).process();
				/* notify the player that the server is offline */
				if (((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).isDisconnected(model.isWaitingResponse) == true) {
					((LeftBoardPanel) app.getMg().getLeftBoardPanel()).getMessageLabel()
							.setText("You are offline! Please close the game.");
					((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
					gameLockResetLabel.setText("");
				}
			}
		});
		quit.setForeground(Color.BLUE);
		quit.setFont(new Font("Tahoma", Font.BOLD, 12));
		quit.setSize(getPreferredSize());
		quit.setBounds(190, 20, 100, 25);
		add(quit);

		JLabel myNameLabel = new JLabel("Name:          " + this.model.getPlayer().getName());
		myNameLabel.setBounds(10, 57, 180, 28);
		add(myNameLabel);

		JLabel myScoreLabel = new JLabel("My Score:");
		myScoreLabel.setBounds(10, 75, 65, 28);
		add(myScoreLabel);

		myScoreFromSever = new JLabel("0");
		myScoreFromSever.setBounds(80, 78, 120, 21);
		myScoreFromSever.setFont(new Font("Tahoma", Font.BOLD, 14));
		;
		add(myScoreFromSever);

		JLabel playerListLabel = new JLabel("Players List: ");
		playerListLabel.setBounds(10, 100, 120, 25);
		add(playerListLabel);

		playersNamesLabel = new JLabel("Name");
		playersNamesLabel.setBounds(100, 100, 120, 25);
		playersNamesLabel.addMouseListener(new MGMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playersListArea.setText(model.getGame().getPlayersListByName());
			}
		});
		add(playersNamesLabel);

		playersScoresLabel = new JLabel("Score");
		playersScoresLabel.setBounds(200, 100, 120, 25);
		playersScoresLabel.addMouseListener(new MGMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				playersListArea.setText(model.getGame().getPlayersListByScore());
			}
		});
		add(playersScoresLabel);

		playersListArea = new JTextArea("");
		playersListArea.setForeground(Color.BLACK);
		playersListArea.setColumns(20);
		playersListArea.setRows(10);
		playersListArea.setEditable(false);

		jScrollPane1 = new JScrollPane();
		jScrollPane1.setBounds(10, 125, 280, 200);
		jScrollPane1.setViewportView(playersListArea);
		add(jScrollPane1);

		JPanel managerPower = new JPanel();
		managerPower.setBounds(10, 350, 280, 110);
		managerPower.setLayout(null);
		add(managerPower);

		JLabel managerNameLabel = new JLabel("Manager Name: ");
		managerNameLabel.setBounds(10, 10, 120, 15);
		managerPower.add(managerNameLabel);

		managerName = new JLabel();
		managerName.setText("You");
		managerName.setBounds(129, 10, 100, 15);
		managerPower.add(managerName);

		lockBtn = new JButton("Lock game");
		lockBtn.setEnabled(false);
		lockBtn.setBackground(Color.WHITE);
		lockBtn.setForeground(Color.BLUE);
		lockBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		lockBtn.setBounds(80, 40, 110, 30);
		managerPower.add(lockBtn);

		resetBtn = new JButton("Reset game");
		resetBtn.setEnabled(false);
		resetBtn.setForeground(Color.BLUE);
		resetBtn.setBackground(Color.WHITE);
		resetBtn.setFont(new Font("Tahoma", Font.BOLD, 12));
		resetBtn.setBounds(80, 70, 110, 30);
		managerPower.add(resetBtn);

		resetBtn.addActionListener(new ActionListener() {// reset game button
			@Override
			public void actionPerformed(ActionEvent e) {
				new ResetGameController(app, model).process();
				if (((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).isDisconnected(model.isWaitingResponse) == true) {
					((LeftBoardPanel) app.getMg().getLeftBoardPanel()).getMessageLabel()
							.setText("Disconnected  from  the  server! Unable to reset!");
					((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
					gameLockResetLabel.setText("");
				} else {
					gameLockResetLabel.setText("Game has been RESET!");
					((LeftBoardPanel) app.getMg().getLeftBoardPanel()).getMessageLabel().setText("");
				}
			}
		});

		lockBtn.addActionListener(new ActionListener() {// lock game button
			@Override
			public void actionPerformed(ActionEvent e) {
				new LockGameController(app, model).process();				
				if (((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).isDisconnected(model.isWaitingResponse) == true) {
					((LeftBoardPanel) app.getMg().getLeftBoardPanel()).getMessageLabel()
							.setText("Disconnected  from  the  server! Unable to lock!");
					((LeftBoardPanel) app.getMultiGame().getLeftBoardPanel()).refreshBoard();
					gameLockResetLabel.setText("");
				} else {
					gameLockResetLabel.setText("Game has been Locked!");
					lockBtn.setEnabled(false);
				}

			}
		});

		updateGameInfoBoard();
	}

	/**
	 * When {@link SampleClientMessageHandler}(Controllers) receive responses
	 * from the server, Controllers will first change the according attributes
	 * in models, then call this method to update the GUI.
	 */
	public void updateGameInfoBoard() {
		gameLockResetLabel.setText("");
		gameIdLabel.setText(model.getGame().getGameID());
		myScoreFromSever.setText(String.valueOf(model.getPlayer().getScore()));
		playersListArea.setText(model.getGame().getPlayersListByName());
		managerName.setText(model.getGame().getManagingUser());
		if (model.getPlayer().isManager()) {// check manager player
			lockBtn.setEnabled(true);
			resetBtn.setEnabled(true);
		}
		if (model.getGame().isLocked()) {
			setBorder(BorderFactory.createTitledBorder("Game Information <Locked>"));
			lockBtn.setEnabled(false);
		}
	}

}
