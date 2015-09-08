/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.model;

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
        int n = 100;
        while (n > 0) {
            testBoard.addMistake();
            n--;
        }
        assertEquals(testBoard.getNumberOfMistakes(), 100);
    }
    
    @After
    public void tearDown() {
    }
}
