package model;

/**
 * It is more specified than class Figure.
 * It has extra attribute "rotationPhase", which helps with rotating.
 * Figure_I overrides the rotate method from class Figure.
 * @author irek
 */
public class Figure_I extends Figure {
    private int rotationPhase;

    /**
     * Creating 4 blocks with specified coordinates and color by calling the parent constructor.
     * Also it sets the rotationPhase to 0.
     */
    public Figure_I() {
        super(3, 0, 4, 0, 5, 0, 6, 0, 0);
        
        rotationPhase = 0; 
    }
    
    /**
     *  For tetromino "I" rotate method has to be overriden because "central" Block is diffrent for different rotation phase.
     *  If rotatation is done, the rotation phase is changed.
     * @param isPanelTaken 2-D boolean array which informs if the panel is taken by any figure 
     * @return false if cannot rotate, true if figure was rotated
     */
    @Override
    public boolean rotate(boolean [][]isPanelTaken) {
        int x,y;
        
        if(rotationPhase == 0 || rotationPhase == 2 ) { 
            x = super.getBlockX(2);
            y = super.getBlockY(2);   
        }
        else {
            x = super.getBlockX(1);
            y = super.getBlockY(1);
        }
        
        
        int xT, yT;
            
        for (int i = 0; i < 4; ++i) {
            xT = x + y - super.getBlockY(i);
            yT = y - x + super.getBlockX(i);

        if (xT < 0 || xT >= 10 || yT < 0 || yT>= 20)
            return false; //the figure would move outside the board
        if (isPanelTaken[yT][xT])    
            return false;  //the figure would has collision with already fallen figure
        }
       
        for(int i = 0; i < 4; ++i) 
            super.getBlock(i).setPosXY( x + y - getBlockY(i), y - x + getBlockX(i));
        
        ++rotationPhase;
        rotationPhase = rotationPhase % 4;
        
        return true;
    }
}