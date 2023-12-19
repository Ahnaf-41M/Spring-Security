package com.becoder.springsecurity4.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.becoder.springsecurity4.model.Employee;
import com.becoder.springsecurity4.service.EmployeeService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EmployeeService employeeService;

    @ModelAttribute
    public void GlobalVariable(Principal p, Model mod) {
        if (p != null) {
            String email = p.getName();
            Employee employee = employeeService.findByEmail(email);
            mod.addAttribute("employee", employee);
        }
    }

    @GetMapping("/profile")
    public String admin() {
        return "admin.html";
    }

    @GetMapping("/index")
    public String index() {
        return "index.html";
    }
}
