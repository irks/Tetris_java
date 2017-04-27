package model;

/**
 * Block class has X and Y coordinates attributes and number of color from color array (in view).
 * Four Blocks (positioned in different ways) are creating diffrent types of Figures (tetrominos).
 * @author irek
 */
public class Block {
    private int posX;
    private int posY;
    private int textureImg;

    /**
     * Only Assignment of attributes
     * @param posX X position of Block (number of column)
     * @param posY Y position of Block (number of row)
     * @param textureImg index in color array
     */
    public Block(int posX, int posY, int textureImg) {
        this.posX = posX;
        this.posY = posY;
        this.textureImg = textureImg;
    }

    /**
     *
     * @return X position of Block (number of column)
     */
    public int getPosX() {
        return posX;
    }

    /**
     *
     * @return Y position of Block (number of row)
     */
    public int getPosY() {
        return posY;
    }

    /**
     *
     * @param posX X position of Block (number of column)
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     *
     * @param posY Y position of Block (number of row)
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     *
     * @return index in color array
     */
    public int getTextureImg() {
        return textureImg;
    }

    /**
     *
     * @param textureImg index in color array
     */
    public void setTextureImg(int textureImg) {
        this.textureImg = textureImg;
    }

    /**
     *
     * @param posX X position of Block (number of column)
     * @param posY Y position of Block (number of row)
     */
    public void setPosXY(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
}