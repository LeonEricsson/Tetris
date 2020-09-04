package se.liu.ida.leoer843.tddd30.tetris;

import java.util.ArrayList;
import java.util.List;

/**
 * A fallthrough powerup state which allows tetris blocks to fall all the way through other blocks. This implementation
 * is done by ignoring collision with other blocks and only stopping once it reaches outside
 */

public class Fallthrough implements FallHandler {

    public boolean hasCollision(Board board){
	for (int row = 0; row < board.getFalling().getHeight(); row++) {
	    for (int col = 0; col < board.getFalling().getWidth(); col++) {
		if (!board.getFalling().checkEmpty(row, col)) {
		    int fallingBoardRow = row + board.getfallingRow();
		    int fallingBoardCol = col + board.getfallingCol();

		    if (board.getPos(fallingBoardRow, fallingBoardCol) == SquareType.OUTSIDE) {
			return true;
		    }

		}
	    }
	}
	return false;

    }



    public String getDescription(){
	return "F";
    }
}
