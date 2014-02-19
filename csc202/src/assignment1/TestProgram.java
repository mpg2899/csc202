package assignment1;

public class TestProgram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NumericalOperations mynumber1 = new Fraction(2, 4);
		NumericalOperations mynumber2 = new Fraction(2, 5);
		Fraction result = (Fraction) mynumber1.add(mynumber1);
		System.out.println(mynumber1.print());
		result.t2().t2();
		
	}

}
