package model;

import java.util.Random;

/**
 * 
 *  There are 7 types of tetrominos in tetris game
 *  This class implements the random generator of new figures
 * @author irek
 */
public class RandomFigureGenerator {
    private final Random random;
    
    /**
     * Creating new Random instance. It is used to generate a stream of pseudorandom numbers.
     */
    public RandomFigureGenerator() {
        random = new Random(); 
    }
    
    /**
     *  
     * @return a random type of figure
     */
    public Figure giveRandomFigure() {
        int rand = random.nextInt(7);
 
        switch(rand) {
            case 0:
                return new Figure_I();
            case 1:
                return new Figure_J();
            case 2:
                return new Figure_L();
            case 3:
                return new Figure_O();
            case 4:
                return new Figure_S();
            case 5:
                return new Figure_T();
            case 6:
                return new Figure_Z();
            default:
                return new Figure_I();
        }
    }
}