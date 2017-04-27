package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 * It consists of all necessary info panels: new NextFigurePanel, ScoresLevelPanel and InstructionsPanel.
 * @author irek
 */
public class InfoPanel extends JPanel {
    
    private final NextFigurePanel nextFigPanel;
    private final ScoresLevelPanel scoresLevPanel;
    
    private final InstructionsPanel instructions;
    
    private GridLayout layout;
    private final Dimension panelDimension;
    
    /**
     * Creating all necessary info panels: new NextFigurePanel, ScoresLevelPanel and InstructionsPanel.
     */
    public InfoPanel() {
        nextFigPanel = new NextFigurePanel();
        scoresLevPanel = new ScoresLevelPanel();

        instructions = new InstructionsPanel();
        
        panelDimension = new Dimension(160, 200);
        
        setLayout(new GridLayout(3,1));

        add(nextFigPanel);
        add(scoresLevPanel);
        add(instructions);        
    }
    
    /**
     * @return the NextFigurePanel object which shows the next figure which will be steered by player
     */
    public NextFigurePanel getNextFigurePanel() {
        return nextFigPanel;
    }
    
    /**
     * @return the ScoresLevelPanel object which consists of actual score, level and top score
     */
    public ScoresLevelPanel getScoreLevelPanel() {
        return scoresLevPanel;
    }
    
    /**
     * @return the Dimension of info JPanel
     */
    @Override
    public Dimension getPreferredSize() {
        return this.panelDimension;
    }
}