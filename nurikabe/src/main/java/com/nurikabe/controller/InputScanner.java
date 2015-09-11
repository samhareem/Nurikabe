/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;

import java.util.Scanner;

/**
 *
 * @author samharju
 */
public class InputScanner {
    private final Scanner scanner;
    
    public InputScanner() {
        this.scanner = new Scanner(System.in);
    }
    
    public int readInt() throws Exception {
        int returnValue = Integer.parseInt(scanner.nextLine());
        return returnValue;
    }
}
