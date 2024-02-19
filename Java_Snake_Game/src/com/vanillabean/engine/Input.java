package com.vanillabean.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	GameContainer gc;

	private final int NUM_KEYS = 256;
	private boolean[] keys = new boolean[NUM_KEYS];
	private boolean[] keysLast = new boolean[NUM_KEYS];

	private final int NUM_BUTTONS = 5;
	private boolean[] buttons = new boolean[NUM_BUTTONS];
	private boolean[] buttonsLast = new boolean[NUM_BUTTONS];

	private int mouseX, mouseY;
	private int scroll;

	public Input(GameContainer gc) {
		this.gc = gc;
		mouseX = 0;
		mouseY = 0;
		scroll = 0;

		gc.getWindow().getCanvas().addKeyListener(this);
		gc.getWindow().getCanvas().addMouseMotionListener(this);
		gc.getWindow().getCanvas().addMouseListener(this);
		gc.getWindow().getCanvas().addMouseWheelListener(this);

	}

	public void update() {

		// Makes sure scroll doesn't stay -1 or 1 every frame
		scroll = 0;

		for (int i = 0; i < NUM_KEYS; i++) {
			keysLast[i] = keys[i];
		}

		for (int i = 0; i < NUM_BUTTONS; i++) {
			buttonsLast[i] = buttons[i];
		}

	}

	// isKey tells that it gives boolean
	public boolean iskey(int keyCode) {
		return keys[keyCode];
	}

	public boolean iskeyReleased(int keyCode) {
		return !keys[keyCode] && keysLast[keyCode];
	}

	public boolean iskeyPressed(int keyCode) {
		return keys[keyCode] && !keysLast[keyCode];
	}

	public boolean isButton(int button) {
		return buttons[button];
	}

	public boolean iskButtonReleased(int button) {
		return !buttons[button] && buttonsLast[button];
	}

	public boolean isButtonPressed(int button) {
		return buttons[button] && !buttonsLast[button];
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		scroll = e.getWheelRotation();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Divide By Scale So that Mouse Position Matches On Different Resolutions
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Divide By Scale So that Mouse Position Matches On Different Resolutions
		mouseX = (int) (e.getX() / gc.getScale());
		mouseY = (int) (e.getY() / gc.getScale());

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
		buttons[e.getButton()] = false;

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}
}
