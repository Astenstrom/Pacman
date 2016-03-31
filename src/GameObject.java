import java.awt.Graphics;

import javax.swing.ImageIcon;

/**
 * A Drawable element of a game. Elements of a game can include
 * players, enemies, and items.
 * 
 * @author Elizabeth
 *
 */
public abstract class GameObject implements Drawable{
    //the location of the object
    protected Location location;
    //the image that will be drawn to represent the object
    private ImageIcon image;
    
    /**
     * Creates a new GameObject at the given location with the given
     * image
     * 
     * @param location the location of the object
     * @param image  the image of the object
     */
    public GameObject(Location location, ImageIcon image){
        /* Your Code Goes Here*/
        this.location = location;
        this.image = image;
    }
    
    /**
     * Returns the location of the object
     * @return the location of the object
     */
    public Location getLocation(){
        /* Your Code Goes Here*/
        return this.location;
    }
    
    
    @Override
    public void draw(Graphics g) {
        // this draws the image at its correct location by row and column - you do not
        // need to worry about how this works
        g.drawImage(image.getImage(), location.getCol()*PacmanGamePanel.IMAGE_WIDTH, 
                location.getRow()*PacmanGamePanel.IMAGE_HEIGHT, image.getImageObserver());
        
    }
}
