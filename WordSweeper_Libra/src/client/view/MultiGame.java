package client.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.controller.requestController.ExitGameController;
import client.model.Model;

/**
 * The GUI of multi-players mode game.
 * 
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */
public class MultiGame extends JFrame {
	
	private JPanel contentPane;
	private JPanel leftBoardPanel;	
	private JPanel rightGameInfoPanel;
	
	private Model model;
	private Application app;
	
	/**MultiGame constructor*/
	public MultiGame (Model model, Application app) {
		this.model = model;
		this.app = app;	
		this.app.setMultiGame(this);
		this.rightGameInfoPanel = new RightGameInfoBoard(model, app);
		this.leftBoardPanel = new LeftBoardPanel(model, app, this.rightGameInfoPanel);
		initiate();
		
	}
	
	/**initiate the GUI object for multi mode.<br>
	 * This GUI consists of leftBoardPanel and rightGameInfoPanel.
	 */
	private void initiate(){		
		setTitle("Wordsweeper");		
		setBounds(100, 100, 700, 520);
		setAlwaysOnTop(false);
		
		this.addWindowListener(new CloseNotifier(app, model));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		contentPane.add(rightGameInfoPanel);	
		contentPane.add(leftBoardPanel);
	}

	public JPanel getLeftBoardPanel() {
		return this.leftBoardPanel;
	}

	public JPanel getRightGameInfoPanel() {
		return this.rightGameInfoPanel;
	}
	
}


/**
 * The CloseNotifier is a class extends WindowAdapter.
 * In the case that the player simply close the window, a message will still be sent to the server to remove this player.
 * @author You Zhou, Qingquan Zhao, Han Bao, Ruochen Shi (Authors contribute equally)
 *
 */

class CloseNotifier extends WindowAdapter{
	
	Application app;
	Model model;
	
	public CloseNotifier(Application app, Model model) {
		this.app = app;
		this.model = model;
	}
	/**
	 * Call the exit game controller to send a exit game message to server.
	 */
	public void windowClosing(WindowEvent event){
		new ExitGameController(app, model).process();
		System.exit(0);
	}
}

