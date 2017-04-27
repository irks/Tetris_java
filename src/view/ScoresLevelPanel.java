
package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class consists of JLabels which are showing actual scores, level and top score.
 * @author irek
 */
public class ScoresLevelPanel extends JPanel{

    private final JLabel textScore;
    private final JLabel actualScore;
    
    private final JLabel textLevel;
    private final JLabel actualLevel;
    
    private final JLabel textTopScore;
    private final JLabel actualTopScore;
    
    private final JPanel bigBox;
  
    /**
     * Creates a JPanel which is storing all the JLabels from this class.
     * Changing the size of font in JLabels.
     */
    public ScoresLevelPanel() {
        bigBox = new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(120,150);
            }
        };
        
        
        textScore = new JLabel("Score:");
        textScore.setFont(textScore.getFont().deriveFont(16f));
        actualScore = new JLabel("0");
        actualScore.setFont(actualScore.getFont().deriveFont(16f));
        
        textLevel = new JLabel("Level:");
        textLevel.setFont(textLevel.getFont().deriveFont(16f)); 
        actualLevel = new JLabel("0");
        actualLevel.setFont(actualLevel.getFont().deriveFont(16f));
        
        textTopScore = new JLabel("Top Score:");
        textTopScore.setFont(textTopScore.getFont().deriveFont(16f));       
        actualTopScore = new JLabel("0");
        actualTopScore.setFont(actualTopScore.getFont().deriveFont(16f));

        bigBox.setLayout(new FlowLayout()); 
        
        bigBox.add(textScore);
        bigBox.add(actualScore);
        
        bigBox.add(textLevel);
        bigBox.add(actualLevel);
        
        bigBox.add(textTopScore);
        bigBox.add(actualTopScore);
        
        add(bigBox);
    }
    
    /**
     * Refreshing top score
     * @param topScore new game top score
     */
    public void refreshTopScore(int topScore) {
        actualTopScore.setText(String.valueOf(topScore));
    }
    
    /**
     * Refreshing actual scores and level
     * @param score actual game score
     * @param level actual game level
     */
    public void refreshScoreAndLevel(int score, int level) {
        actualScore.setText(String.valueOf(score));
        actualLevel.setText(String.valueOf(level));
    } 
}