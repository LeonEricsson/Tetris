package se.liu.ida.leoer843.tddd30.tetris;

/**
 * The default behaviour of the collision handling is implemented in this class which in term implements
 * the fall handler
 */

public class DefaultFallHandler implements FallHandler{

    public boolean hasCollision(Board board){
	for (int row = 0; row < board.getFalling().getHeight(); row++) {
	            for (int col = 0; col < board.getFalling().getWidth(); col++) {
	                if (!board.getFalling().checkEmpty(row, col)) {
	                    int fallingBoardRow = row + board.getfallingRow();
	                    int fallingBoardCol = col + board.getfallingCol();

	                    if (board.getPos(fallingBoardRow, fallingBoardCol) != SquareType.EMPTY) {
	                        return true;
	                    }
	                }
	            }
	        }
	return false;
    }

    public String getDescription(){

        return "D";

    }

}
