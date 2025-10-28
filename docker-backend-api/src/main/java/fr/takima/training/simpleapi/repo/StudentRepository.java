package fr.takima.training.simpleapi.repo;

import fr.takima.training.simpleapi.model.Department;
import fr.takima.training.simpleapi.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
  private final JdbcTemplate jdbc;

  public StudentRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  public List<Student> findByDepartmentName(String deptName) {
    String sql = """
      SELECT s.id, s.first_name, s.last_name, d.id AS dept_id, d.name AS dept_name
      FROM students s
      JOIN departments d ON d.id = s.department_id
      WHERE d.name = ?
      ORDER BY s.id
      """;
    return jdbc.query(sql, ps -> ps.setString(1, deptName), (rs, rowNum) ->
      new Student(
        rs.getLong("id"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        new Department(rs.getLong("dept_id"), rs.getString("dept_name"))
      )
    );
  }

  public List<Student> findAll() {
    String sql = """
      SELECT s.id, s.first_name, s.last_name, d.id AS dept_id, d.name AS dept_name
      FROM students s
      JOIN departments d ON d.id = s.department_id
      ORDER BY s.id
      """;
    return jdbc.query(sql, (rs, rowNum) ->
      new Student(
        rs.getLong("id"),
        rs.getString("first_name"),
        rs.getString("last_name"),
        new Department(rs.getLong("dept_id"), rs.getString("dept_name"))
      )
    );
  }

  public List<Department> findAllDepartments() {
    String sql = """
      SELECT id, name
      FROM departments
      ORDER BY id
      """;
    return jdbc.query(sql, (rs, rowNum) ->
      new Department(rs.getLong("id"), rs.getString("name"))
    );
  }
}
