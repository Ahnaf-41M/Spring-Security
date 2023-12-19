package com.becoder.springsecurity4.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.becoder.springsecurity4.model.Employee;
import com.becoder.springsecurity4.service.EmployeeService;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    EmployeeService employeeService;

    @ModelAttribute
    public void GlobalVariable(Principal p, Model mod) {
        mod.addAttribute("employee", new Employee());
    }


    @GetMapping({"/", "/index"})
    public String index(Model mod) {
        // Employee employee = new Employee();
        // mod.addAttribute("employee", employee);
        return "index.html";
    }


    @GetMapping({"/signin"})
    public String signin(Model mod) {
        return "signin.html";
    }


    @GetMapping({"/register"})
    public String register() {
        return "register.html";
    }

    @PostMapping("/saveEmp")
    public String saveEmp(HttpSession session, @ModelAttribute("employee") Employee employee) {
        // System.out.println("\n\nThis is temp user" + employee + "\n\n");

        if (employeeService.employeeExists(employee) == false) {
            employee.setRole("ROLE_" + employee.getRole().toUpperCase());
            employeeService.saveEmployee(employee);
            session.setAttribute("msg", "Registered Successfully!");
            // ClearEmployee();
        } else {
            session.setAttribute("msg", "Email exists!");
        }

        return "redirect:/register";
    }

    void ClearEmployee() {

    }

}
