package com.nurikabe.model;

import java.util.ArrayList;

/**
 * The class contains puzzle board information and methods that modify or 
 * return said information.
 *
 * @author samharju
 */
public class Board {
    private final int[][] board;
    private int numberOfMistakes;
    
    /**
     * Constructor method, initialises board and numberOfMistakes variables.
     */
    public Board() {
        this.numberOfMistakes = 0;
        this.board = new int[9][9];
    }
    
    /**
     * Sets 9*9 game board with numbers in give ArrayList.
     * 
     * @param boardInfo Numbers used to set the board.
     * @return false if does ArrayList size is incorrect, true otherwise
     */
    public boolean setBoard(ArrayList<Integer> boardInfo) {
        for (int i = 0; i < boardInfo.size(); i++) {
            if (boardInfo.get(i) < 0 || boardInfo.get(i) > 81) {
                return false;
            }
            this.board[i / 9][i % 9] = boardInfo.get(i);
        }
        numberOfMistakes = 0;
        return true;
    }
    
    
    /**
     * Unmarks all set grids, and resets mistakes to 0.
     * 
     * @see Board#resetMistakes() 
     */
    public void resetBoard() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x] == 100) {
                    board[y][x] = 0;
                }
            }
        }
        resetMistakes();
    }
    
    /**
     * Checks whether the board is complete.
     * 
     * @return true if board is complete, false otherwise. 
     */
    public boolean isComplete() {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board.length; x++) {
                if (board[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Marks grid at location indexX, indexY as set.
     * 
     * @param indexX X coordinate of grid to be marked.
     * @param indexY Y coordinate of grid to be marked.
     */
    public void markGrid(int indexX, int indexY) {
        this.board[indexY][indexX] = 100;
    }
    
    /**
     * Increase mistake counter by 1.
     */
    public void addMistake() {
        this.numberOfMistakes++;
    }
    
    /**
     * Resets mistake counter to 0.
     */
    public void resetMistakes() {
        this.numberOfMistakes = 0;
    }
    
    public int getBoardSize() {
        return this.board.length;
    }
    
    public int getGridStatus(int indexX, int indexY) {
        return this.board[indexY][indexX];
    }
    
    public int getNumberOfMistakes() {
        return this.numberOfMistakes;
    }  
}
