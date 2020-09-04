package se.liu.ida.leoer843.tddd30.tetris;

/**
 * To make multiple behaviours of collision possible with for example power up states we implement a Fall Handler
 * interface which allows different classes hold their own unique collision detection methods
 */

public interface FallHandler {

    public boolean hasCollision(Board board);

    public String getDescription();

}
