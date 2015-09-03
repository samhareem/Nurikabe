/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;
import com.nurikabe.model.*;
import java.util.ArrayList;
/**
 *
 * @author samharju
 */
public class Nurikabe {
    private Board gameBoard;
    private Reader reader;
    
    public void initialize() {
        String levelPath = "/levels/1.txt";
        this.gameBoard = new Board();
        this.reader = new Reader(levelPath);
        setBoard();
        gameBoard.printBoard();
    }
    
    private void setBoard() {
        ArrayList<Integer> boardInfo = reader.readFile();
        for (int i = 0; i < boardInfo.size(); i++) {
            gameBoard.setGridStatus(i % 9, i / 9, boardInfo.get(i));
        }
    } 
}



