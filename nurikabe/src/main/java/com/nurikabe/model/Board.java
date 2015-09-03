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
    private char[][] board;
    
    public Board(String levelPath) {
        InputStream levelInfo = this.getClass().getResourceAsStream(levelPath);
        try {
            initializeBoard(levelInfo);
        } catch (IOException e) { 
            System.out.println("Error with level info"); 
            return; 
        }
        printBoard();
    }
    
   private boolean initializeBoard(InputStream source) throws IOException {
        board = new char[9][9];
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                board[x][y] = (char) source.read();
            }
        }
        return true;
    }
    
    private void printBoard() {
        for (int indexY = 0; indexY < this.board.length; indexY++) {
            for (int indexX = 0; indexX < this.board[indexY].length; indexX++) {
                System.out.print(this.board[indexY][indexX]);
            }
        }
    }
}
