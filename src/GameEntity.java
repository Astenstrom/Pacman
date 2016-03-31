import javax.swing.ImageIcon;

/**
 * A Movable GameObject
 * 
 * @author Elizabeth
 *
 */
public abstract class GameEntity extends GameObject implements Movable {
    //the initial location of the GameEntity
    private Location origin;
    //the direction the entity is facing
    private int facingDirection;

    /**
     * Creates a GameEntity at the given location with the given image
     * 
     * @param location the initial location of the entity
     * @param image  the image of the entity to be drawn
     */
    public GameEntity(Location location, ImageIcon image) {
        /* Your Code Goes Here*/
    	super(location,image);
    	this.origin = location;
    }

    //move the Game enity by 1 unit in the direction it
    //is facing - remember, only move it if it results in
    //a valid location (ie, not a wall)
    @Override
    public void move(Level level) {
        /* Your Code Goes Here*/
    	Location newLoc = this.location.clone();
    	newLoc.moveByDirection(facingDirection);
    	
    	if(level.isValidLocation(newLoc)){
    		this.location = newLoc;
    	}


    }

    @Override
    public boolean collide(GameObject object) {
        /* Your Code Goes Here*/
    	return (this.location == object.getLocation());
    		
    }

    @Override
    public int getFacingDirection() {
        /* Your Code Goes Here*/
    	return this.facingDirection;
    }

    @Override
    public void setFacingDirection(int direction) {
        /* Your Code Goes Here*/
    	this.facingDirection = direction;

    }

    /**
     * Moves the GameEntity back to where it initially started
     */
    public void moveToOrigin() {
        /* Your Code Goes Here*/
    	this.location = origin;
    }

    /**
     * Turns the GameEntity. A turn can be right or left, and
     * results in a change of the Entity's facing direction. For example,
     * if the GameEntity was facing North, and it executed a right turn, it should
     * now be facing East.
     * 
     * @param right
     */
    public void turn(boolean right) {
        /* Your Code Goes Here*/
    	if(right){
    		if(this.facingDirection == NORTH){
    			this.facingDirection = EAST;
    		}
    		else if(this.facingDirection == SOUTH){
    			this.facingDirection = WEST;
    		}
			else if(this.facingDirection == EAST){
				this.facingDirection = SOUTH;			
			}
			else if(this.facingDirection == WEST){
				this.facingDirection = NORTH;
			}
    	}
    	else{
    		if(!right){
        		if(this.facingDirection == NORTH){
        			this.facingDirection = WEST;
        		}
        		else if(this.facingDirection == SOUTH){
        			this.facingDirection = EAST;
        		}
    			else if(this.facingDirection == EAST){
    				this.facingDirection = NORTH;			
    			}
    			else if(this.facingDirection == WEST){
    				this.facingDirection = SOUTH;
    			}
        	}
    	}
    	
    }

    /*
     * A helper methods for turning right - think about how you can use
     * this method to execute both right and left turns 
     */
    private void turnRight() {
        /* Your Code Goes Here*/
    	turn(true);
    }

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public Location getOrigin() {
		return origin;
	}
}
