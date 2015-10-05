/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;

import java.io.IOException;
import java.util.ArrayList;
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
public class ReaderTest {
    private Reader testReader;
    
    public ReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.testReader = new Reader();
    }
    
    @Test
    public void fileIsReadCorrectly() {
        ArrayList<Integer> testList = testReader.readFile("/test.txt");
        for (int i = 0; i < testList.size(); i++) {
            assertEquals(i, testList.get(i).intValue());
        }
    }
    
    @Test()
    public void nullFileReturnsEmptyList() {
        assertTrue(testReader.readFile("Invalid filepath").isEmpty());
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
