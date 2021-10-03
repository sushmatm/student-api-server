package my.studentapi.service;

import my.studentapi.api.StudentException;
import my.studentapi.api.StudentNotFoundException;
import my.studentapi.entity.Student;
import my.studentapi.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

  private static final Logger log = LoggerFactory.getLogger(StudentServiceImpl.class);

  @Autowired private StudentRepository repo;

  @Override
  public Student save(Student student) throws StudentException {
    if (student == null) {
      throw new StudentException(400, "Student cannot be null");
    }
    log.info("Saving the record!");
    Student student1 = repo.save(student);
    if (log.isDebugEnabled()) log.debug(String.format("Save the student record: %s", student1));
    return student1;
  }

  @Override
  public Student getByStudentId(int id) throws StudentException {
    if (id < 0) {
      throw new StudentException(400, "Student ID is not valid");
    }
    log.info("Fecthing the student ID");
    Optional<Student> optional = repo.findById((id));
    if (!optional.isPresent()) {
      throw new StudentNotFoundException(400, "StudentId not found!!!");
    }
    Student existingStudent = optional.get();
    if (log.isDebugEnabled()) log.debug(String.format("Student record found: %s", existingStudent));
    return existingStudent;
  }

  @Override
  public List<Student> getByClassName(String className) throws StudentNotFoundException {
    log.info("Fecthing the student by ClassName");
    List<Student> list = repo.findByClassName(className);
    if (log.isDebugEnabled()) log.debug(String.format("Student record found: %s", list));
    return list;
  }

  @Override
  public void delete(int studentId) throws StudentNotFoundException {
    log.info("Checking if StudentId exists!");
    Optional<Student> optional = repo.findById(studentId);
    if (!optional.isPresent()) {
      throw new StudentNotFoundException(400, "StudentId not found!!!");
    }
    repo.deleteById(studentId);
  }

  @Override
  public Student update(Student student) throws StudentNotFoundException {
    Optional<Student> optional = repo.findById((student.getStudentId()));
    if (!optional.isPresent()) {
      throw new StudentNotFoundException(400, "StudentId not found!!!");
    }
    Student existingStudent = optional.get();
    if (student.getClassName() != null) existingStudent.setClassName(student.getClassName());
    if (log.isDebugEnabled()) log.debug(String.format("Student record found: %s", existingStudent));
    return repo.save(existingStudent);
  }
}
