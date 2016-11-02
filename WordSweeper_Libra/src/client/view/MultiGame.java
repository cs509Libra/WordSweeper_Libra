package client.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import client.view.Application;
import client.model.Model;

public class MultiGame extends JFrame {

	private JPanel contentPane;
//	public final Model model;
//	public Application app;
//	
	public MultiGame() {
		setTitle("WordSweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 740, 470);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnUp = new JButton("^");
		btnUp.setBounds(100, 5, 100, 40);
		panel.add(btnUp);
		
		JButton button_15 = new JButton("<");
		button_15.setBounds(5, 100, 40, 100);
		panel.add(button_15);
		
		JButton button_16 = new JButton(">");
		button_16.setBounds(255, 100, 40, 100);
		panel.add(button_16);
		
		JButton btnV = new JButton("v");
		btnV.setBounds(100, 255, 100, 40);
		panel.add(btnV);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(50, 50, 50, 50);
		panel.add(btnNewButton);
		
		JButton button = new JButton("New button");
		button.setBounds(100, 50, 50, 50);
		panel.add(button);
		
		JButton button_1 = new JButton("New button");
		button_1.setBounds(150, 50, 50, 50);
		panel.add(button_1);
		
		JButton button_2 = new JButton("New button");
		button_2.setBounds(200, 50, 50, 50);
		panel.add(button_2);
		
		JButton button_3 = new JButton("New button");
		button_3.setBounds(50, 100, 50, 50);
		panel.add(button_3);
		
		JButton button_4 = new JButton("New button");
		button_4.setBounds(100, 100, 50, 50);
		panel.add(button_4);
		
		JButton button_5 = new JButton("New button");
		button_5.setBounds(150, 100, 50, 50);
		panel.add(button_5);
		
		JButton button_6 = new JButton("New button");
		button_6.setBounds(200, 100, 50, 50);
		panel.add(button_6);
		
		JButton button_7 = new JButton("New button");
		button_7.setBounds(50, 150, 50, 50);
		panel.add(button_7);
		
		JButton button_8 = new JButton("New button");
		button_8.setBounds(100, 150, 50, 50);
		panel.add(button_8);
		
		JButton button_9 = new JButton("New button");
		button_9.setBounds(150, 150, 50, 50);
		panel.add(button_9);
		
		JButton button_10 = new JButton("New button");
		button_10.setBounds(200, 150, 50, 50);
		panel.add(button_10);
		
		JButton button_11 = new JButton("New button");
		button_11.setBounds(50, 200, 50, 50);
		panel.add(button_11);
		
		JButton button_12 = new JButton("New button");
		button_12.setBounds(100, 200, 50, 50);
		panel.add(button_12);
		
		JButton button_13 = new JButton("New button");
		button_13.setBounds(150, 200, 50, 50);
		panel.add(button_13);
		
		JButton button_14 = new JButton("New button");
		button_14.setBounds(200, 200, 50, 50);
		panel.add(button_14);
	}
//	
//	private char obtainLetter(){
//		Character letter='H';
//		
//		return letter;
//	}
}
