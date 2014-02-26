package week4;
import java.util.*;

public class test1 extends testParent implements testinterface  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner keyboard = new Scanner(System.in);
		try {
			int x = keyboard.nextInt();
		}
		
		catch (Exception e) {
			
			System.out.println("Bad input");
		}

		float a = 2;
		for (int i = 1; i < 127; i++) {
			a = a * 2;
			System.out.println( a );
		}
		test1 t = new test1();
		System.out.println(t.hello(0,  0));
		
	}

	public int testa(int a, int b) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int hello(int a, int b) {
		// TODO Auto-generated method stub
		return super.hello(0, 0);
	}

}
