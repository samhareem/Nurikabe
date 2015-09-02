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
    private int[][] board;
    
    public Board() {
        initializeBoard();
        printBoard();
    }
    
    private void initializeBoard() {
        this.board = new int[9][9];
        for (int indexY = 0; indexY < this.board.length; indexY++) {
            for (int indexX = 0; indexX < this.board[indexY].length; indexX++) {
                this.board[indexY][indexX] = 0;
            }
        }
    }
    
    private void printBoard() {
        for (int indexY = 0; indexY < this.board.length; indexY++) {
            for (int indexX = 0; indexX < this.board[indexY].length; indexX++) {
                System.out.println(this.board[indexY][indexX]);
            }
        }
    }
}
