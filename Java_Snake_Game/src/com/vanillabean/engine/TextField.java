package com.vanillabean.engine;
import java.awt.*;

import javax.swing.*;

public class TextField{
	
	private JTextField textField;
	
	private JFrame frame;
		 
	public TextField() {
		 
	 }
	
	public void updateText(){
		frame = new JFrame("Enter Your Name");
		textField = new JTextField(16);
		Font font = new Font("Arial", Font.BOLD, 20);
		textField.setFont(font);
		JPanel panel = new JPanel();
		panel.add(textField);
		frame.add(panel);
		frame.setSize(325, 75);
		frame.setResizable(false);
		panel.setBackground(Color.BLACK);
		textField.setBackground(Color.DARK_GRAY);
		textField.setForeground(Color.WHITE);
		frame.setVisible(true);
	 }
	
	public String getText() {
		 return textField.getText();
	 }
	
	public void closeTextField() {
		 if(frame != null) {
			 frame.setVisible(false);
			 frame.dispose();
		 }
	}
}