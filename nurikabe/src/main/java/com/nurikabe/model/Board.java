/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.model;

import java.io.IOException;
import java.io.InputStream;
/**
 *
 * @author samharju
 */
public class Board {
    private int[][] board;
    private int numberOfMistakes;
    
    public Board() {
        this.numberOfMistakes = 0;
        this.board = new int[9][9];
    }
    
    public void printBoard() {
        for (int indexY = 0; indexY < this.board.length; indexY++) {
            for (int indexX = 0; indexX < this.board[indexY].length; indexX++) {
                System.out.println(this.board[indexY][indexX]);
            }
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
}
