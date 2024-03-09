package com.vanillabean.engine;

import javax.swing.ImageIcon;

//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;

public class GameContainer implements Runnable {

	private Thread thread;
	private boolean running = false;
	private final double UPDATE_LIMIT = 1.0 /10.0;

	// Window Variables
	private int width = 320, height = 320;
	private float scale = 2;
	private String title = "Java Snake Game v1.0";
	// Window Variables

	// Window Setup
	private Window window;
	// Window Setup
	
	//Player name field setup
	private TextField textField;
	//Player name field setup;

	// Renderer Setup
	private Renderer renderer;
	// Renderer Setup

	// Input Setup
	private Input input;
	// Input Setup

	// Abstract Game Setup
	private AbstractGame aGame;
	// Abstract Game Setup
	
	//Setup Logo
	private ImageIcon logo;
	//Setup Logo

	public Window getWindow() {
		return window;
	}
	
	public TextField getTextField() {
		return this.textField;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public GameContainer(AbstractGame game) {
		this.aGame = game;
	}

	public Input getInput() {
		return input;
	}

	public void start() {
		window = new Window(this);
		logo = new ImageIcon("/home/cristiango165/eclipse-workspace/Java_Snake_Game/res/tiles/Snake Game Logo.png");
		window.getFrame().setIconImage(logo.getImage());

		renderer = new Renderer(this);
		
		textField = new TextField();

		input = new Input(this);

		thread = new Thread(this);
		thread.run();
	}

	public void stop() {

	}

	public void run() {

		running = true;

		double firstTime = 0;
		double lastTime = System.nanoTime() / 1000000000.0;
		double passedTime = 0;
		double unprocessedTime = 0;
		boolean render = false;

		double frameTime = 0;
		int frames = 0;
		int fps = 0;

		while (running) {

			render = false;

			firstTime = System.nanoTime() / 1000000000.0;
			passedTime = firstTime - lastTime;
			lastTime = firstTime;

			unprocessedTime += passedTime;

			frameTime += passedTime;

			while (unprocessedTime >= UPDATE_LIMIT) {
				unprocessedTime -= UPDATE_LIMIT;
				render = true;

				aGame.update(this, (float) UPDATE_LIMIT);

				input.update();

				if (frameTime >= 1.0) {
					frameTime = 0;
					fps = frames;
					frames = 0;
				}
			}

			if (render) {

				renderer.clear();
				aGame.render(this, renderer);
				renderer.drawText("FPS: " + fps, 0, 10, 0xff00ffff);
				window.update();
				frames++;

			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		dispose();
	}

	private void dispose() {

	}
}
