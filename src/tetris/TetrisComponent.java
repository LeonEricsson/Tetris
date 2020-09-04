package se.liu.ida.leoer843.tddd30.tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;


/**
 * This class handles the drawing and movement of the tetris blocks
 */
public class TetrisComponent extends JComponent implements BoardListener {
    //Not sure why this is an issue, variable name seems appropriate
    private final static int tetrisBlockWidth = 32;
    private final static int tetrisBlockHeight = 32;
    private Board board;
    private EnumMap<SquareType, Color> squaretypeColor;

    //Assignment doesn't need to be checked and dont see the problem with EnumMap class
    public TetrisComponent(Board board, EnumMap squaretypeColor) {
        this.board = board;
        this.squaretypeColor = squaretypeColor;


    }

    public void boardChanged(){
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension boardFrame = new Dimension(board.getWidth() *tetrisBlockWidth, board.getHeight()*tetrisBlockHeight);
        return boardFrame;
    }

    public void moveRight(){
        board.moveRight();
    }

    public void moveLeft(){
        board.moveLeft();
    }

    public void rotate() {board.rotate();}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                SquareType square = board.getSquareAt(i, j);
                g2d.setColor(squaretypeColor.get(square));
                g2d.drawRect(j*tetrisBlockHeight, i*tetrisBlockWidth, tetrisBlockWidth, tetrisBlockHeight);
                g2d.fillRect(j*tetrisBlockHeight, i*tetrisBlockWidth, tetrisBlockWidth, tetrisBlockHeight);


            }
        }

        g2d.setColor(Color.BLACK);
        //Magic numbers in this case are ok as coordinates and size are easier to read as integers rather than text
        g2d.setFont(new Font("font", Font.PLAIN, 13));
        g2d.drawString("Points: " + board.getPoints(), 0, 15);
        g2d.drawString("Powerup: " + board.getFallHandler().getDescription(), 0, 28);
    }
}