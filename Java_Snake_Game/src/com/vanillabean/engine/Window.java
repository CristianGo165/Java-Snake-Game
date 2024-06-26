package com.vanillabean.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Window {

	public JFrame getFrame() {
		return frame;
	}

	private JFrame frame;
	private BufferedImage image;
	private Canvas canvas;
	
	
	public BufferedImage getImage() {
		return image;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	private Graphics graphics;
	private BufferStrategy bufferStrategy;

	public Window(GameContainer gc) {
		// Image Stored in RAM
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB);
		canvas = new Canvas();
		Dimension dimension = new Dimension((int) (gc.getWidth() * gc.getScale()),
				(int) (gc.getHeight() * gc.getScale()));
		canvas.setPreferredSize(dimension);
		canvas.setMaximumSize(dimension);
		canvas.setMinimumSize(dimension);

		frame = new JFrame(gc.getTitle());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		graphics = bufferStrategy.getDrawGraphics();
	}

	public void update() {
		graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
		bufferStrategy.show();
	}
}