/*
 * Michael Gugino
 * CSC 202
 * Assignment 1
 * 02/25/2014
 * 
 * This class is the Fraction that implements NumericalOperations interface.
 */
package assignment1;

public class Fraction implements NumericalOperations{
	
	// Instance variables for our fraction
	public int numerator, denominator;

	public Fraction(int i, int j) {
		// TODO Auto-generated constructor stub
		this.numerator = i;
		this.denominator = j;
	}

	// Method for adding two fractions.  Requires number be a valid Fraction
	public NumericalOperations add(NumericalOperations number) {
		// Transcribe number into Fraction type
		Fraction number2 = (Fraction) number;
		
		// Create temporary variables for numerators.
		int num1 = this.numerator;
		int num2 = number2.numerator;
		
		// Create temporary variables for denominators.
		int den1 = this.denominator;
		int den2 = number2.denominator;
		
		// Cross multiply and add.
		int resultnum = (num1 * den2) + (num2 * den1);
		int resultden = den1 * den2;
		
		// Find GCD
		int gcd = reduce(resultnum, resultden);
		
		// Return a fraction
		NumericalOperations result = new Fraction(resultnum / gcd, resultden / gcd);
		return result;
	}

	// Method for subtraction a fraction from this object instance.  Requires number be a valid Fraction
	@Override
	public NumericalOperations subtract(NumericalOperations number) {
		// Transcribe number into Fraction type
		Fraction number2 = (Fraction) number;
		
		// Create temporary variables for numerators.
		int num1 = this.numerator;
		int num2 = number2.numerator;
		
		// Create temporary variables for denominators.
		int den1 = this.denominator;
		int den2 = number2.denominator;
		
		// Cross multiply and subtract.
		int resultnum = (num1 * den2) - (num2 * den1);
		int resultden = den1 * den2;
		
		// Find GCD
		int gcd = reduce(resultnum, resultden);
		
		// Return a fraction
		NumericalOperations result = new Fraction(resultnum / gcd, resultden / gcd);
		return result;
	}

	@Override
	public NumericalOperations multiply(NumericalOperations number) {
		// Transcribe number into Fraction type
		Fraction number2 = (Fraction) number;
		
		// Create temporary variables for numerators.
		int num1 = this.numerator;
		int num2 = number2.numerator;
		
		// Create temporary variables for denominators.
		int den1 = this.denominator;
		int den2 = number2.denominator;;
		
		// Multiply numerators and denominators
		int resultnum = num1 * num2;
		int resultden = den1 * den2;
		
		// Find GCD
		int gcd = reduce(resultnum, resultden);
		
		// Return a fraction
		NumericalOperations result = new Fraction(resultnum / gcd, resultden / gcd);
		return result;
	}

	@Override
	public NumericalOperations divide(NumericalOperations number) {
		// Transcribe number into Fraction type
		Fraction number2 = (Fraction) number;
		
		// Create temporary variables for numerators.
		int num1 = this.numerator;
		int num2 = number2.numerator;
		
		// Create temporary variables for denominators.
		int den1 = this.denominator;
		int den2 = number2.denominator;
		
		// Multiply this (Fraction) by inverse of number2 (Fraction)
		int resultnum = num1 * den2;
		int resultden = den1 * num2;
		
		// Find GCD
		int gcd = reduce(resultnum, resultden);
		
		// Return a fraction
		NumericalOperations result = new Fraction(resultnum / gcd, resultden / gcd);
		return result;
	}

	// Print our fraction in a nice format
	public String print() {
		return "" + this.numerator + "/" + this.denominator;
	}
	
	// Reduce a fraction to the GCD
	private int reduce(int a, int b) {
	    int temp;
		
		while (b > 0) {
			temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}

}
