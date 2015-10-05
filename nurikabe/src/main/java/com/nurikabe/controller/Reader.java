/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nurikabe.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author samharju
 */
public class Reader {
    
    public Reader() {
    }
    
    /**
     * Reads resource determined by filePath parameter.
     * 
     * @param filePath  Path of resource file
     * @return  ArrayList containing integers from the level file in order read.
     */
    public ArrayList<Integer> readFile(String filePath) {
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filePath)));
        } catch (NullPointerException e) { return new ArrayList<Integer>(); }
        ArrayList<Integer> contents = new ArrayList<>();
        String input = null;
        try {
            input = in.readLine();
        } catch (IOException e) { return contents; }
        while (input != null) {
            contents.add(Integer.parseInt(input));
            try {
                input = in.readLine();
            } catch (IOException e) { return contents; }
        }
        return contents;
    }
}
