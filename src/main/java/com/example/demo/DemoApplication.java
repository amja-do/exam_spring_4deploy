package com.example.demo;

import com.example.demo.config.RsaKeyProperties;
import com.example.demo.enums.Post;
import com.example.demo.model.Employee;
import com.example.demo.model.Project;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.ProjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.HiddenHttpMethodFilter;

import java.util.List;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}


	@Bean
	CommandLineRunner commandLineRunner(EmployeeService employeeService, ProjectService projectService, PasswordEncoder passwordEncoder){
		return args -> {
			Project project1 = projectService.save(Project.builder().name("Project 1").build());
			Project project2 = projectService.save(Project.builder().name("Project 2").build());
			Project project3 = projectService.save(Project.builder().name("Project 3").build());
			Project project4 = projectService.save(Project.builder().name("Project 4").build());
			Project project5 = projectService.save(Project.builder().name("Project 5").build());
			Project project6 = projectService.save(Project.builder().name("Project 6").build());
			employeeService.save(Employee
					.builder()
					.name("manager")
					.email("manager@mail.com")
					.password(passwordEncoder.encode("password"))
					.post(Post.ROLE_MANAGER)
					.skills(List.of("springboot", "laravel"))
					.projects(List.of(project1))
					.build());
			employeeService.save(Employee
					.builder()
					.name("dev")
					.email("dev@mail.com")
					.password(passwordEncoder.encode("password"))
					.post(Post.ROLE_DEVELOPER)
					.skills(List.of("Javascript", "Angular"))
					.projects(List.of(project1, project2))
					.build());
			employeeService.save(Employee
					.builder()
					.name("test")
					.email("test@mail.com")
					.password(passwordEncoder.encode("password"))
					.post(Post.ROLE_TESTER)
					.skills(List.of("Javascript"))
					.projects(List.of(project3))
					.build());
			employeeService.save(Employee
					.builder()
					.name("tech_lead")
					.email("tech_lead@mail.com")
					.password(passwordEncoder.encode("password"))
					.post(Post.ROLE_TECH_LEAD)
					.skills(List.of("Symphony"))
					.projects(List.of(project5))
					.build());
			employeeService.save(Employee
					.builder()
					.name("devops")
					.email("devops@mail.com")
					.password(passwordEncoder.encode("password"))
					.post(Post.ROLE_TECH_LEAD)
					.skills(List.of("ReactJS"))
					.projects(List.of(project4))
					.build());

		};
	}

}
