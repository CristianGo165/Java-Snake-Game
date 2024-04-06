package com.javaSnakeGame;

import java.awt.event.KeyEvent;
import java.util.Scanner;

import com.vanillabean.engine.AbstractGame;
import com.vanillabean.engine.GameContainer;
import com.vanillabean.engine.Renderer;
import com.vanillabean.engine.audio.SoundClip;
import com.vanillabean.engine.gfx.ImageTile;

public class Game extends AbstractGame{
	
	private ImageTile image;
	private SoundClip foodCollect;
	private SoundClip gameOver;
	private SoundClip gameTheme;
	private SoundClip introSound;
	private SoundClip wallBump;
	
    private Snake snake;
    private Board board;
    private Player player;
    private PlayerData playerData;
        
    private int boardSize;

    public final int DIRECTION_NONE = 0;
    public final int DIRECTION_UP = 1;
    public final int DIRECTION_DOWN = 2;
    public final int DIRECTION_LEFT = 3;
    public final int DIRECTION_RIGHT = 4;

    public int currentDirection;
    public boolean isGameOver;
    public int gameCount;
    public Cell startingCell;
    public boolean isGameStarted;
    public boolean readInput;
    public int startMenuOffset;
    public boolean isGameMuted;
    
    
    Scanner scanner = new Scanner(System.in);
    
    public int  boardOffset = 128/2;


    public Game(Snake initialSnake, Board board, Player player, PlayerData playerData, int boardSize){
        this.snake = initialSnake;
        this.board = board;
        this.player = player;
        this.playerData = playerData;
        this.boardSize = boardSize;
        
        this.gameCount = 0;
        this.boardOffset = (320 - (board.getCOL_COUNT()*16))/2;
        startingCell = new Cell((int) Math.floor(board.getROW_COUNT()/2), (int) Math.floor(board.getROW_COUNT()/2));
        this.isGameStarted = false;
        this.startMenuOffset = 50;
        this.isGameMuted = false;
        
        //Image and Audio Initialization
        image = new ImageTile("/tiles/Snake Game Tiles.png", 16, 16);
        foodCollect = new SoundClip("/audio/Food Collect.wav");
        foodCollect.setVolume(5);
        gameOver = new SoundClip("/audio/Game Over.wav");
        gameTheme = new SoundClip("/audio/Java Snake Game Theme.wav");
        gameTheme.setVolume(-10);
        introSound = new SoundClip("/audio/Intro Sound.wav");        
        introSound.setVolume(-5);
        wallBump = new SoundClip("/audio/Wall Bump.wav");
        introSound.setVolume(5);


    }

    public Snake GetSnake(){
        return snake;
    }

    public Board GetCurrentBoard(){
        return board;
    }
    
    public PlayerData getPlayerData() {
    	return playerData;
    }

    public Player getPlayer() {
		return player;
	}

	public boolean ReturnGameOver(){
        return isGameOver;
    }
	
	public boolean isGameStarted() {
		return isGameStarted;
	}

    public void SetIsGameOver(boolean isGameOver){
        this.isGameOver = isGameOver;
    }
    
    public int GetCurrentDirection(){
        return currentDirection;
    }

    public void setPlayerDirection(int newDirection){
        currentDirection = newDirection;
    }
    
    public void animate() {
    	
    }

    public void nextGame() {
    	wallBump.play();
    	board = new Board(boardSize, boardSize);
    	board.GenerateApple();
    	snake.resetSnake(startingCell);
    	if(player.getPlayerScore() >= player.getTopScores()[gameCount]) {
        	player.setTopScores(player.getPlayerScore(), gameCount);
    	}
    	player.resetPlayerScore();
    	gameCount++;
    }
    
    public void resetGame() {
    	isGameOver = false;
    	isGameStarted = false;
    	gameCount = 0;
    	board = new Board(boardSize, boardSize);
    	board.GenerateApple();
    	player.setPlayerLives(3);
    	snake.resetSnake(startingCell);
    	player.resetPlayerScore();
    	setPlayerDirection(DIRECTION_NONE);
    	gameTheme.stop();
    	introSound.play();
    }

    private Cell GetNextCell(Cell currentPosition){
    
        int row = currentPosition.GetRowCell();
        int col = currentPosition.GetColCell();
        
        if(currentDirection == DIRECTION_UP){col--;}
        else if(currentDirection == DIRECTION_DOWN){col++;}
        else if(currentDirection == DIRECTION_LEFT){row--;}
        else if(currentDirection == DIRECTION_RIGHT){row++;}
        
        
    
        Cell nextCell = board.GetBoard()[row][col];
        //System.out.println("Next Cell: " + nextCell.GetRowCell() + ", " + nextCell.GetColCell());
        return nextCell;
    }
    
    
    
