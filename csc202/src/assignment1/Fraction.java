package assignment1;

public class Fraction implements NumericalOperations{
	private int numerator, denomenator;

	public Fraction(int i, int j) {
		// TODO Auto-generated constructor stub
		this.numerator = i;
		this.denomenator = j;
	}

	@Override
	public NumericalOperations add(NumericalOperations number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumericalOperations subtract(NumericalOperations number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumericalOperations multiply(NumericalOperations number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NumericalOperations divide(NumericalOperations number) {
		// TODO Auto-generated method stub
		return null;
	}

	public String print() {
		return "" + this.numerator + "/" + this.denomenator;
	}
	
	public Fraction t2() {
		return this;
	}

}
