// This class defines and object type which holds data about employee records.

package assignment4;

public class EmployeeRecord {
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
		return lastName + "," + firstName + "," + employeeNumber + "," + salary + "," + zipcode;
	}
}
