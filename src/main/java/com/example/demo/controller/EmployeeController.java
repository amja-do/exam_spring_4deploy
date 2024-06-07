package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.request.EmployeeProject;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("employeeController")
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ProjectService projectService;

    public EmployeeController(EmployeeService employeeService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.projectService = projectService;
    }

    @GetMapping
    public String index(Model model){
            List<Employee> employees = employeeService.get();
            model.addAttribute("employees", employees);
            return "index";
    }

    @GetMapping("/affect")
    public String create(Model model){
            model.addAttribute("request", new EmployeeProject());
            model.addAttribute("employees", employeeService.get());
            model.addAttribute("projects", projectService.get());
            return "affect";
    }

    @PostMapping("/store")
    public String store(Model model, @RequestParam("employee_id") Long employeeId, @RequestParam("project_id") Long projectId){
            Employee employee = employeeService.findById(employeeId);
            Project project = projectService.findById(projectId);
            if (employee != null && project != null) {
                employee.getProjects().add(project);
                employeeService.save(employee);
            }
            List<Employee> employees = employeeService.get();
            model.addAttribute("employees", employees);
        return "redirect:/employees";
    }

    @DeleteMapping("{id}/delete")
    public String delete(Model model, @PathVariable("id") Long id){
        employeeService.delete(id);
        List<Employee> employees = employeeService.get();
        model.addAttribute("employees", employees);
        return "redirect:/employees";
    }
}
