package com.prowings.employee_management_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.prowings.employee_management_service.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

	List<Employee> findAll(Sort sort);

	Page<Employee> findAll(Pageable pageable);
	
	List<Employee> findByDepartment(String department);

	List<Employee> findByAddressCountry(String country);

    List<Employee> findBySalaryGreaterThan(Double salary);
    
    List<Employee> findByDepartmentIn(List<String> departments);

    List<Employee> findByDateOfJoiningBefore(LocalDate date);

    List<Employee> findByDateOfJoiningBetween(LocalDate startDate, LocalDate endDate);
    

}
