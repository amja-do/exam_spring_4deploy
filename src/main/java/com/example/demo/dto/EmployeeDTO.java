package com.example.demo.dto;

import com.example.demo.enums.Post;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EmployeeDTO {

    private Long id;
    private String name, email;
    private Post post;

    private List<String> skills;

    private List<ProjectDTO> projects = new ArrayList<>();
}
