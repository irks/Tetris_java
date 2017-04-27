package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * This class consists of 2-D JPanel array and array with colors.
 * It is responsible for displaying the game board.
 * @author irek
 */
public class GameField extends JPanel {
    
    private final JPanel[][] gameF;
    private static Color[] colors;
    
    /**
     * Create new 2-D JPanel array (20x10), initialize and add them to JPanel.
     * Initialize the color array with colors.
     */
    public GameField() {
        GridLayout layout = new GridLayout(20,10);
        setLayout(layout);
        
        gameF = new JPanel[20][10];
        initializeGF();
        addGF();

        colors = new Color[7];
        colors[0] = Color.BLUE;
        colors[1] = Color.CYAN;
        colors[2] = Color.GREEN;
        colors[3] = Color.MAGENTA;
        colors[4] = Color.ORANGE;
        colors[5] = Color.RED;
        colors[6] = Color.YELLOW;
     
    }
    
    private void initializeGF() {
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) {
                gameF[i][j] = new JPanel() {
                     
                    public Dimension getPrefferedSize() {
                        return new Dimension(45,45);
                    }
                };
                gameF[i][j].setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));   
            }
        }
    }
    
    
    private void addGF() {
        for(int i =0; i<20; i++)
            for(int j=0; j<10; j++)
                add(gameF[i][j]);
    }
    
    /**
     * Change background of JPanel at given position with default WHITE color.
     * @param x X position of Block (number of column)
     * @param y Y position of Block (number of row)
     */
    public void uncolorGFPanel(int x, int y) {
        gameF[y][x].setBackground(Color.WHITE);
    }
    
    /**
     * Color JPanel at given position with given color.
     * @param x X position of Block (number of column)
     * @param y Y position of Block (number of row)
     * @param c index of color in colors array
     */
    public void colorGFPanel(int x, int y, int c) {
        gameF[y][x].setBackground(colors[c]);
    }
    
    
    /**
     * @param index index of color in colors array
     * @return the color at given index in colors array
     */
    public static Color colors(int index) {
        return colors[index];
    }
    
    
}