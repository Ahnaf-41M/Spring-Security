package com.becoder.springsecurity4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.becoder.springsecurity4.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    public boolean existsByEmail(String email);

    public Employee findByEmail(String email);
}
