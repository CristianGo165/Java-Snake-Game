package com.javaSnakeGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PlayerData {
	File playerData;
	FileWriter dataWriter;
	String data;
	
	
	public PlayerData(){
		playerData = new File("C:\\Users\\cgodi\\eclipse-workspace\\Java_Snake_Game\\res\\playerDat\\playerDat.txt");
	}
	
	public void writeData(String playerName, int[] playerScores, boolean append) {
		if(append) {
			data += playerName;
		} else {
			data = playerName;
		}
		for(int i = 0 ; i < playerScores.length; i++) {
			data += "-"+playerScores[i];
		}
		try {
			dataWriter = new FileWriter(playerData, append);
			dataWriter.write(data);
			dataWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void readData(Player player) {
		Scanner scanner;
		String[] tempData = new String[4];
		try {
			scanner = new Scanner(playerData);
			while(scanner.hasNextLine()) {
				tempData = scanner.nextLine().split("-", 0);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		player.setPlayerName(tempData[0]);
		
		for(int i = 0; i < 3; i++) {
				player.setTopScores(Integer.parseInt(tempData[i+1]), i);
		}

	}
}
