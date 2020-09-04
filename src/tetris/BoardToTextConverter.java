package se.liu.ida.leoer843.tddd30.tetris;

/**
 * This class is not used anymore but was used to convert the board to a string printed out in terminal, warnings are ignored
 */
public class BoardToTextConverter {

    public String convertToText(Board board){
        StringBuilder textBoard = new StringBuilder();
        for (int i = 0; i<board.getHeight() ; i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                SquareType type = board.getSquareAt(i, j);
                switch (type) {
                    case OUTSIDE:
                        textBoard.append("0");
                    case EMPTY:
                        textBoard.append("-");
                        break;
                    case I:
                        textBoard.append("|");
                        break;
                    case L:
                        textBoard.append("[");
                        break;
                    case J:
                        textBoard.append("/");
                        break;
                    case O:
                        textBoard.append("@");
                        break;
                    case S:
                        textBoard.append("$");
                        break;
                    case T:
                        textBoard.append("?");
                        break;
                    case Z:
                        textBoard.append("£");
                        break;
                }
                }
            textBoard.append("\n");
        }
        String result = textBoard.toString();
        return result;
        }





}
