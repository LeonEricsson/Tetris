package se.liu.ida.leoer843.tddd30.tetris;

import java.util.Map;

/**
 * Highscore class which creates individual highscores to be added to highscorelist
 */
public class Highscore {
    private String name;
    private int score;

    public Highscore(String name, int score){
        this.name = name;
        this.score = score;
    }

    public int getScore() {
	return score;
    }

    public String getName() {
	return name;
    }
}
