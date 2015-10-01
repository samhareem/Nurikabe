/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;
import com.nurikabe.model.*;
import com.nurikabe.GUI.*;
import java.io.File;
import java.util.ArrayList;
/**
 *
 * @author samharju
 */
public class Nurikabe {
    private final Board gameBoard;
    private GUI gui;
    private final Reader reader;
    private final InputScanner scanner;
    
    public Nurikabe() {
        this.gameBoard = new Board();
        this.reader = new Reader();
        this.scanner = new InputScanner();
    }
    
    public void buildLevel(int level) {
        setLevelFilePath("/levels/" + level + ".txt");
        if (!setBoard()) {
            System.out.println("Error with level file");
            System.exit(0);
        }
        for (int indexX = 0; indexX < gameBoard.getBoardSize(); indexX++) {
            for (int indexY = 0; indexY < gameBoard.getBoardSize(); indexY++) {
                if (gameBoard.getGridStatus(indexX, indexY) != 0 && gameBoard.getGridStatus(indexX, indexY) != 1) {
                    gui.setBoardButtonLabel((indexX+(9*indexY)), gameBoard.getGridStatus(indexX, indexY) - 1);
                }
            }
        }
        gui.updateMistakes(gameBoard.getNumberOfMistakes());
    }
    
    public void start() {
        gui = new GUI(this);
        int level = chooseLevel();
        setLevelFilePath("/levels/" + level + ".txt");
        if (!setBoard()) {
            System.out.println("Error with level file");
            System.exit(0);
        }
        printBoard();
        while (true) {   
            System.out.println("Choose x (1-9):");
            int x = getGridCoordinate();
            System.out.println("Choose y (1-9):");
            int y = getGridCoordinate();
            if (gameBoard.getGridStatus(x - 1, y - 1) == 0) {
                gameBoard.markGrid(x-1, y-1);
            } else {
                gameBoard.addMistake();
                System.out.println("Wrong move!");
                continue;
            }
            if (gameBoard.isComplete()) {
                break;
            }
            printBoard();
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
    
    private boolean setBoard() {
        ArrayList<Integer> boardInfo = reader.readFile();
        return gameBoard.setBoard(boardInfo);
    }
    
    private int getIntegerInput() {
        int input;
        try {
            input = scanner.readInt();
        } catch (Exception noValidNumber) { return -1; }
        return input;
    }
    
   private void printBoard() {
        for (int indexY = 0; indexY < gameBoard.getBoardSize(); indexY++) {
            for (int indexX = 0; indexX < gameBoard.getBoardSize(); indexX++) {
                if (gameBoard.getGridStatus(indexX, indexY) == 0 || gameBoard.getGridStatus(indexX, indexY) == 1) {
                    System.out.print("O ");
                } else if (gameBoard.getGridStatus(indexX, indexY) == 100) {
                    System.out.print("X ");
                } else {
                    System.out.print((gameBoard.getGridStatus(indexX, indexY) - 1) + " ");
                }
            }
            System.out.print("\n");
        }
    }
    
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
    
     public int getNumberOfLevels() {
         return new File("src/main/resources/levels").list().length - 1;
    }
    
    
    public void resetBoard() {
        gameBoard.resetBoard();
        gui.updateMistakes(gameBoard.getNumberOfMistakes());
    }
    
    public boolean checkIfComplete() {
        return gameBoard.isComplete();
    }
}



