package client.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.ServerAccess;
import client.controller.MGMouseListener;
import client.controller.requestController.CreateGameController;
import client.controller.requestController.JoinGameController;
import client.model.Model;


public class Application extends JFrame {

	public Model model;	
	JPanel contentPane;
	ServerAccess serverAccess;

	JTextArea requestArea;
	JTextArea responseArea;
	
	JScrollPane clientRequests;
	JScrollPane serverOutput;
	
	JButton btnPractice;
	JButton btnCreateGame;
	JButton btnJoinGame;
	JTextField gameNumberField;
	JTextField playerNameField;
	JPasswordField passWordField;
	
	MultiGame mg;
	
	String playerName;
	String password;
	String gameNumber;
	
	public MultiGame getMultiGame(){
		return mg;
	}
	
	public void setMultiGame(MultiGame mg){
		this.mg = mg;
	}
	
	public String getPassword() {
		return password;
	}
	public String getGameNumber() {
		return gameNumber;
	}
	public String getPlayerName() {
		return playerName;
	}
	
	public Application(Model model) {
		this.model = model;
		initiate();
	}
	
	private void disableInputs(){
		btnPractice.setEnabled(false);
		btnCreateGame.setEnabled(false);
		btnJoinGame.setEnabled(false);
		gameNumberField.setEditable(false);
		playerNameField.setEditable(false);
		passWordField.setEditable(false);
	}
	
	public void enableInputs(){
		btnPractice.setEnabled(true);
		btnCreateGame.setEnabled(true);
		btnJoinGame.setEnabled(true);
		gameNumberField.setEditable(true);
		playerNameField.setEditable(true);
		passWordField.setEditable(true);
	}
	
	
	private void initiate(){		
		this.setTitle("WordSweeper Login");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
		this.setBounds(100, 100, 500, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel gameNumberLabel = new JLabel("Game Number: ");
		gameNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		gameNumberLabel.setBounds(45, 60, 133, 30);
		contentPane.add(gameNumberLabel);
		
		gameNumberField =  new JTextField(10);
		gameNumberField.setBounds(200, 60, 164, 30);
		contentPane.add(gameNumberField);
		gameNumberField.setColumns(10);
		
		JLabel playerNameLabel = new JLabel("Player Name: ");
		gameNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		playerNameLabel.setBounds(95, 110, 133, 30);
		contentPane.add(playerNameLabel);
		
		playerNameField =  new JTextField(10);
		playerNameField.setBounds(200, 110, 164, 30);
		contentPane.add(playerNameField);
		playerNameField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password: ");
		gameNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setBounds(113, 160, 133, 30);
		contentPane.add(passwordLabel);
		
		passWordField = new JPasswordField();
		passWordField.setBounds(200, 160, 164, 30);
		contentPane.add(passWordField);
		passWordField.setColumns(10);
		
		btnPractice = new JButton("Practice");
		btnPractice.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Practice pg = new Practice(model, Application.this);			
				pg.setVisible(true);
				Application.this.setVisible(false);
			}
		});
		btnPractice.setBounds(70, 250, 120, 40);
		contentPane.add(btnPractice);
		
		btnCreateGame = new JButton("Create Game");
		btnCreateGame.addMouseListener(new MGMouseListener(){
			public boolean notHasPlayerName(){
				playerName = playerNameField.getText();
				if(playerName.length() == 0){
					JOptionPane.showMessageDialog(Application.this, "playerName can not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
					playerNameField.requestFocus();
					return true;
				}
				return false;
			}
			@Override
			public void mousePressed(MouseEvent e) {			
				if(!notHasPlayerName()){
					new CreateGameController(Application.this, model).process();
					Application.this.disableInputs();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!notHasPlayerName()) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e1) { // TODO Auto-generated
														// catch block
						e1.printStackTrace();
					}
					mg = new MultiGame(model, Application.this);
					mg.setVisible(true);
				}
			}
		});

		btnCreateGame.setBounds(195, 250, 120, 40);
		contentPane.add(btnCreateGame);
		
		btnJoinGame = new JButton("Join Game");
		btnJoinGame.addMouseListener(new MGMouseListener(){
			public boolean notHasPlayerNameAndGameId(){			
				gameNumber = gameNumberField.getText();
				playerName = playerNameField.getText();
				if(gameNumber.length() == 0){
					JOptionPane.showMessageDialog(Application.this, "gameID can not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
					gameNumberField.requestFocus();
					return true;
				}else if(playerName.length() == 0){
					JOptionPane.showMessageDialog(Application.this, "playerName can not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
					playerNameField.requestFocus();
					return true;
				}
				return false;
			}			
			@Override
			public void mousePressed(MouseEvent e) {
				if(!notHasPlayerNameAndGameId()){				
					new JoinGameController(Application.this, model).process();
					Application.this.disableInputs();
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if(!notHasPlayerNameAndGameId()){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e1) { // TODO Auto-generated
														// catch block
						e1.printStackTrace();
					}
					mg = new MultiGame(model, Application.this);
					mg.setVisible(true);
				}
			}			
		});
		
		btnJoinGame.setBounds(320, 250, 120, 40);
		contentPane.add(btnJoinGame);
		
		
		JLabel requestInfo = new JLabel("Request Information");
		requestInfo.setBounds(20, 330, 300, 20);
		JLabel responseInfo = new JLabel("Response Information");
		responseInfo.setBounds(20, 500, 300, 20);
		
		clientRequests = new JScrollPane();
		serverOutput = new JScrollPane();
		
		requestArea = new JTextArea();
		clientRequests.setViewportView(requestArea);
		clientRequests.setBounds(20, 360, 450, 130);


		responseArea = new JTextArea();
		serverOutput.setViewportView(responseArea);
		serverOutput.setBounds(20, 530, 450, 130);

		
		contentPane.add(requestInfo);
		contentPane.add(clientRequests);
		contentPane.add(responseInfo);
		contentPane.add(serverOutput);
	}

	/** Record the means to communicate with server. */
	public void setServerAccess(ServerAccess access) {
		this.serverAccess = access;
	}
	
	/** Get the server access object. */
	public ServerAccess getServerAccess() {
		return serverAccess;
	}
	
	/** Navigation access to actionable elements in the GUI. */
	public JTextArea getRequestArea() {
		return requestArea;
	}
	
	/** Navigation access to actionable elements in the GUI. */
	public JTextArea getResponseArea() {
		return responseArea;
	}
}
