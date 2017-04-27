package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Board;
import model.*;
import view.GUI_Tetris;

/**
 * The controller class. Controller has attibutes: GUI_Tetris (view), Board (model), BoardScore (model),
 * RandomFigueGenerator (model), GameState (model), KeyListener,
 * array of twoFigures (the one which is steered and the next figure).
 * This class has the game loop.
 * @author irek
 * 
 */
public class Controller {
    private GUI_Tetris TG;
    private Board BD;
    private BoardScore BS;
    
    private Figure[] twoFigures;
            
    private RandomFigureGenerator figGen;
    
    private GameState state;
    
    private KeyListener keyListener;
    private int keyCode;
    
    private static final String[] option = { "New game", "Exit the game" };
    
    /**
     * Constructor creates new models and view, is setting game state and creating a KeyListener.
     * After all this initialisations, there is created game loop.
     */
    public Controller() {
        TG = new GUI_Tetris();
        BD = new Board();
        BS = new BoardScore();
        figGen = new RandomFigureGenerator();
        
        state = GameState.RUNNING;
    
        twoFigures = new Figure[2];
        
        twoFigures[0] = figGen.giveRandomFigure();
        twoFigures[1] = figGen.giveRandomFigure();
        
        BD.addFigure(twoFigures[1]);
        TG.displayBoard(BD.getFiguresOnBoard(), BD.getPlaceTakenMatrix());
        
        TG.refreshNextFigure(twoFigures[0]);
        
        //KEY LISTENERS
        
        keyListener = new KeyListener() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                keyCode = e.getKeyCode();
                if(state != GameState.PAUSED) {
                    if(keyCode == KeyEvent.VK_LEFT) {
                        if(BD.tryMove(twoFigures[1], -1, 0)) {
                            BD.freshFigureLeft();
                            TG.displayBoard(BD.getFiguresOnBoard(), BD.getPlaceTakenMatrix());
                        }
                    }
                    if(keyCode == KeyEvent.VK_RIGHT) {
                        if(BD.tryMove(twoFigures[1], 1, 0)) {
                            BD.freshFigureRight();
                            TG.displayBoard(BD.getFiguresOnBoard(), BD.getPlaceTakenMatrix());
                        }
                    }
                    if(keyCode == KeyEvent.VK_DOWN) {
                        if(BD.tryMoveDownFreshFigure()) {
                            BD.freshFigureDown();
                            TG.displayBoard(BD.getFiguresOnBoard(), BD.getPlaceTakenMatrix());
                        }
                    }
                    if(keyCode == KeyEvent.VK_UP) {
                        if(BD.freshFigureRotate()) {

                            TG.displayBoard(BD.getFiguresOnBoard(), BD.getPlaceTakenMatrix());
                        }
                    }
                }
                
                if(keyCode == KeyEvent.VK_P) {
                    if(state == GameState.PAUSED)
                        resumeGame();                        
                    else if(state == GameState.RUNNING)
                        state = GameState.PAUSED;
                }
                e.consume();
            }
           

            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        };
        
        TG.addKeyListener(keyListener);
       
        
            //GAME LOOP      
        while(true) {
            
            if(state == GameState.PAUSED) {
                synchronized (this) {
                    while(state == GameState.PAUSED)
                        try { wait(); } 
                        catch (InterruptedException ex) {
                            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }
            else {
                if(BD.tryMoveDownFreshFigure() ) 
                    BD.freshFigureDown();
                else { //the figure reached bottom of board or already fallen figure
                    BS.addLines(BD.removeFullLines()); //removing lines and increasing scores basing on lines removed
                    TG.refreshScoreAndLevel(BS.getScore(), BS.getLevel()); //refreshing scores and level
                    twoFigures[1] = twoFigures[0];
                    twoFigures[0] = figGen.giveRandomFigure(); //generating new figure

                    if(BD.tryMove(twoFigures[1], 0, 0)) { //checking if the figure can be placed at the top of the board
                        BD.addFigure(twoFigures[1]);

                        TG.refreshNextFigure(twoFigures[0]); //refreshing new figure panel
                    }
                    else { //the figure cannot be placed at the top of the board, that means the game over
                        setGameOver(); //changing state of game

                        int choosenOption =  TG.showGameOverMessage(BS.getScore(), BS.getLevel(), option ); //show options it player
                        
                        if(choosenOption == 0) { //if player has chosen "New game"
                            BS.reset();
                            BD.resetBoard();

                            state = GameState.RUNNING;

                            BD.addFigure(twoFigures[1]);
                            TG.refreshNextFigure(twoFigures[0]);
                            TG.refreshScoreAndLevel(BS.getScore(), BS.getLevel());
                            TG.refreshTopScore(BS.getTopScore());

                        }
                        else //playes has chosen "Exit game"
                            System.exit(0);  
                    }
                }

                TG.displayBoard(BD.getFiguresOnBoard(), BD.getPlaceTakenMatrix());
           
                try { Thread.sleep(BS.getSpeed());  }               //1000 milliseconds is one second.
                catch(InterruptedException ex) { Thread.currentThread().interrupt(); }
            }
        }// GAME LOOP
    }
    
    private synchronized void resumeGame() {
        state = GameState.RUNNING;
        notify();
    }
    
    private void pauseGame(){
        state = GameState.PAUSED;
    }
 
    private void setGameOver() {
        state = GameState.ENDED;
    }    
}