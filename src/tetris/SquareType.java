package se.liu.ida.leoer843.tddd30.tetris;

/**
 * Enum class which holds the different type of blocks that can be used in the tetris program
 */
public enum SquareType {
    //Length of squaretypes is not seen as an issue here as it is the best way to represent each individual block.

    /**
     * EMPTY block on the tetris screen
     */
    EMPTY,
    /**
     * I shaped block on the tetris screen
     */
    I,
    /**
     * L shaped block on the tetris screen
     */
    O,
    /**
     * Reversed L shape block on the tetris screen
     */
    T,
    /**
     * Square shaped block on the tetris screen
     */
    S,
    /**
     * Z shaped block on the tetris screen
     */
    Z,
    /**
     * T shaped block on the tetris screen
     */
    J,
    /**
     * Reversed Z shape block on the tetris screen
     */
    L,
    /**
     * Block for outside of the map, used to create a frame
     */
    OUTSIDE
}
