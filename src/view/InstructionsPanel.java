
package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * This class contains of instructions how to move in this tetris game.
 * @author irek
 */
public class InstructionsPanel extends JPanel {
    private final JLabel text1 = new JLabel("Move sideways \u2190 \u2192");
    private final JLabel text2 = new JLabel("Rotate \u2191");
    private final JLabel text3 = new JLabel("Speed up falling \u2193");
    private final JLabel text4 = new JLabel("Pause or resume \u0050");
   

    /**
     * Constructor only adds instructions texts to JPanel
     */
    public InstructionsPanel() {
        add(text1);
        add(text2);
        add(text3);
        add(text4);
    }
}