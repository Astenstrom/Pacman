import java.util.Random;

import javax.swing.ImageIcon;

/**
 * A Ghost in the game of Pacman.
 * 
 * @author Elizabeth
 *
 */
public class Ghost extends GameEntity {
    private Random rand;
    static ImageIcon ghostImg = new ImageIcon("ghost.png");
    /**
     * Creates a Ghost at the specified location
     * 
     * @param location the location at which the Ghost is created
     */
    public Ghost(Location location){
        /* Your Code Here */
    	
    	super(location,ghostImg);

    }
    
    /*
     * handle what happens when a ghost runs into pacman
     *
     * if pacman is invincible, the ghost should be moved back to it's
     * origin point.
     * 
     */
    @Override
    public boolean collide(GameObject object) {
    	if(this.location.getCol() == object.getLocation().getCol() && this.location.getRow() == object.getLocation().getRow()){
	    	if(object.getClass().isInstance(new Pacman(this.location))){

	    		Pacman pac = (Pacman)object;
	    		
	    		if(pac.isInvincible()){

	    			this.moveToOrigin();
	    		}
				else{
					pac.moveToOrigin();
					pac.loseLife();
				}
	    		return true;
	    	}
	    	else{
				return true;
	    	}
    	}
    	else{
    		return false;
    	}
    }

    /*
     * Ghosts move in a special way, so we need to override the move defined in
     * GameEntity.
     * 
     * When move is called, a Ghost should have an 80% chance of actually moving.
     * 
     * If it can continue going in the direction it is facing, it should move that way.
     * 
     * If not, it should pick a way to turn (right or left), and turn until it can move in 
     * the direction it is facing.
     */
    @Override
    public void move(Level level) {
    	rand = new Random();
        if(rand.nextDouble() <= .8){
        	Location newLoc = this.location.clone();
        	newLoc.moveByDirection(this.getFacingDirection());
        	if(level.isValidLocation(newLoc)){
        		this.location = newLoc;
        }
        else{
        		newLoc = this.location.clone();
        		this.turn(rand.nextBoolean());
        		newLoc.moveByDirection(this.getFacingDirection());
        		while(!level.isValidLocation(newLoc)){
        			newLoc = this.location.clone();
            		this.turn(rand.nextBoolean());
            		newLoc.moveByDirection(this.getFacingDirection());
        		}
        		
        		
        	}
        }
    	
    }

}
