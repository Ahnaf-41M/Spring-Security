package com.becoder.springsecurity4.service;

import com.becoder.springsecurity4.model.Employee;

public interface EmployeeService {
    public void saveEmployee(Employee user);

    public boolean employeeExists(Employee user);

    public Employee findByEmail(String email);

    public void removeSessionMessage();
}
