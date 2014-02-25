/*
 * Michael Gugino
 * CSC 202
 * Assignment 1
 * 02/25/2014
 * 
 * This class is the driver to test our Fraction class and our NumericalOperations interface
 */
package assignment1;

public class TestProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Create two fractions
		NumericalOperations mynumber1 = new Fraction(3, 4);
		NumericalOperations mynumber2 = new Fraction(3, 8);
		
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
