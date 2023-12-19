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
    Employee employee;

    @Autowired
    EmployeeService employeeService;

    @ModelAttribute
    public void GlobalVariable(Model mod) {
        mod.addAttribute("employee", employee);
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin.html";
    }

    @GetMapping({"/", "/index"})
    public String index(Model mod) {
        return "index.html";
    }

    @GetMapping({"/profile"})
    public String profile(Principal p, Model mod) {
        if (p != null) {
            String email = p.getName();
            Employee employee1 = employeeService.findByEmail(email);
            employee = employee1;
        }
        return "profile.html";
    }

    @GetMapping({"/home"})
    public String home() {
        return "home.html";
    }

    @GetMapping({"/signin", "/user/signin"})
    public String signin(Model mod) {
        ClearEmployee();
        return "signin.html";
    }


    @GetMapping({"/register", "/user/register"})
    public String register() {
        return "register.html";
    }


    public void ClearEmployee() {
        employee.setName("");
        employee.setEmail("");
        employee.setMobileNo("");
        employee.setPassword("");
        employee.setRole("");
    }

    @PostMapping("/saveEmp")
    public String saveEmp(HttpSession session, @ModelAttribute("employee") Employee employee) {
        // System.out.println("\n\nThis is temp user" + employee + "\n\n");

        if (employeeService.employeeExists(employee) == false) {
            employeeService.saveEmployee(employee);
            session.setAttribute("msg", "Registered Successfully!");
            // ClearEmployee();
        } else {
            session.setAttribute("msg", "Email exists!");
        }

        return "redirect:/register";
    }

}