    public static void main(String[] args) {
    	
        //NOTE: Must Change board size manually
        Board board = new Board(15, 15);
        //NOTE: Must Change board size manually
        Cell initialCell = new Cell((int) Math.floor(board.getROW_COUNT()/2), (int) Math.floor(board.getROW_COUNT()/2));
        //Cell initialCell = new Cell((int) board.getROW_COUNT()/2, (int) board.getROW_COUNT()/2);

        Snake snake = new Snake(initialCell);
        Player playerOne = new Player(" ", 3);
        PlayerData playerDat = new PlayerData();
        Game newSnakeGame = new Game(snake, board, playerOne, playerDat, 15);
        newSnakeGame.isGameOver = false;
        newSnakeGame.currentDirection = newSnakeGame.DIRECTION_NONE;
        
        newSnakeGame.introSound.play();
		board.GenerateApple();

        
        GameContainer gc = new GameContainer(newSnakeGame);
    	gc.start();
    }

	@Override
	public void update(GameContainer gc, float deltaTime) {
		
		
		if((gc.getInput().iskeyPressed(KeyEvent.VK_W) || gc.getInput().iskeyPressed(KeyEvent.VK_UP)) && currentDirection != DIRECTION_DOWN && isGameStarted) {
			//System.out.println("W is pressed");
			setPlayerDirection(DIRECTION_UP);		}
		else if((gc.getInput().iskeyPressed(KeyEvent.VK_A) || gc.getInput().iskeyPressed(KeyEvent.VK_LEFT)) && currentDirection != DIRECTION_RIGHT && isGameStarted) {
			//System.out.println("A is pressed");
			setPlayerDirection(DIRECTION_LEFT);
		}
		else if((gc.getInput().iskeyPressed(KeyEvent.VK_S) || gc.getInput().iskeyPressed(KeyEvent.VK_DOWN)) && currentDirection != DIRECTION_UP && isGameStarted) {
			//System.out.println("S is pressed");
			setPlayerDirection(DIRECTION_DOWN);
		}
		else if((gc.getInput().iskeyPressed(KeyEvent.VK_D) || gc.getInput().iskeyPressed(KeyEvent.VK_RIGHT)) && currentDirection != DIRECTION_LEFT && isGameStarted) {
			//System.out.println("D is pressed");
			setPlayerDirection(DIRECTION_RIGHT);
		}
		if(gc.getInput().iskeyPressed(KeyEvent.VK_ENTER)) {
			if(!isGameStarted) {
				gc.getTextField().closeTextField();
				introSound.stop();
				isGameStarted = true;
				//gameTheme.play();
				gameTheme.loop();
			}
		}
		
		if(gc.getInput().iskeyPressed(KeyEvent.VK_SHIFT)) {
			if(!isGameStarted && !readInput) {
				player = new Player("", 3);
				readInput = true;
				gc.getTextField().updateText();

			}else {
				readInput = false;
				gc.getTextField().closeTextField();
			}
		}
		
		if(gc.getInput().iskeyPressed(KeyEvent.VK_ESCAPE)) {
			System.exit(0);
		}
		
		if(gc.getInput().iskeyPressed(KeyEvent.VK_R)) {
			resetGame();
		}

		if(gc.getInput().iskeyPressed(KeyEvent.VK_L)) {
			if(!isGameStarted) {
				playerData.readData(player);
			}
		}
		
		if(gc.getInput().iskeyPressed(KeyEvent.VK_M)) {
			if(isGameMuted) {
				isGameMuted = false;
				gameTheme.setVolume(-10);
			}else {
				isGameMuted = true;
				gameTheme.setVolume(-75);
			}
		}
		
		//FOR DEBUGING
		//FOR DEBUGING
		
		if(!isGameOver && isGameStarted){
            if(currentDirection != DIRECTION_NONE){
                Cell nextCell = GetNextCell(snake.GetSnakeHead());
                //snake.getCollisionCell(nextCell);
                if(snake.CheckWallCollision(nextCell) || snake.checkSnakeCollision(nextCell)){
                    if(player.getPlayerLives() <=1) {
                    	if(player.getPlayerScore() >= player.getTopScores()[gameCount]) {
                        	player.setTopScores(player.getPlayerScore(), gameCount);
                    	}
                    	gameTheme.stop();
                    	gameOver.play();
                    	playerData.writeData(player.getPlayerName(), player.getTopScores(), false);
                    	isGameOver = true;
                    	
                    }
                    player.changeLivesBy(-1);
                    currentDirection = DIRECTION_NONE;
                    nextGame();

                    
                } else{
                	//System.out.println(nextCell.getTypeCell());
                	if(nextCell.getTypeCell() == CellType.FOOD){
                		player.setPlayerScore(player.getPlayerScore() + 10*player.getPlayerLives());
                		foodCollect.play();
                		//playerData.writeData(player.getPlayerName(), player.getTopScores());
                    	snake.MoveSnake(nextCell, true);
                		board.GenerateApple();

                    }
                	else {
                        snake.MoveSnake(nextCell, false);
                        
                	}
                }
            }
        }
		
		if(!isGameStarted) {
			if(readInput) {
				player.setPlayerName(gc.getTextField().getText());
				
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
		
		
		//Draw Gameplay
		if(isGameStarted) {
			for(int x = 0 ; x < (board.ROW_COUNT * 16) ; x += 16) {
				for(int y = 0; y < (board.COL_COUNT * 16) ; y+= 16) {
					
					if(board.GetBoard()[x/16][y/16].getTypeCell() == CellType.SPACE) {
						renderer.drawImageTile(image, x + boardOffset, y + boardOffset, 1, 1);
					}
					if(board.GetBoard()[x/16][y/16].getTypeCell() == CellType.FOOD) {
						renderer.drawImageTile(image, x + boardOffset, y + boardOffset, 2, 0);
					}
					if(board.GetBoard()[x/16][y/16].getTypeCell() == CellType.SNAKE_BODY){
						renderer.drawImageTile(image, x + boardOffset, y + boardOffset, 0, 0);
					}
					if(board.GetBoard()[x/16][y/16].getTypeCell() == CellType.WALL){
						renderer.drawImageTile(image, x + boardOffset, y + boardOffset, 2, 1);
					}
				}
			}
			
			
			
			
			
			//renderer.drawImageTile(image, gc.getInput().getMouseX(), gc.getInput().getMouseY(), 3, 3);
			
			//Draw Lives
			for(int i = 0 ; i < player.getPlayerLives(); i++) {
				renderer.drawImageTile(image, 136 + i*16, 0, 2, 3);
	
			}
			//Draw Lives
			renderer.drawText("Score: " + player.getPlayerScore(), 250, 0, 0xffffffff);
			renderer.drawText("Player: " + player.getPlayerName(), 0, 0, 0xffffffff);
			
			renderer.drawText("Move: WASD/ARROW KEYS", 0, 310, 0xffffffff);
			renderer.drawText("Mute Music: M", 100, 310, 0xffffffff);
			renderer.drawText("Restart Game: R", 175, 310, 0xffffffff);
			renderer.drawText("Quit Game: ESC", 250, 310, 0xffffffff);
		}
		
		//Draw Gameplay
		
		else {
			renderer.drawText("Java Snake Game", 160 - renderer.getTextWidth("Java Snake Game"), 50, 0xffffffff);
			renderer.drawText("Press L to load previous save", 160 - renderer.getTextWidth("Press L to load previous save"), 160, 0xffffffff);
			renderer.drawText("Press Shift to load new save", 160 - renderer.getTextWidth("Press Shift to load new save"), 170, 0xffffffff);
			renderer.drawText("Player Name: " + player.getPlayerName(), 160 - renderer.getTextWidth("Player Name: " + player.getPlayerName()), 190, 0xffffffff);
			renderer.drawText("High Scores: ", 160 - renderer.getTextWidth("High Scores: "), 200, 0xffffffff);
			for(int i = 0 ; i < 3 ; i++) {
				renderer.drawText(Integer.toString(player.getTopScores()[i]), 160 - renderer.getTextWidth(Integer.toString(player.getTopScores()[i])), (200 + (10*(i + 1))), 0xffffffff);
			}
			
			renderer.drawText("Score Sum: " + player.getScoreSum(), 160 - renderer.getTextWidth("Score Sum: " + player.getScoreSum()), 240, 0xffffffff);
		}

		
		if(ReturnGameOver()){
			renderer.drawText("Game Over :(", 160 - renderer.getTextWidth("Game Over :("), 160, 0xffffffff);
		}
		
	}
}