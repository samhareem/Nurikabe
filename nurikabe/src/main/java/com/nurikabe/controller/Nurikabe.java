package com.nurikabe.controller;
import com.nurikabe.gui.Gui;
import com.nurikabe.model.*;
import java.util.ArrayList;

/**
 * The class contains the main method for the Nurikabe program, and other 
 * methods used to get and modify data in the Board class. 
 * 
 * @author samharju
 */
public class Nurikabe {
    private final Board gameBoard;
    private Gui gui;
    private final Reader reader;
    private final int numberOfLevels = 2;
    
    
    /**
     * Main method for the program.
     * 
     * @param args Currently not implemented.
     */
    public static void main(String args[]) {
        Nurikabe game = new Nurikabe();
        game.start();
    }
    
    /**
     * Constructor for Nurikabe class, intializes gameBoard and reader.
     */
    public Nurikabe() {
        this.gameBoard = new Board();
        this.reader = new Reader();
    }
    
    /**
    * Initialises GUI.    
    */
    public void start() {
        gui = new Gui(this);
    }
    
    /**
     * Updates the model and view of the game board according to the 
     * level number given by the GUI.
     *
     * @param   levelNumber  Level to be built, given by GUI
     * 
     * @return  Returns true if level was built successfully, false otherwise
     */
    public boolean buildLevel(int levelNumber) {
        if (!setBoard(levelNumber)) {
            return false;
        }
        for (int indexX = 0; indexX < gameBoard.getBoardSize(); indexX++) {
            for (int indexY = 0; indexY < gameBoard.getBoardSize(); indexY++) {
                if (gameBoard.getGridStatus(indexX, indexY) != 0 && gameBoard.getGridStatus(indexX, indexY) != 1) {
                    gui.setBoardButtonLabel((indexX + (9 * indexY)), gameBoard.getGridStatus(indexX, indexY) - 1);
                }
            }
        }
        gui.updateMistakes(gameBoard.getNumberOfMistakes());
        return true;
    }
    
    /**
    * Checks grid at board position (x, y).
    *
    * @param    indexX  X coordinate of grid being checked, given by Gui.
    *
    * @param    indexY  Y coordinate of grid being checked, given by Gui.
    *
    * @return   Boolean specifying whether move was correct (true) or incorrect (false)
    */
    public boolean checkGrid(int indexX, int indexY) {
        if (gameBoard.getGridStatus(indexX, indexY) == 0) {
            gameBoard.markGrid(indexX, indexY);
            return true;
        } else {
            gameBoard.addMistake();
            gui.updateMistakes(gameBoard.getNumberOfMistakes());
            return false;
        }
    }
    
    /**
     * 
     * Checks to see if puzzle is complete.
     * 
     * @see Board#isComplete()
     * 
     * @return True if puzzle is solved, otherwise false
     */
    public boolean checkIfComplete() {
        return gameBoard.isComplete();
    }
    
    /**
     * Resets marked grids on the game board and sets number of mistakes to 0.    
     */
    public void resetBoard() {
        gameBoard.resetBoard();
        gui.updateMistakes(gameBoard.getNumberOfMistakes());
    }
    
    /**
    *
    * Returns number of files in level folder.
    * 
    * @return Number of levels 
    */
    public int getNumberOfLevels() {
        return this.numberOfLevels;
    }
    
    private boolean setBoard(int level) {
        ArrayList<Integer> boardInfo = reader.readFile("/levels/" + level + ".txt");
        if (boardInfo.isEmpty() || boardInfo.size() != 81) {
            return false;
        }
        return gameBoard.setBoard(boardInfo);
    }
}



