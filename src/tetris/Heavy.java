package se.liu.ida.leoer843.tddd30.tetris;

import java.util.ArrayList;
import java.util.List;

/**
 * Heavy is a powerup that allows certain blocks to press down on already in place blocks if there is a
 * space under them.
 */

public class Heavy implements FallHandler {

    public boolean hasCollision(Board board) {
	List<Position> collisionPos = new ArrayList<>();
	for (int row = 0; row < board.getFalling().getHeight(); row++) {
	    for (int col = 0; col < board.getFalling().getWidth(); col++) {
		if (!board.getFalling().checkEmpty(row, col)) {
		    int fallingBoardRow = row + board.getfallingRow();
		    int fallingBoardCol = col + board.getfallingCol();


		    if (board.getPos(fallingBoardRow, fallingBoardCol) == SquareType.OUTSIDE) {
			return true;
		    } else if (board.getPos(fallingBoardRow, fallingBoardCol) != SquareType.EMPTY) {
			boolean emptySpace = false;
		        for(int rowCheck = fallingBoardRow + 1; rowCheck < board.getHeight(); rowCheck++){
			    if (board.getPos(rowCheck, fallingBoardCol) == SquareType.EMPTY) {
			        fallingBoardRow = rowCheck;
				emptySpace = true;
				break;
			    }
			}
		        if (emptySpace) {
			    Position pos = new Position(fallingBoardRow, fallingBoardCol);
			    collisionPos.add(pos);
			    System.out.println("Row: " + pos.getRow() + " Col: " + pos.getCol());
			}
			else {
			    return true;
			}
		    }
		}
	    }
	}
	if(!collisionPos.isEmpty()){
	    board.shuffleDownRows(collisionPos);
	}
	return false;
    }


	public String getDescription(){
	    return "H";
	}
}
