package com.vanillabean.engine;

public abstract class AbstractGame {
	public abstract void update(GameContainer gc, float deltaTime);

	public abstract void render(GameContainer gc, Renderer renderer);
}
