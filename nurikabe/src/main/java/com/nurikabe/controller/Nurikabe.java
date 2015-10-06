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
    private Gui gui;
    private final Reader reader;
    
    public static void main(String args[]) {
        Nurikabe game = new Nurikabe();
        game.start();
    }
    
    public Nurikabe() {
        this.gameBoard = new Board();
        this.reader = new Reader();
    }
    
    /**
     * Updates the model and view of the game board according to the 
     * level number given by the GUI.
     *
     * @param   levelNumber  Level to be built, given by Gui
     *
     */
    public boolean buildLevel(int levelNumber) {
        if (!setBoard(levelNumber)) {
            return false;
        }
        for (int indexX = 0; indexX < gameBoard.getBoardSize(); indexX++) {
            for (int indexY = 0; indexY < gameBoard.getBoardSize(); indexY++) {
                if (gameBoard.getGridStatus(indexX, indexY) != 0 && gameBoard.getGridStatus(indexX, indexY) != 1) {
                    gui.setBoardButtonLabel((indexX+(9*indexY)), gameBoard.getGridStatus(indexX, indexY) - 1);
                }
            }
        }
        gui.updateMistakes(gameBoard.getNumberOfMistakes());
        return true;
    }
    
   /**
    * Initialises Gui.    
    */
    public void start() {
        gui = new Gui(this);
    }    
    
    private boolean setBoard(int level) {
        ArrayList<Integer> boardInfo = reader.readFile("/levels/" + level + ".txt");
        return gameBoard.setBoard(boardInfo);
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



