package my.studentapi.repository;

import my.studentapi.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
  @Query(value = "SELECT * FROM Student WHERE className = ?1", nativeQuery = true)
  List<Student> findByClassName(String className);
}
