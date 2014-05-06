package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class RecordSort {
	 // Create an array of employee records
	static EmployeeRecord[] recordArray;
	
	//  How we will parse our lines in the file.
	public static String separator = ",";
	
	// How many lines we're allowed to have in an array.
	public static final int MAX_READ_LINES = 10;

	
	// temp file name template.
	public static String tempFileTemplate = "ms_temp";
	
	// how many temp files we have.
	public static int fileCount = 0;
	
	// Read in the main file, break it into smaller parts, sort those parts, write those parts to disk.
	public static void readMainFile(String filePath, int sortColumn) {
		
		try {
		 BufferedReader br = new BufferedReader(new FileReader(filePath));
		 BufferedWriter bw = null;
		 String line;
		 int count = 0;
		
		   while ((line = br.readLine()) != null) {
			    // Read the line, and populate and our array.
		    	String[] xline = line.split(separator);
		    	System.out.println(xline[0] + xline[1] + Integer.parseInt(xline[2]) + Float.parseFloat(xline[3]) +  Integer.parseInt(xline[4]));
		    	recordArray[count % 10] = new EmployeeRecord(xline[0], xline[1], Integer.parseInt(xline[2]), Float.parseFloat(xline[3]),  Integer.parseInt(xline[4]));
		    	 
		    	// Increase counter
		    	count++;
		    	
		    	// When we hit our max read line, we sort and write out to a new temp file.
		    	if (count % MAX_READ_LINES == 0) {
		    		bw = new BufferedWriter(new FileWriter(tempFileTemplate + fileCount + ".txt"));
		    		for (int i = 0; i<MAX_READ_LINES; i++) {
		    			bw.write(recordArray[i].recordOut());
		    		}
		    		bw.close();
		    		fileCount++;
		    	}
		   }
		   br.close();
		   // We need to write out the remaining
		   // records because our file wasn't
		   // a multiple of MAX_READ_LINES
		   if (count % MAX_READ_LINES != 0) {
			   bw = new BufferedWriter(new FileWriter(tempFileTemplate + fileCount + ".txt"));
			   for (int i = 0; i<count % MAX_READ_LINES; i++) {
				   bw.write(recordArray[i].recordOut());
			   }
	    		bw.close();
	    		fileCount++;
		   }
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot open file name.  Exiting.");
			System.exit(1);
		}
	}
	
	// Define column names
	static final String[] columnNames = {"Last Name", "First Name", "Employee ID", "Salary", "ZipCode"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Read input file name, get sort column
		
		// Read the file, sort, and write temp files.
		recordArray = new EmployeeRecord[10];
		readMainFile("inputfile.txt", 0);
		// Merge the sorted temp files.
	}

}
