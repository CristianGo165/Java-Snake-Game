package com.javaSnakeGame;

import java.awt.event.KeyEvent;

import com.vanillabean.engine.AbstractGame;
import com.vanillabean.engine.GameContainer;
import com.vanillabean.engine.Renderer;
//import com.vanillabean.engine.gfx.Image;
import com.vanillabean.engine.gfx.ImageTile;

public class Game extends AbstractGame{
	
	//Test
	private ImageTile image;
	//Test
	
	
    private Snake snake;
    private Board board;

    public final int DIRECTION_NONE = 0;
    public final int DIRECTION_UP = 1;
    public final int DIRECTION_DOWN = 2;
    public final int DIRECTION_LEFT = 3;
    public final int DIRECTION_RIGHT = 4;

    public int currentDirection;
    public boolean isGameOver;


    public Game(Snake initialSnake, Board board){
        this.snake = initialSnake;
        this.board = board;
        image = new ImageTile("/Snake Game Tiles.png", 16, 16);
        
    }

    public Snake GetSnake(){
        return snake;
    }

    public Board GetCurrentBoard(){
        return board;
    }

    public boolean ReturnGameOver(){
        return isGameOver;
    }

    public void SetIsGameOver(boolean isGameOver){
        this.isGameOver = isGameOver;
    }
    
    public int GetCurrentDirection(){
        return currentDirection;
    }

    public void SetPlayerDirection(int newDirection){
        currentDirection = newDirection;
    }



    private Cell GetNextCell(Cell currentPosition){
    
        int row = currentPosition.GetRowCell();
        int col = currentPosition.GetColCell();
        
        if(currentDirection == DIRECTION_UP){col--;}
        else if(currentDirection == DIRECTION_DOWN){col++;}
        else if(currentDirection == DIRECTION_LEFT){row--;}
        else if(currentDirection == DIRECTION_RIGHT){row++;}
        
        
    
        Cell nextCell = board.GetBoard()[row][col];
        return nextCell;
    }
    
    
    
    public static void main(String[] args) {
    	
    	Cell initialCell = new Cell(5, 5);
        Snake snake = new Snake(initialCell);
        Board board = new Board(11, 11);
        Game newSnakeGame = new Game(snake, board);
        newSnakeGame.isGameOver = false;
        newSnakeGame.currentDirection = newSnakeGame.DIRECTION_NONE;
        
		board.GenerateApple();

        
        GameContainer gc = new GameContainer(newSnakeGame);
    	gc.start();
    }

	@Override
	public void update(GameContainer gc, float deltaTime) {
		
		
		if(gc.getInput().iskeyPressed(KeyEvent.VK_W)) {
			System.out.println("W is pressed");
			currentDirection = DIRECTION_UP;
		}
		else if(gc.getInput().iskeyPressed(KeyEvent.VK_A)) {
			System.out.println("A is pressed");
			currentDirection = DIRECTION_LEFT;
		}
		else if(gc.getInput().iskeyPressed(KeyEvent.VK_S)) {
			System.out.println("S is pressed");
			currentDirection = DIRECTION_DOWN;
		}
		else if(gc.getInput().iskeyPressed(KeyEvent.VK_D)) {
			System.out.println("D is pressed");
			currentDirection = DIRECTION_RIGHT;
		}
		
		if(!isGameOver){
            if(currentDirection != DIRECTION_NONE){
                Cell nextCell = GetNextCell(snake.GetSnakeHead());
                if(snake.CheckWallCollision(nextCell)){
                    currentDirection = DIRECTION_NONE;
                    isGameOver = false;
                } else{
                    snake.MoveSnake(nextCell);
                    
                    //System.out.println("snake Moved to: " + nextCell.GetRowCell() + ", " + nextCell.GetColCell());
                    //System.out.println(GetCurrentDirection());
                    
                    if(nextCell.getTypeCell() == CellType.FOOD){
                        snake.GrowSnake();
                        board.GenerateApple();
                    }
                }
            }
        }
        
		
		
		//System.out.println("X: " + gc.getInput().getMouseX() + " Y: " + gc.getInput().getMouseY());
		
		
		
		/*Animation Code
		temp += deltaTime;
		if(temp > 3) {
			temp = 0
		}
		Animation Code*/
	}
	
	//Animation Code
	//float temp = 0;
	
	@Override
	public void render(GameContainer gc, Renderer renderer) {
		for(int x = 0 ; x < (board.ROW_COUNT * 16) ; x += 16) {
			for(int y = 0; y < (board.COL_COUNT * 16) ; y+= 16) {
				
				if(board.GetBoard()[x/16][y/16].getTypeCell() == CellType.SPACE) {
					renderer.drawImageTile(image, x, y, 1, 1);
				}
				if(board.GetBoard()[x/16][y/16].getTypeCell() == CellType.FOOD) {
					renderer.drawImageTile(image, x, y, 2, 0);
				}
				if(board.GetBoard()[x/16][y/16].getTypeCell() == CellType.SNAKE_BODY){
					renderer.drawImageTile(image, x, y, 0, 0);
				}
			}
		}
		
		
		
		renderer.drawImageTile(image, gc.getInput().getMouseX(), gc.getInput().getMouseY(), 3, 3);
	}
}