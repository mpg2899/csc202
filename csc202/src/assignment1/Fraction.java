package assignment1;

public class Fraction extends FractionType<Fraction>{
	private int numerator, denomenator;
	
	public String add(Fraction number) {
		// TODO Auto-generated method stub
		int a1 = number.numerator + this.numerator;
		System.out.println(a1);
		return null;
	}


	public String subtract(Fraction number) {
		// TODO Auto-generated method stub
		return null;
	}


	public String multiply(Fraction number) {
		// TODO Auto-generated method stub
		return null;
	}


	public String divide(Fraction number) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Fraction(int numerator, int denomenator) {
		this.numerator = numerator;
		this.denomenator = denomenator;
	}

}
