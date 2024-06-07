package com.example.demo.model;

import com.example.demo.enums.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, email;

    @JsonIgnore
    private String password;

    @ElementCollection
    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private Post post;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "employee_project_mapping", joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projects;

    @Override
    public String toString(){
        return "name : " + this.name + "\n"
                + "email : " + this.email + "\n"
                + "post : " + this.post;
    }

    public String getFormattedSkills(){
        return String.join("\n", this.skills);
    }

    public String getFormattedProjects(){
        List<String> projectsAsStringList = this.projects.stream().map(p -> p.getName()).toList();
        return String.join("\n", projectsAsStringList);
    }

}
