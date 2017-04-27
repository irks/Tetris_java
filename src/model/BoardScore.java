package model;

/**
 * This class is responsible for management of scores, level and top scores.
 * Each five lines cleared is increasing the level by one.
 * The bigger level is the faster figures falls.
 * Max level in this game is 7.
 * @author irek
 */
public class BoardScore {
    static final int MAXLEVEL =7;
    
    private int level;
    private int lines;
    private int score;
    private int topScore;
    
    /**
     * only changing values of attributes to 0 
     */
    public BoardScore() {
        level = lines = score = topScore = 0;
    }
    
    /**
     * reset the board score, setting the Top Score and reseting values of other attributes
     */
    public void reset() {
        setTopScore();
        level = lines = score = 0;
    }
    
    /**
     * check if we need to change Top Score, if we need - change it
     */
    public void setTopScore() {
        if(score > topScore)
            topScore = score;
    }
    
    /**
     * @return the Top Score
     */
    public int getTopScore() {
        return topScore;
    }
    
    /**
     * @return the speed of game, it depends on level of current game
     */
    public int getSpeed() {
        switch(level) {
            case 0:
                return 750;
            case 1:
                return 650;
            case 2:
                return 550;
            case 3:
                return 470;
            case 4:
                return 390;
            case 5:
                return 300;
            case 6:
                return 200;
            case 7:
                return 150;
            default:
                return 150;
        }
    }
    
    private void addScore(int sc) {
        score += sc;
    }
    
    /**
     *
     * @param linesNr how many lines player had cleaned
     */
    public void addLines(int linesNr) {
        
        switch(linesNr) {
            case 1:
                addScore(10);
                break;
            case 2:
                addScore(20);
                break;
            case 3:
                addScore(30);
                break;
            case 4:
                addScore(40);
                break;
            default:
                return;
        }       
        lines += linesNr;
        if(lines > 5)
            addLevel();  
    }
    
    private void addLevel() {
        lines %= 5;
        if(level < MAXLEVEL) 
            ++level;
    }
    
    /**
     * @return the level of current game
     */
    public int getLevel() {
        return level;
    }
    
    /**
     * @return scores of current game
     */
    public int getScore() {
        return score;
    }  
}
