// This class defines and object type which holds data about employee records.

package assignment4;

import java.util.Comparator;

public class EmployeeRecord implements Comparable<EmployeeRecord>{
	// Define some attributes.
	String lastName, firstName;
	int employeeNumber;
	float salary;
	int zipcode;
	
	// Constructor to define our attributes.
	public EmployeeRecord(String lastName, String firstName, int employeeNumber, float salary, int zipcode) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.employeeNumber = employeeNumber;
		this.salary = salary;
		this.zipcode = zipcode;
	}
	
	// Easy method to print out object to CSV.
	public String recordOut() {
		return lastName + "," + firstName + "," + employeeNumber + "," + salary + "," + zipcode + "\n";
	}
	
	// Sort by last name by default.
	public int compareTo(EmployeeRecord compareRecord) {
		String recordName1 = this.lastName;
		String recordName2 = compareRecord.lastName;

		//ascending order
		return recordName1.compareTo(recordName2);
	}
	
	public static Comparator<EmployeeRecord> LastNameComparator 
    = new Comparator<EmployeeRecord>() {

		public int compare(EmployeeRecord record1, EmployeeRecord record2) {

			String recordName1 = record1.lastName;
			String recordName2 = record2.lastName;

			//ascending order
			return recordName1.compareTo(recordName2);

		}

	};
	
	public static Comparator<EmployeeRecord> FirstNameComparator 
    = new Comparator<EmployeeRecord>() {

		public int compare(EmployeeRecord record1, EmployeeRecord record2) {

			String recordName1 = record1.firstName;
			String recordName2 = record2.firstName;

			//ascending order
			return recordName1.compareTo(recordName2);
		}

	};
	
	public static Comparator<EmployeeRecord> IDComparator 
    = new Comparator<EmployeeRecord>() {

		public int compare(EmployeeRecord record1, EmployeeRecord record2) {

			int id1 = record1.employeeNumber;
			int id2 = record2.employeeNumber;

			//ascending order
			return id1 - id2;
		}

	};
	
	public static Comparator<EmployeeRecord> SalaryComparator 
    = new Comparator<EmployeeRecord>() {

		public int compare(EmployeeRecord record1, EmployeeRecord record2) {

			float salary1 = record1.salary;
			float salary2 = record2.salary;

			//ascending order
			return (int)(salary1 - salary2);
		}

	};
	
	public static Comparator<EmployeeRecord> ZipComparator 
    = new Comparator<EmployeeRecord>() {

		public int compare(EmployeeRecord record1, EmployeeRecord record2) {

			int zip1 = record1.zipcode;
			int zip2 = record2.zipcode;

			//ascending order
			return zip1 - zip2;
		}

	};
}
