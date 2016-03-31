import javax.swing.ImageIcon;

/**
 * An item in a game. All items have a specifc value
 * attributed to them. This  value is the amount that they 
 * are worth to the player when picked up.
 * 
 * @author Elizabeth
 *
 */
public class GameItem extends GameObject {
    private int value;
    
    /**
     * Creates an Item at the specified location with the specified image
     * and value.
     * 
     * @param location the location at which the item is created
     * @param image  the image to use when drawing the item
     * @param value  the value of the item
     */
    public GameItem(Location location, ImageIcon image, int value){
        /* Your Code Goes Here*/
    	super(location,image);
    	this.value = value;
    }
    
    /**
     * Returns the value of the item
     * 
     * @return  the value of the item
     */
    public int getValue(){
        /* Your Code Goes Here*/
    	return this.value;
    }
    
}
