/*
 * Michael Gugino
 * CSC 202
 * Assignment 1
 * 02/25/2014
 * 
 * This is the NumericalOperations interface
 */

package assignment1;

public interface NumericalOperations {
	NumericalOperations add(NumericalOperations number);
	NumericalOperations subtract(NumericalOperations number);
	NumericalOperations multiply(NumericalOperations number);
	NumericalOperations divide(NumericalOperations number);
	String print();

}
