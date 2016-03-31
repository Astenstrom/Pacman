/**
 * A Movable object is an object that is capable of facing a particular 
 * direction (North, South, East, or West), and moving in some some manner
 * within a given level.
 * 
 * Objects that move can also collide with other objects in the game.
 * 
 * @author Elizabeth
 * 
 */
public interface Movable {

    /**
     * The int value that represents North
     */
    public static final int NORTH = 0;
    /**
     * The int value that represents South
     */
    public static final int SOUTH = 1;
    /**
     * The int value that represents East
     */
    public static final int EAST = 2;
    /**
     * The int value that represents West
     */
    public static final int WEST = 3;
   

    /**
     * Moves the object. Typically a move will be in the direction that
     * the object is facing. The level is provided to check the validity 
     * of the intended move. If the move is not valid, the moveable should not be moved.
     * 
     * @param level the level on which the movable resides
     */
    public abstract void move(Level level);
    
    /**
     * Gets whether or not the movable object hit the specified object
     * and performs any necessary actions
     *  
     * @param object the object that may have been hit
     * @return whether or not it was hit
     */
    public abstract boolean collide(GameObject object);

    /**
     * Get the direction the movable object is facing
     * 
     * @return the direction the object is facing
     */
    public abstract int getFacingDirection();
    
    /**
     * Set the direction the movable object is facing
     * 
     * @param direction the direction the object should face
     */
    public abstract void setFacingDirection(int direction);

}
