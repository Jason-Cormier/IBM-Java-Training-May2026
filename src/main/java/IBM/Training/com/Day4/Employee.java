package IBM.Training.com.Day4;

public class Employee {
	private String name;
	private String dept;
	private double salary;
	
	public Employee(String name, String dept, double salary) {
		this.name = name;
		this.dept = dept;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDept() {
		return dept;
	}
	
	public double getSalary() {
		return salary;
	}
	
	@Override	
	public String toString() {
		return name + " | " + dept + " | $" + salary;
	}
}
