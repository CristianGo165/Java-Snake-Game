package com.javaSnakeGame;

public class Player {
	public int score;
	
	public int[] topScores = new int[3];
	
	String playerName;
	
	public int lives;
	
	public Player(String playerName, int lives) {
		this.playerName = playerName;
		this.score = 0;
		this.lives = lives;
	}
	public String getPlayerName() {
		return playerName;
	}
	
	public int getPlayerScore() {
		return score;
	}
	
	public void setPlayerScore(int newScore) {
		score = newScore;
	}
	
	public void resetPlayerScore() {
		score = 0;
	}
	
	public int getPlayerLives() {
		return lives;
	}
	
	public void changeLivesBy(int value) {
		lives += value;
	}
	
	public int[] getTopScores() {
		return topScores;
	}
	
	public void setTopScores(int scoreSet, int index) {
		topScores[index] = scoreSet;
	}
	
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	
	
	
	
}
