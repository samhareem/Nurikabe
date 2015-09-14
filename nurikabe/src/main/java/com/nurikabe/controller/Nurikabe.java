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
    private final Board gameBoard;
    private final Reader reader;
    private final InputScanner scanner;
    
    public Nurikabe() {
        this.gameBoard = new Board();
        this.reader = new Reader();
        this.scanner = new InputScanner();
    }
    
    public void start() {
        int level = chooseLevel();
        setLevelFilePath("/levels/" + level + ".txt");
        setBoard();
        gameBoard.printBoard();
        while (true) {   
            System.out.println("Choose x (1-9):");
            int x = getGridCoordinate();
            System.out.println("Choose y (1-9):");
            int y = getGridCoordinate();
            if (gameBoard.getGridStatus(x - 1, y - 1) == 0) {
                gameBoard.setGridStatus(x - 1, y - 1, 100);
            } else {
                gameBoard.addMistake();
                System.out.println("Wrong move!");
                continue;
            }
            if (gameBoard.isComplete()) {
                break;
            }
            gameBoard.printBoard();
        }
        System.out.println("Game won!");
    }

    private int chooseLevel() {
        int level;
        while (true) {
            System.out.println("Choose level?");
            level = getIntegerInput();
            if (level != -1) {
                return level;
            } else {
                System.out.println("Invalid level");
            }
        }
    }
    
    private int getGridCoordinate() {
        while (true) {
            int ret = getIntegerInput();
            if (checkNumber(ret)) {
                return ret;
            }
        }
    }
    private boolean checkNumber(int x) {
        if (x < 1 || x > 9) {
            System.out.println("Invalid number!");
            return false;
        }
        return true;
    }
    
    private void setLevelFilePath(String path) {
        reader.setFilePath(path);
    }
    
    private void setBoard() {
        ArrayList<Integer> boardInfo = reader.readFile();
        for (int i = 0; i < boardInfo.size(); i++) {
            gameBoard.setGridStatus(i % 9, i / 9, boardInfo.get(i));
        }
    }
    
    private int getIntegerInput() {
        int input;
        try {
            input = scanner.readInt();
        } catch (Exception noValidNumber) { return -1; }
        return input;
    }
}



