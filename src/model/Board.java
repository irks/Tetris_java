package model;

import java.util.ArrayList;

/**
 * This class represents the board model. It has an ArrayList of Figures which are on Board, 
 * 2-D boolean array (informs what places are taken) and freshFigure (the figure which is now steered by player).
 * @author irek
 */
public class Board {
    private ArrayList<Figure> figuresOnBoard;
    private static boolean[][] isPanelTaken;
    private Figure freshFigure;

    private int currentPos;
    
    /**
     * Constructor is creating new ArrayList of figures which are on Board, new 2-D boolean Array,
     * which tells if panel is already taken by any Figure and 
     * this constructor is also reseting the board.
     */
    public Board() {
        figuresOnBoard = new ArrayList<Figure>();
        isPanelTaken = new boolean[20][10];

        resetBoard();
    }
    
    /**
     * Adding the Figure to the ArrayList of figures which are on Board and changing the freshFigure to this given figure.
     * @param figure the figure which we want to add to ArrayList of figures which are on Board
     */
    public void addFigure(Figure figure) {
        freshFigure = figure;
        figuresOnBoard.add(figure);
    }
    
    public boolean[][] getPlaceTakenMatrix() {
        return isPanelTaken;
    }
    
    /**
     * @return number of figures which are now on board
     */
    public int numbersOfFigures() {
        return figuresOnBoard.size();
    }
    
    /**
     * @return ArrayList of Figures on Board
     */
    public ArrayList<Figure> getFiguresOnBoard() {
        return figuresOnBoard;
    }
    
    private boolean isRowFull(int row) {
        for(int i = 0; i < 10; ++i) {
            if(!isPanelTaken[row][i])
                return false;
        }
        return true;
    }
    
    /**
     * @param ind index of figure in ArrayList of figures on board
     * @return figure at given index in ArrayList
     */
    public Figure getFigureFromIndex(int ind) {
        return figuresOnBoard.get(ind);
    }
    
    /**
     * Method which is responsible for falling the figure which is now being steered by player.
     */
    public void freshFigureDown() {
        for(int j = 0; j < 4; j++) {
            currentPos = freshFigure.getBlockY(j);
            freshFigure.setBlockY(currentPos + 1, j);
        }
    }
    
    /**
     * Move the figure to left.
     */
    public void freshFigureLeft() {
        for(int j = 0; j < 4; j++) {
            currentPos = freshFigure.getBlockX(j);
            freshFigure.setBlockX(currentPos - 1, j);
        }
    }
    
    /**
     * Move the figure to right.
     */
    public void freshFigureRight() {
        for(int j = 0; j < 4; j++) {
            currentPos = freshFigure.getBlockX(j);
            freshFigure.setBlockX(currentPos + 1, j);
        }
    }
    
    /**
     * Checking if move with given X and Y difference is allowed.
     * @param figure this figure we want to move
     * @param diffX difference in X position of figure after wanted move
     * @param diffY difference in Y position of figure after wanted move
     * @return false if that move is not allowed, true if it is allowed
     */
    public boolean tryMove(Figure figure, int diffX, int diffY) {
        int x, y;
        for (int i = 0; i < 4; ++i) {
            x = diffX + figure.getBlockX(i);
            y = diffY + figure.getBlockY(i);

            if (x < 0 || x >= 10 || y < 0 )
                return false;
            if (isPanelTaken[y][x]) { //tetromino doszlo do klockow juz obecnych na planszy
              
                return false;  
            }   
        }
        return true;
    }
    
    /**
     * Checking if the down move is possible. If not, positions of blocks in isPanelTaken array are changed to already taken and false is returned.
     * @return false if figure cannot move down (it reached bottom of Board or reached the figure that has already fallen),
     * true if move down is possible
     */
    public boolean tryMoveDownFreshFigure() {
        int x, y;
        for(int i = 0; i < 4; ++i) {
            x = freshFigure.getBlockX(i);
            y = freshFigure.getBlockY(i) + 1 ;
            
            if( y >= 20 || isPanelTaken[y][x] ) {
                for(int j = 0; j < 4; j++) 
                    isPanelTaken[freshFigure.getBlockY(j)][freshFigure.getBlockX(j)] = true;
                return false;
            }
        }
        return true;
    }
    
    /**
     * If figure cannot be rotated false is returned, if it can be rotated it would be rotated.
     * @return true is freshFigure can rotate, false if not
     */
    public boolean freshFigureRotate() {
        return freshFigure.rotate(isPanelTaken);
    }
    
    /**
     * Removing the lines which are full and putting down blocks if needed after removing lines.
     * @return number of lines which were cleaned (max 4)
     */
    public int removeFullLines() {
        int[] cleanLines = new int[4];
        int linesToClean = 0;
        int cleanLineIndex = 0;
        
        //saving number of full rows, which should be cleaned.
        for(int i = 0; i < 20; ++i) {
            if(isRowFull(i)) {
                cleanLines[cleanLineIndex] = i;
                ++cleanLineIndex;
                ++linesToClean;   
            }
        }
        
        //removing blocks
        for(int i = 0; i < linesToClean; ++i) 
            deleteBlocksInRow(cleanLines[i]);        
        
        //removing figures which haven't any block
        for(int i = 0; i < numbersOfFigures(); ++i) {
            if(getFiguresOnBoard().get(i).getNrOfBlocks() == 0) {
                getFiguresOnBoard().remove(i);
                --i;
            }
        }
        
        putDownBlocksIfNeeded(cleanLines, linesToClean);
        
        return linesToClean;
    }
    
    private void deleteBlocksInRow(int row) {
        for(int i = 0; i < numbersOfFigures(); ++i) {
            
            for(int j = 0; j < getFiguresOnBoard().get(i).getNrOfBlocks(); ++j) {
                if( row == getFiguresOnBoard().get(i).getBlockY(j)) {
                    getFiguresOnBoard().get(i).removeBlock(j);
                    --j;
                }
            }
        }
    }
    
    private void putDownBlocksIfNeeded(int[] cleanLines, int linesToClean) {
        for(int i = 0; i < linesToClean; ++i) {
            for(int j = 0; j < numbersOfFigures(); ++j) {
                for(int k = 0; k < getFiguresOnBoard().get(j).getNrOfBlocks(); ++k) {
                    int currentYofBlock = getFiguresOnBoard().get(j).getBlockY(k);
                    
                    if(currentYofBlock < cleanLines[i]) {
                        getFiguresOnBoard().get(j).setBlockY(currentYofBlock + 1, k);
                    }
                }
            }
        }
        refreshIsPanelTakenMatrix();
    }
    
    private void refreshIsPanelTakenMatrix() {
        for(int i = 0; i < 20; ++i) 
            for(int j = 0; j < 10; ++j) 
                isPanelTaken[i][j] = false;
            
        for(int i = 0; i < numbersOfFigures(); ++i) 
            for(int j = 0; j < figuresOnBoard.get(i).getNrOfBlocks(); ++j)
                isPanelTaken[figuresOnBoard.get(i).getBlockY(j)][figuresOnBoard.get(i).getBlockX(j)] = true;
    }
    
    /**
     * Reseting board to original state.
     */
    public final void resetBoard() {
        currentPos = 0; 
        
        for(int i = 0; i < 20; i++) 
            for(int j = 0; j < 10; j++)
                isPanelTaken[i][j] = false;
        
        figuresOnBoard.clear();
    }
}