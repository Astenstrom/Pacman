import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * PacmanGamePanel
 * 
 * @author Elizabeth
 * 
 */
public class PacmanGamePanel extends JPanel {
    // the images to use for each pacman element - you will need to use these in your
    // calls to super constructors
    public static final ImageIcon GHOST_IMAGE = new ImageIcon("ghost.png");
    public static final ImageIcon PACMAN_IMAGE = new ImageIcon("pacman.png");
    public static final ImageIcon CHERRY_IMAGE = new ImageIcon("cherry.png");
    public static final ImageIcon PELLET_IMAGE = new ImageIcon("pellet.png");

    // the width and height of all the images
    public static final int IMAGE_WIDTH = 40;
    public static final int IMAGE_HEIGHT = 40;

    private PacmanGame game;

    // a timer to update the game with and repaint the screen
    private Timer updater;

    private final int SCORE_BAR_HEIGHT = 30;

    public PacmanGamePanel() {
        // creates a new game from the specified level - if you want to write
        // your own
        // level files, simply provide a new level.txt file
        game = new PacmanGame(Level.parseLevel("level.txt"));

        // allows us to control the pacman through the keyboard
        setFocusable(true);
        addKeyListener(new PacmanControls());

        // creates our timer and starts it - Game Updater is an action listener
        // class that listens for the timer to go off, and performs its
        // actionPerformed
        // method every time the timer goes off
        updater = new Timer(500, new GameUpdater());
        updater.start();

        // sets the size of our panel
        setPreferredSize(new Dimension(game.getLevel().getNumCols()
                * IMAGE_WIDTH, game.getLevel().getNumRows() * IMAGE_HEIGHT
                + SCORE_BAR_HEIGHT));

    }

    // this method is what actually does all of the drawing - you can think of
    // the Graphics object as a kind of canvas that we are painting things onto.
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        //amount by which we want to offset the score when drawing
        int scoreOffset = 20;
        
        // if we are still playing our game, we want to draw all of the game
        // components
        if (game.isPlaying()) {
            
            //local variables to keep track of the number of rows and columns
            Level level = game.getLevel();
            int numRows = level.getNumRows();
            int numCols = level.getNumCols();

            
            
            // draw the walls
            g.setColor(Color.black);
            for (int row = 0; row < numRows; row++) {
                for (int col = 0; col < numCols; col++) {
                    //find the walls by checking to see if the location at
                    //the row and column is a valid one
                    if (!level.isValidLocation(new Location(row, col))) {
                        // draws a rectangle at the specified row and column
                        g.fillRect(col * IMAGE_HEIGHT, row * IMAGE_WIDTH,
                                IMAGE_HEIGHT, IMAGE_WIDTH);
                    }
                }
            }
            
            //draw the scorebar
            g.setColor(Color.lightGray);
            g.fillRect(0, numRows * IMAGE_HEIGHT, getWidth(), SCORE_BAR_HEIGHT);
            g.setColor(Color.black);
            //offset to draw the score label in roughly the center of the screen
            g.drawString(game.toString(), getWidth() / 2
                    - game.toString().length(), numRows * IMAGE_HEIGHT
                    + scoreOffset);
            
            //passes the graphics object to the game which will handle actually drawing
            //all of the game elements
            game.drawEverything(g);
            
        } else {
            //if we are not still playing, we want to tell the player whether
            //they won or lost
            String message = "";
            if (game.itemsLeft()) {
                message = "You Lost";
            } else {
                message = "You Won!";
            }
            g.setColor(Color.black);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.white);
            g.drawString(message, getWidth() / 2 - message.length(), getHeight() / 2);
            g.drawString(game.toString(), getWidth() / 2 - scoreOffset,
                    getHeight() / 2 + scoreOffset);

        }
    }

    /*
     * This is a class that allows the pacman to be controlled
     * by the user through the arrow keys. We attached an instance 
     * of this class to the Panel so that the Panel is listening
     * for any keys pressed by the user. Through the use of 
     * key codes, we can find out what key was pressed and move the
     * pacman accordingly.
     */
    private class PacmanControls implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP :
                    game.movePacman(Movable.NORTH);
                    break;
                case KeyEvent.VK_DOWN :
                    game.movePacman(Movable.SOUTH);
                    break;
                case KeyEvent.VK_RIGHT :
                    game.movePacman(Movable.EAST);
                    break;
                case KeyEvent.VK_LEFT :
                    game.movePacman(Movable.WEST);
                    break;
            }

            game.checkCollisions();

            repaint();

        }

        //method in KeyListener that we do not want to use, 
        //so we just override it with an empty method body
        @Override
        public void keyReleased(KeyEvent e) {
        }

        //method in KeyListener that we do not want to use, 
        //so we just override it with an empty method body
        @Override
        public void keyTyped(KeyEvent e) {
        }

    }

    /* 
     * this is the actionlistener that we have tied to our timer.
     * every time the timer goes off, the action performed
     * method is called to update the game and repaint the panel
     */
    private class GameUpdater implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //update the state of the game
            game.update();
            //redraw everything
            repaint();
        }
    }

}
