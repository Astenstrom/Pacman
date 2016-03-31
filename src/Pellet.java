import javax.swing.ImageIcon;

/**
 * A pellet in the game of pacman
 * 
 * @author Elizabeth
 *
 */
public class Pellet extends GameItem {
    private final static int VALUE = 1;
    static ImageIcon pellet = new ImageIcon("pellet.png");
    /**
     * Creates a new pellet at the given location
     * 
     * @param location the location at which the pellet is created
     */
    public Pellet(Location location){
        /* Your Code Here */
    	super(location,pellet,VALUE);
    	
    }
    
    
}
