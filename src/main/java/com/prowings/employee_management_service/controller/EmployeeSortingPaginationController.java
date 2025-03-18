package com.prowings.employee_management_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.employee_management_service.entity.Employee;
import com.prowings.employee_management_service.service.EmployeeService;

@RestController
public class EmployeeSortingPaginationController {

	@Autowired
	private EmployeeService employeeService;

	
	@GetMapping("/employeespagination")
	public Page<Employee> getAllEmployees(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, 
			@RequestParam(defaultValue = "id,asc") String[] sort) {

		Sort.Direction direction = sort[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));

		return employeeService.findAll(pageable);
	}

}
