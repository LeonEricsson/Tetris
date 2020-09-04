package se.liu.ida.leoer843.tddd30.tetris;

/**
 * Help class created to be able to quickly store a position on the map
 */

public class Position {

    private int row;
    private int col;

    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){ return row;}

    public int getCol() {return col; }
}
