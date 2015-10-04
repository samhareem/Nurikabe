/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;
import com.nurikabe.model.*;
import com.nurikabe.GUI.*;
import com.sun.media.sound.ModelAbstractChannelMixer;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author samharju
 */
public class Nurikabe {
    private final Board gameBoard;
    private GUI gui;
    private final Reader reader;
    
    public Nurikabe() {
        this.gameBoard = new Board();
        this.reader = new Reader();
    }
    
    /**
     * Updates the model and view of the game board according to the 
     * level number given by the GUI.
     *
     * @param   levelNumber  Level to be built, given by GUI
     *
     */
    public void buildLevel(int levelNumber) {
        if (!setBoard(levelNumber)) {
            System.out.println("Error with level file");
            System.exit(0);
        }
        for (int indexX = 0; indexX < gameBoard.getBoardSize(); indexX++) {
            for (int indexY = 0; indexY < gameBoard.getBoardSize(); indexY++) {
                if (gameBoard.getGridStatus(indexX, indexY) != 0 && gameBoard.getGridStatus(indexX, indexY) != 1) {
                    gui.setBoardButtonLabel((indexX+(9*indexY)), gameBoard.getGridStatus(indexX, indexY) - 1);
                }
            }
        }
        gui.updateMistakes(gameBoard.getNumberOfMistakes());
    }
    
   /**
    * Initialises GUI.
    *
    */
    public void start() {
        gui = new GUI(this);
    }    
    
    private boolean setBoard(int level) {
        ArrayList<Integer> boardInfo = reader.readFile("/levels/" + level + ".txt");
        return gameBoard.setBoard(boardInfo);
    }
    
   /**
    * Checks grid at board position (x, y).
    *
    * @param    indexX  X coordinate of grid being checked, given by GUI.
    *
    * @param    indexY  Y coordinate of grid being checked, given by GUI.
    *
    * @return   Boolean specifying wheter move was correct (true) or incorrect (false)
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
    * Returns number of files in level folder.
    * 
    * @return Number of levels 
    */
    public int getNumberOfLevels() {
         return new File("src/main/resources/levels").list().length - 1;
    }
    
    /**
     * Resets all marked grids on the game board and sets number of mistakes to 0.    
     */
    public void resetBoard() {
        gameBoard.resetBoard();
        gui.updateMistakes(gameBoard.getNumberOfMistakes());
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
}



