package com.javaSnakeGame;

import java.util.LinkedList;

public class Snake {
    public LinkedList<Cell> snakeCells = new LinkedList<>();

    public Cell head;
    public Cell tail;

    Snake(Cell initialPos){
        snakeCells.add(initialPos);
        head = initialPos;
        head.SetTypeCell(CellType.SNAKE_BODY);
        
        //System.out.println("Snake Head At: " + head.GetRowCell() + ", " + head.GetColCell());
    }
    
    public void resetSnake(Cell initialPos) {
    	snakeCells = new LinkedList<>();
    	snakeCells.add(initialPos);
    	head = initialPos;
    	head.SetTypeCell(CellType.SNAKE_BODY);
    }

    

    public void MoveSnake(Cell moveCell, boolean grow){
        //Update Tail Cell
    	if(!grow){
    		tail = snakeCells.removeLast();
            tail.SetTypeCell(CellType.SPACE);
    	}

        //Head Update
        head = moveCell;
        head.SetTypeCell(CellType.SNAKE_BODY);
        //Update snake body Linked List
        snakeCells.addFirst(head);
        //System.out.println(snakeCells.size());
    }

    public boolean CheckWallCollision(Cell moveCell){
        return moveCell.getTypeCell() == CellType.WALL;
    }
    
    public boolean checkSnakeCollision(Cell moveCell){
        return moveCell.getTypeCell() == CellType.SNAKE_BODY;
    }
    
    public void getCollisionCell(Cell moveCell) {
    	System.out.println(moveCell.getTypeCell()); 
    }
    

    public LinkedList<Cell> GetSnakeBody(){
        return snakeCells;
    }

    public Cell GetSnakeHead(){
        //return snakeCells.getFirst();
    	return head;
    }
    
}
