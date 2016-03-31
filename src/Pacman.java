import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 * Pacman in a game of Pacman.
 * 
 * @author Elizabeth
 *
 */
public class Pacman extends GameEntity implements Movable{
    //whether or not pacman is invincible
    private boolean invincible;
    //pacman's score
    private int score;
    //the number of lives pacman has left
    private int lives;
    
    static ImageIcon pacmanImg = new ImageIcon("pacman.png");
    
    /**
     * Creates a new Pacman with 3 lives at the given location. 
     * 
     * @param location the location at which the pacman is created
     */
    public Pacman(Location location) {
        /* Your Code Here */
    	super(location,pacmanImg);
    	this.invincible = false;
    	this.score = 0;
    	this.lives = 3;
    }
    
    /*
     *  If pacman collides with another pacman, simply return 
     *  that pacman hit something
     *  
     *  If with an item, pick up that item
     * 
     *  If with a ghost and invincible, return that pacman hit something 
     *  
     *  If with a ghost and not invincible, be eaten, lose a life, start back at origin point
     * 
     */
    @Override
    public boolean collide(GameObject object){
        /* Your Code Here */
    	if(this.location.getCol() == object.getLocation().getCol() && this.location.getRow() == object.getLocation().getRow()){
	    	if(object.getClass().isInstance(new Ghost(this.location))){
	    		if(this.isInvincible()){
	    			return true;
	    		}
	    		else{
	    			this.loseLife();
	    			this.moveToOrigin();
	    			return true;
	    		}
	    		
	    	}
	    	else if(object.getClass().isInstance(new Pellet(this.location))){
	    		pickUpItem((GameItem)object);
	    		return true;
	    	}
	    	else if(object.getClass().isInstance(new Cherry(this.location))){
	    		this.invincible = true;
	    		pickUpItem((GameItem)object);
	    		return true;
	    	}
	    	else
	    		return true;
    	}
    	else{
    		return false;
    	}
    	
    } 
    
    /*
     * Helper method that allows pacman to pick up the item that
     * it collided with.
     * 
     * When an item is picked up, the score should be increased and
     * invincibility handled correctly if the item is a cherry
     */
    private void pickUpItem(GameItem item){
        /* Your Code Here */
    	this.score++;
    	if(item.getClass().isInstance(new Cherry(this.location))){
    		this.invincible = true;
    		
    	}
    	
    }
    
    /**
     * Causes pacman to no longer be invincible
     */
    public void loseInvincibility(){
        /* Your Code Here */
    	this.invincible = false;
    }
    
    /**
     * Returns whether or not pacman is invincible
     * 
     * @return the state of pacman's invincibility 
     */
    public boolean isInvincible(){
        /* Your Code Here */
    	return this.invincible;
    }
    
    /**
     * Causes pacman to lose a life and
     * move back to its origin point
     */
    public void loseLife(){
        /* Your Code Here */
    	this.lives--;
    }
    
    /**
     * Returns the number of lives that pacman has left
     * @return the number of lives that pacman has left
     */
    public int getLives(){
        /* Your Code Here */
    	return this.lives;
    }
    
    /**
     * Returns pacman's score
     * @return pacman's score
     */
    public int getScore(){
        /* Your Code Here */
    	return this.score;
    }

    // handles the rotation of the image depending on what direction pacman is facing
    // do not worry about how this code works :]
    @Override
    public void draw(Graphics g) {

        g.translate(location.getCol() * PacmanGamePanel.IMAGE_WIDTH
                + PacmanGamePanel.IMAGE_WIDTH / 2, location.getRow()
                * PacmanGamePanel.IMAGE_HEIGHT + PacmanGamePanel.IMAGE_HEIGHT
                / 2);

        switch (getFacingDirection()) {
            case Movable.NORTH :
                ((Graphics2D) g).rotate(-Math.PI / 2);
                break;
            case Movable.SOUTH :
                ((Graphics2D) g).rotate(Math.PI / 2);
                break;
            case Movable.WEST :
                ((Graphics2D) g).rotate(Math.PI);
                break;

        }

        g.translate(-location.getCol()*PacmanGamePanel.IMAGE_WIDTH - PacmanGamePanel.IMAGE_WIDTH / 2,
                -location.getRow()* PacmanGamePanel.IMAGE_HEIGHT - PacmanGamePanel.IMAGE_HEIGHT / 2);
        super.draw(g);
    }
    

    
}
