package com.prowings.employee_management_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.employee_management_service.entity.Employee;
import com.prowings.employee_management_service.service.EmployeeService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Create Employee
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		log.info("Received Request to save employee..");
		log.info("Received Employee Details: " + employee);

		return employeeService.saveEmployee(employee);
	}

	@GetMapping("/employees")
	public List<Employee> getEmployees() {
		log.info("Received Request to get all employees..");

		return employeeService.getAllEmployees();
	}

	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable Long id) {
		log.info("Received Request to get employee by id..");
		log.info("Received Employee Id: " + id);
		return employeeService.getEmployeeById(id);
	}

	@PutMapping("/employees/{id}")
	public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		log.info("Received Request to update employee with id : {}", id);
		log.info("Received Employee update Details: " + employee);

		return employeeService.updateEmployee(id, employee);
	}

	@DeleteMapping("/employees/{id}")
	public void deleteEmployee(@PathVariable Long id) {
		log.info("Received Request to delete employee with id : {}", id);
		employeeService.deleteEmployee(id);
	}

	
	@GetMapping("/employees/country/{country}")
	public List<Employee> getEmployeeByCountry(@PathVariable String country) {
		log.info("Received Request to get employees by country: {}", country);
		return employeeService.getEmployeesByCountry(country);
	}

	@GetMapping("/employees/department/{department}")
	public List<Employee> getEmployeeByDepartment(@PathVariable String department) {
		log.info("Received Request to get employees by department: {}", department);
		return employeeService.getEmployeesByDepartment(department);
	}

	
	@GetMapping("/employees/salary/{salary}")
	public List<Employee> getEmployeeBySalaryGreaterThan(@PathVariable Double salary) {
		log.info("Received Request to get employees by salary greater than: {}", salary);
		return employeeService.getEmployeesBySalaryGreaterThan(salary);
	}
	
	
    @GetMapping("/employees/by-departments")
    public List<Employee> getEmployeesByDepartments(@RequestParam List<String> departments) {
        return employeeService.getEmployeesByDepartments(departments);
    }
}
