package com.prowings.employee_management_service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.prowings.employee_management_service.entity.Employee;
import com.prowings.employee_management_service.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/employees")
@Tag(name = "Employee Management", description = "APIs for managing employee data")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "Create a new employee", description = "Saves a new employee to the database.")
    @ApiResponse(responseCode = "201", description = "Employee created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)))
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        log.info("Received Request to save employee.." + employee);
        return employeeService.saveEmployee(employee);
    }

    @Operation(summary = "Get all employees", description = "Fetch all employees with sorting options.")
    @ApiResponse(responseCode = "200", description = "List of employees fetched successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)))
    @GetMapping
    public List<Employee> getEmployees(@RequestParam(defaultValue = "id,asc") String[] sort) {
        log.info("Fetching employees with sorting by: {}", (Object) sort);
        return employeeService.getAllEmployeesSorted(sort);
    }

    @Operation(summary = "Get employee by ID", description = "Fetch employee details using the ID.")
    @ApiResponse(responseCode = "200", description = "Employee found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Employee.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        log.info("Fetching employee with ID: {}", id);
        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Update an employee", description = "Updates employee data by ID.")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        log.info("Updating employee with ID: {}", id);
        return employeeService.updateEmployee(id, employee);
    }

    @Operation(summary = "Delete an employee", description = "Deletes employee by ID.")
    @ApiResponse(responseCode = "204", description = "Employee deleted successfully")
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        log.info("Deleting employee with ID: {}", id);
        employeeService.deleteEmployee(id);
    }

    @Operation(summary = "Get employees by country", description = "Fetch all employees belonging to a specific country.")
    @GetMapping("/country/{country}")
    public List<Employee> getEmployeeByCountry(@PathVariable String country) {
        return employeeService.getEmployeesByCountry(country);
    }

    @Operation(summary = "Get employees by department", description = "Fetch all employees belonging to a specific department.")
    @GetMapping("/department/{department}")
    public List<Employee> getEmployeeByDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    @Operation(summary = "Get employees with salary greater than a value", description = "Fetch employees whose salary is above a certain threshold.")
    @GetMapping("/salary/{salary}")
    public List<Employee> getEmployeeBySalaryGreaterThan(@PathVariable Double salary) {
        return employeeService.getEmployeesBySalaryGreaterThan(salary);
    }

    @Operation(summary = "Get employees by joining date before a specific date", description = "Fetch employees who joined before the specified date.")
    @GetMapping("/by-joiningdate-before/{date}")
    public List<Employee> getEmployeeBySalaryGreaterThan(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        return employeeService.getEmployeesBeforeJoiningDate(parsedDate);
    }

    @Operation(summary = "Get employees by joining date range", description = "Fetch employees who joined within a date range.")
    @GetMapping("/search/by-joining-date")
    public List<Employee> getEmployeesByJoiningDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return employeeService.getEmployeesBetweenJoiningDate(startDate, endDate);
    }

    @Operation(summary = "Get employees by multiple departments", description = "Fetch employees belonging to multiple departments.")
    @GetMapping("/by-departments")
    public List<Employee> getEmployeesByDepartments(@RequestParam List<String> departments) {
        return employeeService.getEmployeesByDepartments(departments);
    }
}
