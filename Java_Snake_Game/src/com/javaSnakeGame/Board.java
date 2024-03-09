package com.javaSnakeGame;

//import java.util.concurrent.ThreadLocalRandom;

public class Board {
    final int ROW_COUNT;
    final int COL_COUNT;
    final int borderSize = 1;
    private Cell[][] board;
    
    public Board(int ROW_COUNT, int COL_COUNT){

        this.ROW_COUNT = ROW_COUNT;
        this.COL_COUNT = COL_COUNT;

        board = new Cell[ROW_COUNT][COL_COUNT];

        for(int i = 0 ; i < ROW_COUNT ; i++){
            for(int j = 0 ; j < COL_COUNT ; j++){
                board[i][j] = new Cell(i, j);
                board[i][j].SetTypeCell(CellType.SPACE);
                if(i == 0 || i == ROW_COUNT - 1 || j == 0 || j == COL_COUNT - 1) {
                    board[i][j].SetTypeCell(CellType.WALL);
                }
            }
        }
    }
    
    
    

    public int getROW_COUNT() {
		return ROW_COUNT;
	}




	public int getCOL_COUNT() {
		return COL_COUNT;
	}




	public Cell[][] GetBoard(){
        return board;
    }

    public void GenerateApple(){
        while(true){
        	int foodRow = (int) (Math.random() * ((ROW_COUNT-1) - borderSize) + borderSize);
        	int foodCol = (int) (Math.random() * ((ROW_COUNT-1) - borderSize) + borderSize);

            if(board[foodRow][foodCol].getTypeCell() != CellType.SNAKE_BODY){
            	//System.out.println("Food At: " + foodRow + ", " + foodCol);
                board[foodRow][foodCol].SetTypeCell(CellType.FOOD);
                break;
            }
        }
    }
}
