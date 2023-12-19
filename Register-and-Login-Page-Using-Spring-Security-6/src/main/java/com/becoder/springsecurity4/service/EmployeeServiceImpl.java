package com.becoder.springsecurity4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.becoder.springsecurity4.model.Employee;
import com.becoder.springsecurity4.repository.EmployeeRepository;
import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(Employee user) {
        String pass = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(pass);
        employeeRepository.save(user);
    }

    @Override
    public boolean employeeExists(Employee user) {
        return employeeRepository.existsByEmail(user.getEmail());
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    @Override
    public void removeSessionMessage() {
        HttpSession session =
                ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
                        .getRequest().getSession();

        session.removeAttribute("msg");
    }



}
