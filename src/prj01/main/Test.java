package prj01.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

class Employee {

	private Long id;
	private Long number;
	private String name;
	private String type;
	private Double salary;
	
	public Employee(Long id, Long number, String name, String type, Double salary) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
		this.type = type;
		this.salary = salary;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}

}

public class Test {
	
	private List<Employee> employees = new ArrayList<>();

	private Map<String, Consumer<Employee>> employeesAction = new HashMap<>();
	
	public List<Employee> findAllEmployees() {
		employees.add(new Employee(1L, 10L, "peter", "developer", Double.valueOf(1500)));
		employees.add(new Employee(2L, 12L, "luke", "manager", Double.valueOf(3100)));
		employees.add(new Employee(3L, 15L, "steve", "developer", Double.valueOf(1800)));
		
		return employees;
	}
	
	private void assignPlus(Employee employee) {
		if(employee.getType().equals("developer")) {
			employee.setSalary(employee.getSalary() + 500);
		} else if(employee.getType().equals("manager")){
			employee.setSalary(employee.getSalary() + 950);
		}
	}
	
	private void assignPlusWithLambdas(List<Employee> employees) {
		employees.stream().filter(e -> e.getType().equals("developer")).forEach(employeesAction.get("developer"));
		employees.stream().filter(e -> e.getType().equals("manager")).forEach(employeesAction.get("manager"));
	}
	
	
	public void assignPlusToAllEmployees(List<Employee> employees) {
		for(Employee e : employees) {
			this.assignPlus(e);
		}
	}

	public void assignPlusToAllEmployeesByLambdas(List<Employee> employees) {
		this.assignPlusWithLambdas(employees);
	}

	public static void main(String[] args) {
		Test t1 = new Test();
		Test t2 = new Test();
		
		List<Employee> employees = t1.findAllEmployees();
		List<Employee> employees2 = t2.findAllEmployees();
		
		t1.assignPlusToAllEmployees(employees);
		
		System.out.println("Original employees list: " );
		
		for(Employee e : employees2) {
			System.out.println("name: " + e.getName() + " and salary: " + e.getSalary());
		}
		
		t1.assignPlusToAllEmployees(employees2);

		System.out.println("\nAfter applying plus: " );

		for(Employee e : employees2) {
			System.out.println("name: " + e.getName() + " and salary: " + e.getSalary());
		}

	}

}