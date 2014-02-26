/*
 * Michael Gugino
 * CSC 202
 * Assignment 1
 * 02/25/2014
 * 
 * This class is the driver to test our Fraction class and our NumericalOperations interface
 */

package assignment1;

import java.util.*;

public class TestProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Read input
		Scanner keyboard;
		boolean validnums =false;
		int num1 = 0, num2 = 0, den1 = 0, den2 = 0;
		while (validnums == false) {
			num1 = 0;
			keyboard = new Scanner(System.in);
			System.out.println("Enter first numerator");
			try {
				num1 = keyboard.nextInt();
				validnums = true;
			}
			catch (Exception e) {
				System.out.println("Invalid number entered");
				num1 = 0;
			}
			
		}
		
		validnums = false;
		while (validnums == false) {
			den1 = 0;
			keyboard = new Scanner(System.in);
			System.out.println("Enter first denominator");
			try {
				den1 = keyboard.nextInt();
				validnums = true;
			}
			catch (Exception e) {
				System.out.println("Invalid number entered");
				den1 = 0;
			}
			
		}
		
		validnums = false;
		while (validnums == false) {
			num2 = 0;
			keyboard = new Scanner(System.in);
			System.out.println("Enter second numerator");
			try {
				num2 = keyboard.nextInt();
				validnums = true;
			}
			catch (Exception e) {
				System.out.println("Invalid number entered");
				num2 = 0;
			}
			
		}
		
		validnums = false;
		while (validnums == false) {
			den2 = 0;
			keyboard = new Scanner(System.in);
			System.out.println("Enter second denominator");
			try {
				den2 = keyboard.nextInt();
				validnums = true;
			}
			catch (Exception e) {
				System.out.println("Invalid number entered");
				den2 = 0;
			}
			
		}
		
		//Create two fractions
		NumericalOperations mynumber1 = new Fraction(num1, den1);
		NumericalOperations mynumber2 = new Fraction(num2, den2);
		
		// Add the fractions
		Fraction result = (Fraction) mynumber1.add(mynumber2);
		System.out.println(result.print());
		
		// Subtract
		result = (Fraction) mynumber1.subtract(mynumber2);
		System.out.println(result.print());
		
		// Multiply
		result = (Fraction) mynumber1.subtract(mynumber2);
		System.out.println(result.print());
		
		// Divide
		result = (Fraction) mynumber1.divide(mynumber2);
		System.out.println(result.print());
	}

}
