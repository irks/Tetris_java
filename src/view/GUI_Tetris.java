package view;

import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.Figure;

/**
 * This is the primary view of this application. 
 * It has GameField and InfoPanel attributes.
 * It has JFrame attribute in place of inheriting from JFrame to unable easy messing up the view in controller.
 * @author irek
 */
public class GUI_Tetris {
        
        private static final int WIDTH_T = 460;
        private static final int HEIGHT_T = 625;
       
        private final GameField GF;
        private final InfoPanel INF;
        
        private final JFrame frame;
    /**
     * Creating new JFrame, GameField and InfoPanel.
     * Adding them in proper places of JFrame. Setting title and border layout.
     */
    public GUI_Tetris() {
            frame = new JFrame();
        
            frame.setTitle("Tetris");
            frame.setSize(WIDTH_T, HEIGHT_T);
  
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            
            GF = new GameField();
            frame.add(GF, BorderLayout.CENTER);
            
            INF = new InfoPanel();
            frame.add(INF, BorderLayout.EAST);
            
            frame.setFocusable(true);
            frame.setVisible(true);       
    }
    
    /**
     * Color JPanel at given position with given color.
     * @param x X position of Block (number of column)
     * @param y Y position of Block (number of row)
     * @param c index of color in colors array
     */
    public void colorGFPanel(int x, int y, int c) {
        GF.colorGFPanel(x, y, c);
    }
    
    /**
     * Change background of JPanel at given position with default WHITE color.
     * @param x X position of Block (number of column)
     * @param y Y position of Block (number of row)
     */
    public void unColorGFPanel(int x, int y) {
        GF.uncolorGFPanel(x, y);
    }
    
    /**
     * Refreshing the next figure panel with given figure.
     * @param figure the next figure which will be steered by player
     */
    public void refreshNextFigure(Figure figure) {
        INF.getNextFigurePanel().refreshNextFigure(figure);
    }
    
    /**
     * Refresing the score and level panel with given parameters.
     * @param score actual game score
     * @param level actual level
     */
    public void refreshScoreAndLevel(int score, int level) {
        INF.getScoreLevelPanel().refreshScoreAndLevel(score, level);
    }
    
    /**
     * Refreshing the topScore panel with given parameter.
     * @param topScore the new top score
     */
    public void refreshTopScore(int topScore) {
        INF.getScoreLevelPanel().refreshTopScore(topScore);
    }

    /**
     * Adding to JFrame a new KeyListener. It is used in controller.
     * @param keyL KeyListener 
     */
    
    public void addKeyListener(KeyListener keyL) {
        frame.addKeyListener(keyL);
    }
    
    /**
     * Showing the game over message panel. Player can choose between "New game" and "Exit game".
     * It displays also scores and level which player achieved.
     * @param score how many scores player made
     * @param level which level player achieved
     * @param option String array of possible choices
     * @return choosen option by player ( 0 - new game, 1 - exit game)
     */
    public int showGameOverMessage(int score, int level, String[] option) {
         return JOptionPane.showOptionDialog(
            frame, "Your score is: " + score
            + "\n Your level is: " + level, 
            "Game over", JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, null, option, option[0]);
    }
    
    /**
     * Refreshing the game field. At first uncoloring all places, next coloring places where blocks are.
     * @param figures ArrayList of Figures which are on the board.
     * @param isPlaceTakenMatrix  2-D boolean array which informs if the panel is taken by any block
     */
    public void displayBoard(ArrayList<Figure> figures, boolean [][] isPlaceTakenMatrix) {
        int x, y, c;
        
        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 10; j++) {
                if(!isPlaceTakenMatrix[i][j])
                    GF.uncolorGFPanel(j, i);
            }
        }
        
        for(int i = 0; i < figures.size(); i++) 
            for(int j = 0; j < figures.get(i).getNrOfBlocks(); j++) {
                x = figures.get(i).getBlockX(j);
                y = figures.get(i).getBlockY(j);
                c = figures.get(i).getBlockImg(j);
                
                GF.colorGFPanel(x, y, c);
            } 
    } 
}