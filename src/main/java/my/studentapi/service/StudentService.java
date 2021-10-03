package my.studentapi.service;

import my.studentapi.api.StudentException;
import my.studentapi.api.StudentNotFoundException;
import my.studentapi.entity.Student;

import java.util.List;

public interface StudentService {
  Student save(Student student) throws StudentException;

  Student getByStudentId(int id) throws StudentException;

  List<Student> getByClassName(String className) throws StudentNotFoundException;

  void delete(int studentId) throws StudentNotFoundException;

  Student update(Student student) throws StudentNotFoundException;
}
