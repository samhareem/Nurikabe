/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.model;

import java.util.ArrayList;

/**
 *
 * @author samharju
 */
public class Board {
    private final int[][] board;
    private int numberOfMistakes;
    
    public Board() {
        this.numberOfMistakes = 0;
        this.board = new int[9][9];
    }
    
    public boolean setBoard(ArrayList<Integer> boardInfo) {
        if (boardInfo.size() != 81) {
            return false;
        }
        for (int i = 0; i < boardInfo.size(); i++) {
            this.board[i / 9][i % 9] = boardInfo.get(i);
        }
        numberOfMistakes = 0;
        return true;
    }
    
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
    
    public int getBoardSize() {
        return this.board.length;
    }
    
    public int getGridStatus(int indexX, int indexY) {
        return this.board[indexY][indexX];
    }
    
    public void markGrid(int indexX, int indexY) {
        this.board[indexY][indexX] = 100;
    }
    
    public int getNumberOfMistakes() {
        return this.numberOfMistakes;
    }
    
    public void addMistake() {
        this.numberOfMistakes++;
    }
    
    public void resetMistakes() {
        this.numberOfMistakes = 0;
    }
    
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
}
