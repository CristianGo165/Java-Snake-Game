package com.javaSnakeGame;

public class Cell {

    private final int row;
    private final int col;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int GetRowCell(){
        return row;
    }

    public int GetColCell(){
        return col;
    }

    private CellType cellType;

    public CellType getTypeCell(){
        return cellType;
    }
    public void SetTypeCell(CellType cellType){
        this.cellType = cellType;
    }
}
