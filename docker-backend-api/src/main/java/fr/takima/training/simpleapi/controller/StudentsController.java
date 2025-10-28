package fr.takima.training.simpleapi.controller;

import fr.takima.training.simpleapi.model.Department;
import fr.takima.training.simpleapi.model.Student;
import fr.takima.training.simpleapi.repo.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsController {
  private final StudentRepository repo;

  public StudentsController(StudentRepository repo) {
    this.repo = repo;
  }

  @GetMapping("/departments/{name}/students")
  public List<Student> studentsByDepartment(@PathVariable String name) {
    return repo.findByDepartmentName(name);
  }

  @GetMapping("/students")
  public List<Student> allStudents() {
    return repo.findAll();
  }

  @GetMapping("/departments")
  public List<Department> allDepartments() {
    return repo.findAllDepartments();
  }
}
