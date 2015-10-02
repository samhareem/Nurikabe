/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.model;

import java.util.ArrayList;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samharju
 */
public class BoardTest {
    Board testBoard;
    Random numGenerator;
    
    public BoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testBoard = new Board();
        numGenerator = new Random();
    }
    
    @Test
    public void newBoardStartsWithZeroMistakes() {
        assertEquals(testBoard.getNumberOfMistakes(), 0);
    }
    
    @Test
    public void numberOfMistakesIncreases() {
        testBoard.addMistake();
        assertEquals(testBoard.getNumberOfMistakes(), 1);
    }
    
    @Test
    public void largeNumberOfMistakesCorrectlyAdded() {
        int n = numGenerator.nextInt(10000);
        for (int i = 0; i < n; i++) {
            testBoard.addMistake();
        }
        assertEquals(testBoard.getNumberOfMistakes(), n);
    }
    
    @Test
    public void mistakesResetProperly() {
        int n = numGenerator.nextInt(10000);
        while (n > 0) {
            testBoard.addMistake();
            n--;
        }
        testBoard.resetMistakes();
        assertEquals(testBoard.getNumberOfMistakes(), 0);
    }
    
    @Test
    public void setBoardMethodInitializesBoardArrayCorrectly() {
        int testNumber = numGenerator.nextInt(100);
        ArrayList<Integer> testBoardInfo = new ArrayList<Integer>();
        for (int i = 0; i < 81; i++) {
            testBoardInfo.add(testNumber);
        }
        testBoard.setBoard(testBoardInfo);
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                assertEquals(testNumber, testBoard.getGridStatus(x, y));
            }
        }
    }
    
    @Test
    public void isCompleteReturnsTrueWhenBoardIsComplete() {
        ArrayList<Integer> testBoardInfo = new ArrayList<Integer>();
        for (int i = 0; i < 81; i++) {
            testBoardInfo.add(numGenerator.nextInt(20) + 1);
        }
        testBoard.setBoard(testBoardInfo);
        assertEquals(testBoard.isComplete(), true);
    }
    
    @Test
    public void isCompleteReturnsFalseWhenBoardIsIncomplete() {
        ArrayList<Integer> testBoardInfo = new ArrayList<Integer>();
        for (int i = 0; i < 81; i++) {
            testBoardInfo.add(numGenerator.nextInt(20) + 1);
        }
        int unsolvedGrids = numGenerator.nextInt(20) + 1;
        for (int a = 0; a < unsolvedGrids; a++) {
            testBoardInfo.set(numGenerator.nextInt(79) + 1, 0);
        }
        testBoard.setBoard(testBoardInfo);
        assertEquals(testBoard.isComplete(), false);
    }
    
    @Test
    public void resetBoardResetsMarkedGrids() {
        ArrayList<Integer> testBoardInfo = new ArrayList<Integer>();
        for (int i = 0; i < 81; i++) {
            testBoardInfo.add(numGenerator.nextInt(20) + 1);
        }
        int unsolvedGrids = numGenerator.nextInt(20) + 1;
        for (int a = 0; a < unsolvedGrids; a++) {
            testBoardInfo.set(numGenerator.nextInt(79) + 1, 100);
        }
        testBoard.setBoard(testBoardInfo);
        testBoard.resetBoard();
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                assertFalse(100 == testBoard.getGridStatus(y, x));
            }
        }
    }
    @After
    public void tearDown() {
        
    }
}
