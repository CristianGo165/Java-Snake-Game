public class Board {
    final int ROW_COUNT;
    final int COL_COUNT;
    private Cell[][] board;
    public Board(int ROW_COUNT, int COL_COUNT){

        this.ROW_COUNT = ROW_COUNT;
        this.COL_COUNT = COL_COUNT;

        board = new Cell[ROW_COUNT][COL_COUNT];

        for(int i = 0 ; i < ROW_COUNT ; i++){
            for(int j = 0 ; j < COL_COUNT ; j++){
                board[i][j] = new Cell(i, j);
            }
        }
    }

    public Cell[][] GetBoard(){
        return board;
    }

    public void GenerateApple(){
        while(true){
            int foodRow = (int)(Math.random() * ROW_COUNT);
            int foodCol = (int)(Math.random() * COL_COUNT);
            if(board[foodRow][foodCol].getTypeCell() != CellType.SNAKE_BODY){
                board[foodRow][foodCol].SetTypeCell(CellType.FOOD);
                break;
            }
        }
    }
}
