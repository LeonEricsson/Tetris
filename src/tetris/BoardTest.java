package se.liu.ida.leoer843.tddd30.tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is the test class where the program is initilized and run from.
 */
public class BoardTest {
    public static final int DELAY = 250;
    //Static field usage is needed
    private static Board board;
    private static TetrisViewer viewer;



    public void newBoard(int width, int height){
        //Assignment to static field is intended and needed
        board = new Board(width, height);
        viewer = new TetrisViewer(board);
        board.addBoardListener(viewer.getPlayingField());
    }




    public static void main(String[] args) {
        final int delayReduction = 25;
        final int boardWidth = 10;
        final int boardHeight = 15;
        BoardTest ny = new BoardTest();
        HighscoreList highscoreList = new HighscoreList();
        try {
            highscoreList = highscoreList.readHighscores();
        }
        catch (FileNotFoundException y){
            //User does not need to be alerted of this issue as it is resolved outside the catch
            System.out.println("Error:" + y);
        }
        ny.newBoard(boardWidth, boardHeight);
        final HighscoreList finalHighscoreList = highscoreList;
        // This is what I've learnt to use and complexity is not an issue in this project
        final Action startBoard = new AbstractAction()
        {
            int timerCounter = 0;
            int delayCurrent = DELAY;

            public void actionPerformed(ActionEvent e) {
                Timer myTimer = (Timer) e.getSource();
                timerCounter++;
                System.out.println(delayCurrent);
                if (board.getGameOver()) {
                    if(viewer.getStart()){
                        try {
                            Thread.sleep(3000);
                        }
                        catch (InterruptedException l) {
                        	    l.printStackTrace();
                        }
                        viewer.disposeFrame();
                        ny.newBoard(10,15);
                        delayCurrent = DELAY;
                    }
                    else if(viewer.getOngoing()) {
                        viewer.disposeFrame();
                        int count = 0;
                        int maxTries = 10;
                        while(true) {
                            try{
                                viewer.gameOverScreen(board.getPoints(), finalHighscoreList);
                                break;
                            }
                            catch (FileNotFoundException x){
                                viewer.fileNotFoundError(x);
                                //I believe that the context of ++count is understandable and using an entire for loop instead
                                //would make the code harder to understand
                                if(++count == maxTries) break;
                            }
                            catch (IOException y){
                                viewer.fileAlreadyExistsError(y);
                                //Same conclusion as above
                                if(++count == maxTries) break;
                            }
                        }
                    }
                }
                else if(viewer.getPause()){
                    viewer.pauseScreen();
                }
                else {
                    board.tick();
                }

                if(timerCounter % 150 == 0 && timerCounter < 900){
                    delayCurrent -= delayReduction;
                    myTimer.setDelay(delayCurrent);
                }
            }
            };
        final Timer clockTimer = new Timer(300, startBoard);
        clockTimer.setCoalesce(true);
        clockTimer.start();
        }
    }

