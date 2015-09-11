/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.model;
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
    
    public int getGridStatus(int indexX, int indexY) {
        return this.board[indexY][indexX];
    }
    
    public void setGridStatus(int indexX, int indexY, int newStatus) {
        this.board[indexY][indexX] = newStatus;
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
