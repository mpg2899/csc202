package assignment4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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
	
	// Define column names
	static final String[] columnNames = {"Last Name", "First Name", "Employee ID", "Salary", "ZipCode"};
	
	// Sort our array while reading the main input file.
	public static EmployeeRecord[] sortArray(EmployeeRecord[] recordArray, int sortColumn) {
		
		switch (sortColumn) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
		Arrays.sort(recordArray);

		return recordArray;
		
	}
	
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
		    	recordArray[count % 10] = new EmployeeRecord(xline[0], xline[1], Integer.parseInt(xline[2]), Float.parseFloat(xline[3]),  Integer.parseInt(xline[4]));
		    	 
		    	// Increase counter
		    	count++;
		    	
		    	// When we hit our max read line, we sort and write out to a new temp file.
		    	if (count % MAX_READ_LINES == 0) {
		    		// Sort our array.
		    		recordArray = sortArray(recordArray, sortColumn);
		    		
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
	

	// Merge two files together
	
	public static void mergeFiles(String file1, String file2, String outputFile, int compareColumn) {
		try {
			BufferedReader inputfile1 = new BufferedReader(new FileReader(file1));
			BufferedReader inputfile2 = new BufferedReader(new FileReader(file2));
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));

		      String line1 = inputfile1.readLine();
		      String line2 = inputfile2.readLine();
		      String[] xline1;
		      String[] xline2;
		      while (line1 != null || line2 != null) {
		    	  
		        if (line1 == null) {
		          // Write out line2, read the next line2.
		          bw.write(line2 + "\n");
		          line2 = inputfile2.readLine();
		        } 
		        else if (line2 == null) {
		           // Write out line1, read the next line1.;
			       bw.write(line1 + "\n");
		          line1 = inputfile1.readLine();
		        } 
		        else  {
		        	// Create two EmployeeRecords so we can compare.
		        	xline2 = line2.split(separator);
		        	xline1 = line1.split(separator);
		        	
		        	// Compare the column as a number.
		        	if (compareColumn > 1) {
		        		if (Float.parseFloat(xline1[compareColumn]) <= Float.parseFloat(xline2[compareColumn])) {
		        			bw.write(line1 + "\n");
		        			line1 = inputfile1.readLine();
		        		}
		        		else {
		  		          bw.write(line2 + "\n");
				          line2 = inputfile2.readLine();
				        }
		        	}
		        	// Compare as string.
		        	else {
		        		if (xline1[compareColumn].compareTo(xline2[compareColumn]) <= 0) {
		        			bw.write(line1 + "\n");
		        			line1 = inputfile1.readLine();
		        		}
		        		else {
		  		          bw.write(line2 + "\n");
				          line2 = inputfile2.readLine();
				        }
		        	}
		        	
		        }
		      }
		      inputfile1.close();
		      inputfile2.close();
		      bw.close();
		    } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		    }
		
	}
	
	
	
	// ---------------------------
	// MAIN
	// ---------------------------
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Read input file name, get sort column
		
		// Read the file, sort, and write temp files.
		recordArray = new EmployeeRecord[10];
		readMainFile("inputfile.txt", 0);
		// Merge the sorted temp files.
		String f1 = tempFileTemplate + "0.txt";
		String f2 = tempFileTemplate + "1.txt";
		String outfile = tempFileTemplate + "_result" + "0.txt";
		
		for (int i = 0; i<fileCount-1; i++) {
			mergeFiles(f1, f2, outfile, 0);
			f1 = outfile.toString();
			f2 = tempFileTemplate + (i + 2) + ".txt";
			outfile = tempFileTemplate + "_result" + (i+1) + ".txt";
			if (i >= fileCount - 3) {
				outfile = "Sorted_Results.txt";
			}
		}
		//mergeFiles(f1, f2, outfile, 2);
		System.out.println("See " + outfile + " for your results");
	}

}
