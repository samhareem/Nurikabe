/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;
import com.nurikabe.model.*;
/**
 *
 * @author samharju
 */
public class Nurikabe {
    private Board gameBoard;
    
    public void initialize() {
        String levelPath = "/levels/1.txt";
        this.gameBoard = new Board(levelPath);
    }
 
}



