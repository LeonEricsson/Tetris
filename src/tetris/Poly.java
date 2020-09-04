package se.liu.ida.leoer843.tddd30.tetris;

/**
 * Poly class handles the two dimensional array shapes of each block.
 */
public class Poly
{
    private SquareType[][] shape;

    public Poly(SquareType[][] shape) {
        this.shape = shape;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {
        return shape[0].length;
    }

    public boolean checkEmpty(int x, int y) {
        SquareType square = shape[x][y];
        return square == SquareType.EMPTY;
    }

    public SquareType[][] getShape() {
        return shape;
    }

    public Poly rotateRight() {
        int size = this.getHeight();
        Poly fallingCopy = new Poly(new SquareType[size][size]);

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                fallingCopy.shape[col][size - 1 - row] = this.shape[row][col];
            }
        }

        return fallingCopy;
    }
}
