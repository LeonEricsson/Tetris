package se.liu.ida.leoer843.tddd30.tetris;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * The board class handles everything to do with the tetris board from creation of the board to finding out each specific block
 * on the board
 */
public class Board {
    public static final int HEAVY = 19;
    public static final int FALLTHROUGH = 10;
    private SquareType[][] squares;
    private int width;
    private int height;
    private Random rnd;
    //Field is not meant to be initialized during object construction but is still needed as a field
    private Poly falling;
    private int fallingRow;
    private int fallingCol;
    private List<BoardListener> boardListeners;
    //Needed and not a big issue
    private Boolean gameOver = false;
    private int points;
    private int spawnedBlocks;
    private FallHandler fallHandler;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.rnd = new Random();
        this.boardListeners = new ArrayList<>();
        this.points = 0;
        this.fallHandler = new DefaultFallHandler();
        this.squares = new SquareType[height + 4][width + 4];
        for (int row = 0; row < squares.length; row++) {
            for (int col = 0; col < squares[row].length; col++) {
                if (row < 2 || row > height + 1) {
                    squares[row][col] = SquareType.OUTSIDE;
                } else if (col < 2 || col > width + 1) {
                    squares[row][col] = SquareType.OUTSIDE;
                } else {
                    squares[row][col] = SquareType.EMPTY;
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Poly getFalling(){ return falling;}

    public int getfallingRow(){
        return fallingRow;
    }

    public int getfallingCol() {
        return fallingCol;
    }

    public int getPoints(){ return points; }

    public boolean getGameOver(){
        return gameOver;
    }

    public SquareType getPos(int row, int col) {
        return squares[row + 2][col + 2];
    }

    public FallHandler getFallHandler() {
        return fallHandler;
    }

    public void addBoardListener(BoardListener bl){

        boardListeners.add(bl);
    }

    private void notifyListeners(){
        for (BoardListener boardListener : boardListeners) {
            boardListener.boardChanged();
        }
    }

    public void tick() {
        if (!gameOver) {
            if (falling == null) {
                final int startingPosRow = 0;
                final int startingPosCol = (width / 2) - 1;
                spawnedBlocks++;
                if(spawnedBlocks % FALLTHROUGH == 0){
                    fallHandler = new Fallthrough();
                }
                else if(spawnedBlocks % HEAVY == 0){
                    fallHandler = new Heavy();
                }
                else {
                    fallHandler = new DefaultFallHandler();
                }

                TetrominoMaker newMaker = new TetrominoMaker();
                falling = newMaker.getPoly(rnd.nextInt(newMaker.getNumberOfTypes()));
                fallingRow = startingPosRow;
                fallingCol = startingPosCol;
                if (fallHandler.hasCollision(this)) {
                    //Autoboxing needed
                    gameOver = true;
                    //Setting to null needed
                    falling = null;
                }
            }
            else {
                fallingRow += 1;
                if (fallHandler.hasCollision(this)) {
                    fallingRow -= 1;
                    for (int row = 0; row < falling.getHeight(); row++) {
                        for (int col = 0; col < falling.getWidth(); col++) {
                            if (!falling.checkEmpty(row, col)) {
                                squares[row + fallingRow + 2][col + fallingCol + 2] = falling.getShape()[row][col];
                            }
                        }
                    }
                    //Setting to null is needed
                    falling = null;
                    int count = eraseRows();
                    addPoints(count);


                }
            }
            notifyListeners();
        }
        else{
            System.out.println("Game Over");


        }
    }

    public boolean contains(SquareType squareType, int row) {
        for (int col = 0; col < squares[0].length; col++) {
            if (row > 1 || row < height + 2) {
                if (squares[row][col] == squareType) {
                    return true;
                }
            }
        }
        return false;
    }

    public void shuffleDownRows(List<Position> collisionPos){

        for(Position pos : collisionPos){
            int col = pos.getCol();
            for(int row = pos.getRow(); row > fallingRow + 1; row--){
                squares[row + 2][col + 2] = squares[row - 1 + 2][col + 2];
            }
        }

    }


    public int eraseRows() {
        int counter = 0;
        for (int row = height + 1; row > 1; row--) {
            if (!contains(SquareType.EMPTY, row)){
                counter += 1;
                for(int row1 = row - 1; row1 > 0; row1--){
                        if(row1 == 1){
                            SquareType[] newRow = new SquareType[height+4];
                            for(int col1 = 0; col1 < width + 4; col1++) {
                                if(col1 < 2 || col1 > width + 1){
                                    newRow[col1] = SquareType.OUTSIDE;
                                } else{
                                    newRow[col1] = SquareType.EMPTY;
                                }
                            }
                            squares[row1 + 1] = newRow;
                        }
                        else{
                            squares[row1+1] = squares[row1];

                        }
                  }
                //Decleration of row inside the body of the for loop is intented
                row += 1;
             }
          }
        return counter;
      }

    public void addPoints(int count){
        switch (count) {
            case 0:
                final int zeroRowsRemoved = 0;
                points += zeroRowsRemoved;
                break;
            case 1:
                final int oneRowsRemoved = 100;
                points += oneRowsRemoved;
                break;
            case 2:
                final int twoRowsRemoved = 300;
                points += twoRowsRemoved;
                break;
            case 3:
                final int threeRowsRemoved = 500;
                points += threeRowsRemoved;
                break;
            case 4:
                final int fourRowsRemoved = 800;
                points += fourRowsRemoved;
                break;
            default:
                    throw new IllegalArgumentException("Invalid row removal");
        }
    }

    public void moveLeft(){
        if((falling != null) && (!gameOver)) {
            fallingCol -= 1;
            if(fallHandler.getDescription().equals("H")){
                fallHandler = new DefaultFallHandler();
                if (fallHandler.hasCollision(this)) { fallingCol += 1; }
                fallHandler = new Heavy();
            }
            else{
                if (fallHandler.hasCollision(this)) { fallingCol += 1; }
            }


        }
        notifyListeners();
    }

    public void moveRight() {
        if((falling != null) && (!gameOver)) {
            fallingCol += 1;
            if(fallHandler.getDescription().equals("H")){
                fallHandler = new DefaultFallHandler();
                if (fallHandler.hasCollision(this)) { fallingCol -= 1; }
                fallHandler = new Heavy();
            }
            else{
                if (fallHandler.hasCollision(this)) { fallingCol -= 1; }
            }
        }
        notifyListeners();
    }

    public void rotate() {
        if (falling != null) {
                Poly fallingStash = falling;
                falling = falling.rotateRight();
                if (fallHandler.hasCollision(this)) {
                    falling = fallingStash;
                }
        }
        notifyListeners();
    }

    public SquareType getSquareAt(int row, int col) {
        if(falling != null){
            if (row >= fallingRow && row <= (fallingRow + falling.getHeight()-1) && col >= fallingCol && col <= (fallingCol + falling.getWidth() -1)){

                if(falling.checkEmpty(row-fallingRow, col-fallingCol)){
                    return getPos(row, col);
                }
                else{
                    return falling.getShape()[row - fallingRow][col - fallingCol];
                }
            }
            else{
                return getPos(row, col);
            }
        }
        else{
            return getPos(row, col);
        }
    }

}
