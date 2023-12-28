// -----------------------------------------------------
// Assignment 3
// Question: driver
// Written by: Anthony Nguyen - 40210667 AND Nektarios Zampetoulakis - 40211948
// -----------------------------------------------------

import java.io.IOException;
import Part1.*;
import Part2.*;
import Part3.*;

// This driver runs all the methods from this assignment and displays an welcome message before.

public class driver {

	public static void main(String[] args) throws ClassNotFoundException {
        try {
        	System.out.println("Welcome to our Program.\n");
            Part1.do_part1();
            Part2.do_part2();
            Part3.do_part3();
            
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
