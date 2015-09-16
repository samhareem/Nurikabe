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
        return true;
    }
    
    public void printBoard() {
        for (int indexY = 0; indexY < this.board.length; indexY++) {
            for (int indexX = 0; indexX < this.board[indexY].length; indexX++) {
                if (this.board[indexY][indexX] == 0 || this.board[indexY][indexX] == 1) {
                    System.out.print("O ");
                } else if (this.board[indexY][indexX] == 100) {
                    System.out.print("X ");
                } else {
                    System.out.print((this.board[indexY][indexX] - 1) + " ");
                }
            }
            System.out.print("\n");
        }
    }
    
    public boolean isComplete() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
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
}
