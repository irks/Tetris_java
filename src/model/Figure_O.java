package model;

/**
 * Figure_O overrides the rotate method from class Figure, because this figure cannot be rotated.
 * @author irek
 */
public class Figure_O extends Figure {

    /**
     * Creating 4 blocks with specified coordinates and color by calling the parent constructor.
     */
    public Figure_O() {
        super(4, 0, 5, 0, 4, 1, 5, 1, 3);   
    }
    
    /**
     * Figure_O cannot be rotated, so the parent method is overriden.
     * @param isPanelTaken not used, but it has to be here, because we want to override rotate function from parent class
     * @return false because O figure cannot be rotated
     */
    @Override
    public boolean rotate(boolean [][]isPanelTaken) {
        return false;
    }
}