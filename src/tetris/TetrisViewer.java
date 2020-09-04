package se.liu.ida.leoer843.tddd30.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;

import static se.liu.ida.leoer843.tddd30.lab1.Exercise8.askUser;

/**
 * This class handled everything that the user sees, JFrames etc aswell as the users keybindings.
 */
public class TetrisViewer
{
    /**
     * Playingfield is the game frame
     */
    public TetrisComponent playingField;
    private JFrame frame;
    //Raw use not an issue
    private EnumMap squaretypeColor;
    private boolean ongoing;
    private boolean start;
    private boolean pause;

    public TetrisViewer(Board board) {
	final int tetrisBlockWidth = 32;
	final int tetrisBlockHeight = 32;
        ongoing = true;
        start = false;
        pause = false;
	this.squaretypeColor = new EnumMap<SquareType, Color>(SquareType.class);
	squaretypeColors();
	this.playingField = new TetrisComponent(board, squaretypeColor);
	board.addBoardListener(playingField);

	this.frame = new JFrame("Start screen");
	tetrisStartScreen startScreen = new tetrisStartScreen();
	frame.add(startScreen);
	frame.setPreferredSize(new Dimension(board.getWidth() *tetrisBlockWidth, board.getHeight()*tetrisBlockHeight));
	frame.pack();
	frame.setVisible(true);
	try {
	    Thread.sleep(3000);
	}
	catch (InterruptedException e) {
	    e.printStackTrace();
	}
	frame.dispose();


	this.frame = new JFrame("Playing Field");
	keyBindings();
	menuMaker();
	frame.setLayout(new BorderLayout());
	frame.add(playingField, BorderLayout.CENTER);
	frame.pack();
	frame.setVisible(true);

    }

    public TetrisComponent getPlayingField(){
        return playingField;
}

    public boolean getOngoing(){
        return ongoing;
}
    public boolean getStart(){
        return start;
    }
    public boolean getPause() {return pause;}

    public void disposeFrame(){
        frame.dispose();
    }

    public void gameOverScreen(int points, HighscoreList highscoreList) throws FileNotFoundException, IOException {
        ongoing = false;
        start = true;
        String name = JOptionPane.showInputDialog("Enter username:");
        Highscore newHighscore = new Highscore(name, points);
        highscoreList.addHighscore(newHighscore);
        highscoreList.sortValues();
        highscoreList.saveHighscores(highscoreList);
        this.frame = new JFrame("Highscores");
        JTable highscoreTable = new JTable(highscoreList.showHighscores().size(), 2);
        int row=0;
        // Could not be safely removed
        for(Map.Entry<String,Integer> entry: highscoreList.showHighscores().entrySet()){
            highscoreTable.setValueAt(entry.getKey(),row,0);
            highscoreTable.setValueAt(entry.getValue(),row,1);
            row++;
        }
       	highscoreTable.addKeyListener(new startGameAction());
        frame.add(highscoreTable);
        frame.pack();
        frame.setVisible(true);

    }

    public void pauseScreen(){
        if (askUser("Un-pause?")) {
            pause = !pause;
	}
    }

    public void fileNotFoundError(FileNotFoundException x){
	JOptionPane.showMessageDialog(frame,
		"Failed to create highscores file \n" +
		"Try again?",
		"File Error",
		JOptionPane.ERROR_MESSAGE);

    }

    public void fileAlreadyExistsError(IOException y){
	JOptionPane.showMessageDialog(frame,
			"Failed to create highscores file \n" +
			"Try again?",
			"File Error",
			JOptionPane.ERROR_MESSAGE);
    }

    private void squaretypeColors(){
        //Not an issue that this is unchecked assignment we know it works
	squaretypeColor.put(SquareType.EMPTY, Color.WHITE);
	squaretypeColor.put(SquareType.I, Color.RED);
	squaretypeColor.put(SquareType.J, Color.BLUE);
	squaretypeColor.put(SquareType.L, Color.GRAY);
	squaretypeColor.put(SquareType.O, Color.YELLOW);
	squaretypeColor.put(SquareType.S, Color.GREEN);
	squaretypeColor.put(SquareType.T, Color.CYAN);
	squaretypeColor.put(SquareType.Z, Color.MAGENTA);
    }

    private void keyBindings(){
	JComponent pane = frame.getRootPane();
	final InputMap in = pane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	in.put(KeyStroke.getKeyStroke("LEFT"), "left");
	in.put(KeyStroke.getKeyStroke("RIGHT"), "right");
	in.put(KeyStroke.getKeyStroke("UP"), "rotate");
	in.put(KeyStroke.getKeyStroke("P"), "pause");

	final ActionMap act = pane.getActionMap();
	act.put("left", new leftAction());
	act.put("right", new rightAction());
	act.put("rotate", new rotate());
	act.put("pause", new pause());

    }

    private void menuMaker(){
        final JMenuBar barMenu = new JMenuBar();
        final JMenu menu = new JMenu("Menu");
        final JMenuItem quit = new JMenuItem("Avsluta", 'Q');

        menu.add(quit);
        barMenu.add(menu);
        quit.addActionListener(new quitAction());
        frame.setJMenuBar(barMenu);

    }

    //Appropriate class name
    public class tetrisStartScreen extends JComponent {
    	final private ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("tetris.jpg"));

    	@Override public void paintComponent(final Graphics g) {
    	    final Graphics2D g2d = (Graphics2D) g;
    	    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	    final AffineTransform old = g2d.getTransform();
    	    final AffineTransform at = AffineTransform.getScaleInstance(0.5, 0.5);
    	    g2d.setTransform(at);
    	    // Magic numbers here is okay as coordinates are better read as integers
    	    icon.paintIcon(this, g, 50, 50);

    	    g2d.setTransform(old);
    	}
        }

    //Appropriate class name
    private class startGameAction extends KeyAdapter
    {
	@Override public void keyPressed(KeyEvent e) {
	    char ch = e.getKeyChar();
	    if (ch == 'r') {
		start = true;
		frame.dispose();
	    }
	}
    }

    //Appropriate class name
    private class quitAction implements	ActionListener {
	@Override public void actionPerformed(ActionEvent e) {
	    if (askUser("Avsluta?")) {
		System.exit(0);
	    }
	}
    }


    //Appropriate class name
    private class rightAction extends AbstractAction
    {
	@Override public void actionPerformed(ActionEvent e) {playingField.moveRight();}
    }

    //Appropriate class name
    private class leftAction extends AbstractAction
    {
	@Override public void actionPerformed(ActionEvent e) {playingField.moveLeft();}
    }

    //Appropriate class name
    private class rotate extends AbstractAction
    {
     	@Override public void actionPerformed(ActionEvent e) {playingField.rotate();}
    }

    //Appropriate class name
    private class pause extends AbstractAction
    {
	@Override public void actionPerformed(ActionEvent e) {
	    pause = !pause;
	}
    }
}
