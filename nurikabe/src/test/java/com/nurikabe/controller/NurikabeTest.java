/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        ReaderTest test = new ReaderTest();
    }
    
    @After
    public void tearDown() {
    }
    
//    @Test
//    public void levelBuildsCorrectly() {
//        testNurikabe.buildLevel(1);
//    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
