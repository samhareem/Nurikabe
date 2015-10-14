/*

* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;

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
public class NurikabeTest {
        Nurikabe testNurikabe;
        Reader testReader;
        
    public NurikabeTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testNurikabe = new Nurikabe();
        testReader = new Reader();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void validLevelReturnsTrue() {
        testNurikabe.start();
        assertTrue(testNurikabe.buildLevel(1));
    }
    
    @Test
    public void invalidLevelReturnsFalse() {
        testNurikabe.start();
        assertFalse(testNurikabe.buildLevel(4));
    }
    
    @Test
    public void checkGridMethodReturnsCorrectValue() {
        ArrayList<Integer> values = testReader.readFile("/levels/1.txt");
        Random numGenerator = new Random();
        testNurikabe.start();
        testNurikabe.buildLevel(1);
        for (int tests = 0; tests < 10; tests++) {
            int testIndex = numGenerator.nextInt(81);
            if (values.get(testIndex) == 0) {
                assertTrue(testNurikabe.checkGrid((testIndex % 9), (testIndex / 9)));
            } else {
                assertFalse(testNurikabe.checkGrid((testIndex % 9), (testIndex / 9)));
            }
        }
    }
}
