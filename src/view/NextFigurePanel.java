package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.*;

/**
 * It consists of 2-D JPanel array (4x4). It shows the next figure wich will be at board.
 * @author irek
 */
public class NextFigurePanel extends JPanel {
    private final JPanel[][] nextFigBoxes;
    private final JPanel bigBox;
    private Color color;
    
    /**
     * Creating new 2-D JPanel array (4x4), initializing fields and adding them to big JPanel.
     */
    public NextFigurePanel() {
        bigBox = new JPanel();
        nextFigBoxes = new JPanel[4][4];
        
        initializeNF();
        
        bigBox.setLayout(new GridLayout(4, 4));
        
        addNF();
        
        add(bigBox);
    }
    
    private void initializeNF() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j<4; j++) {
                nextFigBoxes[i][j] = new JPanel() {
                    @Override
                    public Dimension getPreferredSize() {
                        return new Dimension(25,25);
                    }
                };
                nextFigBoxes[i][j].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));         
            }
        }
    }
    
    private void addNF() {
        for(int i =0; i<4; i++)
            for(int j=0; j<4; j++)
                bigBox.add(nextFigBoxes[i][j]);
    }
    
    /**
     * Displaying the next figure which will be on board. Checking what instance of Figure is given parameter,
     * coloring proper JPanels basing on type of Figure.
     * @param figure the next figure which will be steered by player
     */
    public void refreshNextFigure(Figure figure) {
        color = GameField.colors(figure.getBlockImg(0));
        
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                nextFigBoxes[i][j].setBackground(Color.LIGHT_GRAY);
        
        if (figure instanceof Figure_I) {
            nextFigBoxes[2][0].setBackground(color);
            nextFigBoxes[2][1].setBackground(color);
            nextFigBoxes[2][2].setBackground(color);
            nextFigBoxes[2][3].setBackground(color);
        }
        if (figure instanceof Figure_J) {
            nextFigBoxes[1][2].setBackground(color);
            nextFigBoxes[2][2].setBackground(color);
            nextFigBoxes[3][2].setBackground(color);
            nextFigBoxes[3][1].setBackground(color);
        }
        if (figure instanceof Figure_L) {
            nextFigBoxes[1][1].setBackground(color);
            nextFigBoxes[2][1].setBackground(color);
            nextFigBoxes[3][1].setBackground(color);
            nextFigBoxes[3][2].setBackground(color);
        }
        if (figure instanceof Figure_O) {
            nextFigBoxes[2][1].setBackground(color);
            nextFigBoxes[2][2].setBackground(color);
            nextFigBoxes[3][1].setBackground(color);
            nextFigBoxes[3][2].setBackground(color);
        }
        if (figure instanceof Figure_S) {
            nextFigBoxes[1][1].setBackground(color);
            nextFigBoxes[2][1].setBackground(color);
            nextFigBoxes[2][2].setBackground(color);
            nextFigBoxes[3][2].setBackground(color);
        }
        if (figure instanceof Figure_T) {
            nextFigBoxes[1][2].setBackground(color);
            nextFigBoxes[2][1].setBackground(color);
            nextFigBoxes[2][2].setBackground(color);
            nextFigBoxes[3][2].setBackground(color);
        }
        if (figure instanceof Figure_Z) {
            nextFigBoxes[1][2].setBackground(color);
            nextFigBoxes[2][1].setBackground(color);
            nextFigBoxes[2][2].setBackground(color);
            nextFigBoxes[3][1].setBackground(color);
        }  
    }
}