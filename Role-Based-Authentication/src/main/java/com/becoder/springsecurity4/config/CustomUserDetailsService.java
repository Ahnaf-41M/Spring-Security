package com.becoder.springsecurity4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.becoder.springsecurity4.model.Employee;
import com.becoder.springsecurity4.repository.EmployeeRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee user = employeeRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        } else {
            return new CustomUser(user);
        }
    }

}
