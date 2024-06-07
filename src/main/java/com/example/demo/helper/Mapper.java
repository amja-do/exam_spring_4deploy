package com.example.demo.helper;

import java.util.stream.Collectors;

import com.example.demo.dto.EmployeeDTO;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;

public class Mapper {
    public static EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setPost(employee.getPost());
        employeeDTO.setSkills(employee.getSkills());
        employeeDTO.setProjects(employee.getProjects().stream().map(Mapper::toProjectDTO).collect(Collectors.toList()));
        return employeeDTO;
    }

    public static ProjectDTO toProjectDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        return projectDTO;
    }
}
