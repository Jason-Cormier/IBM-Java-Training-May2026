package IBM.Training.com.Day4;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 50000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 52000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
        
        List<Employee> cleanEmpList = new ArrayList<>();
        Set<String> dupNames = new HashSet<>();
        for (Employee emp : employees) {
        	if (!dupNames.contains(emp.getName())) {
        		dupNames.add(emp.getName());
        		cleanEmpList.add(emp);
        	}
        }
        
        System.out.println("=== Unique Employees ===");
        for (Employee emp : cleanEmpList) {
        	System.out.println(emp);
        }
        System.out.println();
        
        Map<String, List<Employee>> groupEmp = new HashMap<>();
        for (Employee emp : employees) {
        	String dept = emp.getDept();
        	if (!groupEmp.containsKey(dept)) {
        		groupEmp.put(dept, new ArrayList<>());
        	}
        	groupEmp.get(dept).add(emp);
        }
        
        System.out.println("=== Employees by Department ===");
        for (String dept : groupEmp.keySet()) {
        	System.out.println(dept + ":");
        	for (Employee emp : groupEmp.get(dept)) {
        		System.out.println("   - " + emp);
        	}
        }
        System.out.println();
        
        System.out.println("=== Highest Paid per Department ===");
        for (String dept : groupEmp.keySet()) {
        	List<Employee> highPaidList = groupEmp.get(dept);
        	Employee highPaidEmp = highPaidList.get(0);
        	
        	for (Employee emp : highPaidList) {
        		if (emp.getSalary() > highPaidEmp.getSalary()) {
        			highPaidEmp = emp;
        		}
        	}
        	System.out.println(dept + ": " + highPaidEmp);
        }
        System.out.println();
        
        System.out.println("=== Employees Sorted by Salary (Desc) ===");
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
        for (Employee emp : employees) {
        	System.out.println(emp);
        }
        System.out.println();
        
        Set<Double> uniqSalary = new TreeSet<>();
        for (Employee emp : employees) {
        	if (!uniqSalary.contains(emp.getSalary())) {
        		uniqSalary.add(emp.getSalary());
        	}
        }
        System.out.println("=== Unique Salaries (Sorted) ===");
        for (Double salary : uniqSalary) {
        	System.out.println(salary);
        }
	}
}
