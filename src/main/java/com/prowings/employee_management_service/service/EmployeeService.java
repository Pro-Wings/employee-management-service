package com.prowings.employee_management_service.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.prowings.employee_management_service.entity.Employee;
import com.prowings.employee_management_service.repository.EmployeeRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return (List<Employee>) employeeRepository.findAll();
	}

	public Employee getEmployeeById(Long id) {
		return employeeRepository.findById(id).get();
	}

	public Employee updateEmployee(Long id, Employee updateEmployee) {

		Employee fetchedEmployee = employeeRepository.findById(id).get();

		if (fetchedEmployee != null) {
			fetchedEmployee.setName(updateEmployee.getName());
			fetchedEmployee.setDepartment(updateEmployee.getDepartment());
			fetchedEmployee.setEmail(updateEmployee.getEmail());
			fetchedEmployee.setSalary(updateEmployee.getSalary());
			fetchedEmployee.setAge(updateEmployee.getAge());
			fetchedEmployee.setGender(updateEmployee.getGender());
			fetchedEmployee.setDateOfJoining(updateEmployee.getDateOfJoining());
			
			fetchedEmployee.setAddress(updateEmployee.getAddress());

			return employeeRepository.save(fetchedEmployee);
		} else {
			log.error("Employee not found with id : ",id);
			return null;
		}
	}

	public boolean deleteEmployee(Long id) {
		if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			log.info("Employee deleted successfully with id : ", id);
			return true;
		} else {
			log.error("Employee not found with id : {}", id);
			return false;
		}
	}
	
	public List<Employee> getEmployeesByDepartment(String department) {
		return employeeRepository.findByDepartment(department);
	}

	public List<Employee> getEmployeesByCountry(String country) {
		return employeeRepository.findByAddressCountry(country);
	}
	
	public List<Employee> getEmployeesBySalaryGreaterThan(Double salary) {
		return employeeRepository.findBySalaryGreaterThan(salary);
	}
	
    public List<Employee> getEmployeesByDepartments(List<String> departments) {
        return employeeRepository.findByDepartmentIn(departments);
    }

    public List<Employee> getEmployeesBeforeJoiningDate(LocalDate date) {
    	return employeeRepository.findByDateOfJoiningBefore(date);
    }

    public List<Employee> getEmployeesBetweenJoiningDate(LocalDate start, LocalDate end) {
    	return employeeRepository.findByDateOfJoiningBetween(start, end);
    }

	public Page<Employee> findAll(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
}
