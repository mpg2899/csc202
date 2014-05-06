package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateTestData {

	 // Create an array of employee records
	static EmployeeRecord[] recordArray;
	
	// Define column names
	static final String[] columnNames = {"Last Name", "First Name", "Employee ID", "Salary", "ZipCode"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		 BufferedWriter bw = null;
		 String line;
		 int count = 0;
		recordArray = new EmployeeRecord[101];
		for (int i = 0; i<101; i++) {
			recordArray[i] = new EmployeeRecord("Smith" + (int)(Math.random()*100), "Joe" + (int)(Math.random()*100), (int)(Math.random()*100), (float)(Math.random()*1000), 22193+(int)(Math.random()*100));
		}
		try {
			bw = new BufferedWriter(new FileWriter("inputfile.txt"));
		
		for (int i = 0; i<101; i++) {
				   bw.write(recordArray[i].recordOut());
		}
		bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}