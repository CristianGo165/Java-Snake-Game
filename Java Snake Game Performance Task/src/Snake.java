import java.util.LinkedList;

public class Snake {
    public LinkedList<Cell> snakeCells = new LinkedList<>();

    public Cell head;

    Snake(Cell initialPos){
        snakeCells.add(initialPos);
        head = initialPos;
        head.SetTypeCell(CellType.SNAKE_BODY);
    }

    public void GrowSnake(){
        snakeCells.add(head);
    }

    public void MoveSnake(Cell moveCell){
        //Update Tail Cell
        Cell tail = snakeCells.removeLast();
        tail.SetTypeCell(CellType.SPACE);

        //Head Update
        head = moveCell;
        head.SetTypeCell(CellType.SNAKE_BODY);
        //Update snake body Linked List
        snakeCells.add(head);
    }

    public boolean CheckWallCollision(Cell moveCell){
        return moveCell.getTypeCell() == CellType.WALL;
    }

    public LinkedList<Cell> GetSnakeBody(){
        return snakeCells;
    }

    
}
