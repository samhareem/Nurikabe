/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.model;

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
    public void isCompleteReturnsTrueWhenBoardIsComplete() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                testBoard.setGridStatus(x, y, numGenerator.nextInt(20) + 1);
            }
        }
        assertEquals(testBoard.isComplete(), true);
    }
    
    @Test
    public void isCompleteReturnsFalseWhenBoardIsIncomplete() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                testBoard.setGridStatus(x, y, numGenerator.nextInt(20) + 1);
            }
        }
        int unsolvedGrids = numGenerator.nextInt(20) + 1;
        for (int a = 0; a < unsolvedGrids; a++) {
            testBoard.setGridStatus(numGenerator.nextInt(9), numGenerator.nextInt(9), 0);
        }
        assertEquals(testBoard.isComplete(), false);
    }
    
    @After
    public void tearDown() {
    }
}
