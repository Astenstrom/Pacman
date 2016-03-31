import java.awt.Graphics;

/**
 * An object that is Drawable is an object that can be drawn on a Graphics
 * object. The draw method defines how that object draws itself
 * 
 * @author Elizabeth
 * 
 */
public interface Drawable {
    
    /**
     * Draws the object onto the specified Graphics object
     * 
     * @param g the graphics object on which to draw
     */
    public abstract void draw(Graphics g);
}
