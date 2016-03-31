import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

/**
 * This class defines the logic for a game of pacman.
 * 
 * It contains all the objects needed for a game, and handles
 * all of the logic that actually runs the game.
 * 
 * @author Elizabeth
 *
 */
public class PacmanGame {
    //array of ghosts
    private Ghost[] ghosts;
    //pacman!
    private Pacman pacman;
    //the level for the game
    private Level level;
    //whether or not the game is still playing
    private boolean playing;
    
    //an arraylist is useful here because the number of items
    //we have is constantly changing
    private ArrayList<GameItem> items;
    //a timer to control pacmans invincibility
    private Timer invincibilityTimer;

    /**
     * Creates a new game of pacman based off of the 2D char
     * array that represents the level
     * 
     * @param levelTiles the representation of the level
     */
    public PacmanGame(char[][] levelTiles) {
        /* 
         * Your Code Here
         * 
         * Create a level based off of the tiles that were passed in
         * and then instantiate all of the objects at the correct locations
         * according to the level - see the Level documentation for the methods
         * you can use here
         * 
         */
    	items = new ArrayList<GameItem>();
    	Level lvl = new Level(levelTiles);
    	Location[] cherries = lvl.getCherryLocations();
    	Location[] pellets = lvl.getPelletLocations();
    	Location ghost = lvl.getGhostOrigin();
    	Location pacman = lvl.getPacmanOrigin();
    	
    	ghost = lvl.wrapLocation(ghost);
    	pacman = lvl.wrapLocation(pacman);
    	this.pacman = new Pacman(pacman);
    	Ghost[] ga = new Ghost[4];
    	for(int i = 0; i<ga.length;i++){
    		ga[i] = new Ghost(ghost);
    	}
    	this.ghosts = ga;
    	for(Location l:cherries){
    		this.items.add(new Cherry(l));
    	}
    	for(Location l:pellets){
    		this.items.add(new Pellet(l));
    	}
    	
        //makes a timer that controls the amount of time that pacman
        //is invincible for
        invincibilityTimer = new Timer(6000, new InvincibilityChanger());
        invincibilityTimer.setRepeats(false);
        this.playing = true;

        this.level = lvl;
    }

    /**
     * Move pacman in the indicated direction
     * 
     * @param direction the direction to move pacman
     */
    public void movePacman(int direction) {
        /* Your Code Here */
    	pacman.setFacingDirection(direction);
    	pacman.move(getLevel());
    }

    /*
     * helper method that checks all of the items to see if pacman collided
     * with them - if it did, this removes the items from play.
     */
    private void pickUpItems() {
        ArrayList<GameItem> removedItems = new ArrayList<GameItem>();
        for (GameItem item : items) {
            if (pacman.collide(item)) {
                removedItems.add(item);
                if (item instanceof Cherry) {
                    invincibilityTimer.start();
                }

            }
        }

        items.removeAll(removedItems);

    }

    /**
     * Moves the ghosts
     */
    private void moveGhosts() {
        /* Your Code Here */
    	for(Ghost ghost :this.ghosts){
    		ghost.move(getLevel());
    	}
    }
    
    /**
     * Gets the level associated with the game
     * @return
     */
    public Level getLevel() {
        /* Your Code Here */
    	return this.level;
    }
    
    /**
     * Checks to see if any collisions occured between
     * pacman and the ghosts and between pacman and the items.
     */
    public void checkCollisions() {
        /* Your Code Here */
    	for(int i = 0; i<ghosts.length; i++){
    		ghosts[i].collide(pacman);
    	}
		pickUpItems();
    }

    /**
     * Returns whether or not the game is still being played
     * @return the state of gameplay
     */
    public boolean isPlaying() {
        /* Your Code Here */
    	return this.playing;
    }

    /**
     * Returns whether or not there are any items left in the game 
     * @return whether or not items are left
     */
    public boolean itemsLeft() {
        /* Your Code Here */
    	return !(this.items.isEmpty());
    }
    
    /**
     * Updates the game
     * 
     * an update includes moving the ghosts, checking collisions,
     * and stopping game play if pacman runs out of lives or items to 
     * pick up
     */
    public void update(){
        /* Your Code Here */
    	for(Ghost g:ghosts){
    		g.move(getLevel());
    		checkCollisions();

    		if(!invincibilityTimer.isRunning()){
    			pacman.loseInvincibility();
    		}
    		if(pacman.getLives() == 0){
    			this.playing = false;
    		}
    		if(!itemsLeft()){
    			this.playing = false;
    		}
    	}
    }

    /**
     * Draws all of the game elements
     * @param g the graphics object on which the elements are drawn
     */
    public void drawEverything(Graphics g) {
        //changes the color when pacman is invincible
        if (pacman.isInvincible()) {
            g.setColor(new Color(255, 255, 0, 50));
            g.fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        }
        for (GameItem item : items) {
            item.draw(g);
        }
        for (Ghost ghost : ghosts) {
            ghost.draw(g);
        }

        pacman.draw(g);
    }

    @Override
    public String toString(){
    	return "Lives: " + this.pacman.getLives() + " Score: " + this.pacman.getScore();
    }
    
    //controls pacman's invincibility - again, do not worry about how this works
    private class InvincibilityChanger implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            pacman.loseInvincibility();
        }

    }
    


}
