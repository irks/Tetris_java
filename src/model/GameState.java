package model;

/**
 *
 * @author irek
 */
public enum GameState {

    /**
     * the game is running
     */
    RUNNING,

    /**
     * the game is paused
     */
    PAUSED,

    /**
     * game over, player can choose between new game or exiting game
     */
    ENDED,
}