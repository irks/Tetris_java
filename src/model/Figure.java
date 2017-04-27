package model;

import java.util.ArrayList;

/**
 * This class represents the figure (tetromino). It consists of 4 Blocks, blocks can be removed if lines are cleaned.
 * If all 4 Blocks was removed the Figure is removed also.
 * @author irek
 */
abstract public class Figure {

    private ArrayList<Block> blocks;

    /**
     * Constructor takes 9 parameters, coordinates for 4 Blocks and color of figure.
     * It is creating 4 new Blocks with given parameters.
     * 
     * @param firstX X position of first Block (number of column)
     * @param firstY Y position of first Block (number of row)
     * @param secondX X position of second Block (number of column)
     * @param secondY Y position of second Block (number of row)
     * @param thirdX X position of third Block (number of column)
     * @param thirdY Y position of third Block (number of row)
     * @param fourthX X position of fourth Block (number of column)
     * @param fourthY Y position of fourth Block (number of row)
     * @param textureImg index in color Array
     */
    public Figure(int firstX, int firstY, int secondX, int secondY,
                    int thirdX, int thirdY, int fourthX, int fourthY, int textureImg) {
        blocks = new ArrayList<Block>();

        blocks.add(0, new Block(firstX, firstY, textureImg));
        blocks.add(1, new Block(secondX, secondY, textureImg));
        blocks.add(2, new Block(thirdX, thirdY, textureImg));
        blocks.add(3, new Block(fourthX, fourthY, textureImg));	
    }

    /**
     * @param i index of Block in ArrayList
     * @return Block with given index in ArrayList
     */
    
    public Block getBlock(int i) {
        return blocks.get(i);
    }
    
    /**
     * @param i index of Block in ArrayList
     * @return X position of Block with given index in ArrayList
     */
    public int getBlockX(int i) {
        return blocks.get(i).getPosX();
    }
    
    /**
     * @param i index of Block in ArrayList
     * @return Y position of Block with given index in ArrayList
     */
    public int getBlockY(int i) {
        return blocks.get(i).getPosY();
    }
    
    /**
     * @param i index of Block in ArrayList
     * @return index in color array
     */
    public int getBlockImg(int i) {
        return blocks.get(i).getTextureImg();
    }
    
    /**
     * @param posX X position of Block (number of column)
     * @param nr index of Block in ArrayList
     */
    public void setBlockX(int posX, int nr) {
        blocks.get(nr).setPosX(posX);
    }
    
    /**
     * @param posY Y position of Block (number of row)
     * @param nr index of Block in ArrayList
     */
    public void setBlockY(int posY, int nr) {
        blocks.get(nr).setPosY(posY);
    }
    
    /**
     * Setting the color of block at given index.
     * @param color Index of color in color array.
     * @param nr Index of block in blocks array.
     */
    protected void setBlockImg(int color, int nr) {
        blocks.get(nr).setTextureImg(color);
    }

    /**
     * @return blocks number of this figure
     */
    public int getNrOfBlocks() {
            return this.blocks.size();
    }

    /**
     * Removing block at given index
     * @param index  index of Block in ArrayList
     */
    public void removeBlock(int index) {
            this.blocks.remove(index);
    }

    /**
     * The method, which is responsible for rotating the figures. This method is overriden by Figure_I and Figure_O.
     * Figures are rotated around the central Block (except Figures I and O). At first it is checked if rotating is possible.
     * @param isPanelTaken 2-D boolean array which informs if the panel is taken by any figure 
     * @return false if cannot rotate, true if figure was rotated
     */
    public boolean rotate(boolean [][]isPanelTaken) {
        int x = blocks.get(1).getPosX();
        int y = blocks.get(1).getPosY();
        int xT, yT;

        for (int i = 0; i < 4; ++i) {
            xT = x + y - blocks.get(i).getPosY();
            yT = y - x + blocks.get(i).getPosX();

        if (xT < 0 || xT >= 10 || yT < 0 || yT >= 20)
            return false; //the figure would move outside the board
        if (isPanelTaken[yT][xT])    
            return false;  //the figure would has collision with already fallen figure
        }

        for(int i = 0; i < 4; ++i) {
            blocks.get(i).setPosXY( x + y - getBlockY(i), y - x + getBlockX(i));
        }
        return true;
    }
}