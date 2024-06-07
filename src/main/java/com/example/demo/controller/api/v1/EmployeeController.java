package com.example.demo.controller.api.v1;

import com.example.demo.helper.Mapper;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController("employeeApiController")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<?> index(){
        System.out.println(SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal());
            List<Employee> employees = employeeService.get();
            return ResponseEntity.ok(employees.stream().map(Mapper::toEmployeeDTO).collect(Collectors.toList()));
    }

}
