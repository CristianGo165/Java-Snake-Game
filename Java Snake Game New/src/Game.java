public class Game {
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
    
        if(currentDirection == DIRECTION_UP){col++;}
        else if(currentDirection == DIRECTION_DOWN){col--;}
        else if(currentDirection == DIRECTION_LEFT){row--;}
        else if(currentDirection == DIRECTION_RIGHT){row++;}
        
    
        Cell nextCell = new Cell(row, col);
        return nextCell;
    }
    
    //TEMP UPDATE FUNCTION
    public void update(){
        if(!isGameOver){
            if(currentDirection != DIRECTION_NONE){
                Cell nextCell = GetNextCell(snake.GetSnakeHead());
                if(nextCell.getTypeCell() == CellType.WALL || nextCell.getTypeCell() == CellType.TRAP){
                    currentDirection = DIRECTION_NONE;
                    isGameOver = false;
                } else{
                    snake.MoveSnake(nextCell);
                    if(nextCell.getTypeCell() == CellType.FOOD){
                        snake.GrowSnake();
                        board.GenerateApple();   
                    }
                }
            }
        }
    }


    //TEMP UPDATE FUNCTION
}