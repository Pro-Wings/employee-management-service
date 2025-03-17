package com.prowings.employee_management_service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prowings.employee_management_service.entity.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	List<Employee> findByDepartment(String department);

	List<Employee> findByAddressCountry(String country);

    List<Employee> findBySalaryGreaterThan(Double salary);
    
    List<Employee> findByDepartmentIn(List<String> departments);

}
