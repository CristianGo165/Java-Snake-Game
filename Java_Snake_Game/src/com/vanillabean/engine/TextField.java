package com.vanillabean.engine;
import java.awt.*;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class TextField{
	private JTextField textField;
	
	private JFrame frame;
	 
	 
	public TextField() {
		 
	 }
	
	public void updateText(){
		frame = new JFrame("Enter Your Name");
		textField = new JTextField(16);
		Font fo = new Font("Arial", Font.BOLD, 20);
		textField.setFont(fo);
		JPanel p = new JPanel();
		p.add(textField);
		frame.add(p);
		frame.setSize(325, 75);
		frame.setResizable(false);
		p.setBackground(Color.BLACK);
		textField.setBackground(Color.DARK_GRAY);
		textField.setForeground(Color.WHITE);
		frame.setVisible(true);
	 }
	
	public String getText() {
		 return textField.getText();
	 }
	public void closeTextField() {
		 if(frame != null) {
			 WindowEvent winEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
			 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winEvent);
			 System.out.println("Test");
			 frame.setVisible(false);
			 frame.dispose();
		 }
	}
}