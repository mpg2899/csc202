package assignment1;

public interface NumericalOperations {
	NumericalOperations add(NumericalOperations number);
	NumericalOperations subtract(NumericalOperations number);
	NumericalOperations multiply(NumericalOperations number);
	NumericalOperations divide(NumericalOperations number);
	String print();

}
