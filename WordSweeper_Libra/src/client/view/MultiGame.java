package client.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.model.Model;

public class MultiGame extends JFrame {
	
	private JPanel contentPane;
	private JPanel leftBoardPanel;	
	private JPanel rightGameInfoPanel;
	
	private Model model;
	private Application app;
	
	public MultiGame (Model model, Application app) {
		this.model = model;
		this.app = app;	
		this.app.setMultiGame(this);
		this.rightGameInfoPanel = new RightGameInfoBoard(model, app);
		this.leftBoardPanel = new LeftBoardPanel(model, app, this.rightGameInfoPanel);
		initiate();
		
	}
	
	private void initiate(){		
		setTitle("Wordsweeper");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 520);
		setAlwaysOnTop(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		rightGameInfoPanel = new RightGameInfoBoard(model, app);
		contentPane.add(rightGameInfoPanel);	
		
//		leftBoardPanel = new LeftBoardPanel(model, app, rightGameInfoPanel);
		contentPane.add(leftBoardPanel);

	}

	public JPanel getLeftBoardPanel() {
		return this.leftBoardPanel;
	}

	public JPanel getRightGameInfoPanel() {
		return this.rightGameInfoPanel;
	}
	
	
}
