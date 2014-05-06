package assignment4;

public class RecordSort {
	 // Create an array of employee records
	static EmployeeRecord[] recordArray;
	
	// Define column names
	static final String[] columnNames = {"Last Name", "First Name", "Employee ID", "Salary", "ZipCode"};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		recordArray = new EmployeeRecord[10];
		for (int i = 0; i<=9; i++) {
			recordArray[i] = new EmployeeRecord("Smith" + i, "Joe", i, (float)i*50, 22193+i);
		}
		
		for (int i = 0; i<=9; i++) {
			System.out.println(recordArray[i].recordOut());
		}
	}

}
