import javax.swing.JFrame;


public class PacmanDriver {
    public static void main(String[] args) {
        
        /*
         * Sets up the thing that actually lets you see the panel that we
         * made. Don't worry about what this does for now - we will be
         * getting into all of this after Spring break
         */
        JFrame frame = new JFrame();
        frame.getContentPane().add(new PacmanGamePanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
