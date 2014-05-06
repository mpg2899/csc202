package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class MergeTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		BufferedReader f1;
		BufferedReader f2;
		String line;
		try {
			
			
		f1 = new BufferedReader(new FileReader("ms_temp0.txt"));
		
		f2 = new BufferedReader(new FileReader("ms_temp1.txt"));
		

		bw = new BufferedWriter(new FileWriter("results.txt"));
		String p1 = f1.readLine();
		String p2 = f2.readLine();
		while ((line = f1.readLine()) != null) {
			bw.write(line + "\n");
			bw.write(p2+ "\n");
			p2 = f2.readLine();
		}
		bw.close();
		f1.close();
		f2.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
