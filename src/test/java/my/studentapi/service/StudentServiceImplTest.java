package my.studentapi.service;

import my.studentapi.api.StudentException;
import my.studentapi.api.StudentNotFoundException;
import my.studentapi.entity.Student;
import my.studentapi.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceImplTest {

  @InjectMocks StudentServiceImpl service;

  @Mock StudentRepository repository;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void Should_Create_Student_For_ValidInput() throws StudentException {
    Student student = new Student();
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    service.save(student);
    verify(repository).save(student);
  }

  @Test
  public void Should_ThrowException_When_Student_IsNull() throws StudentException {
    Student student = new Student();
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    // service.save(null);
    // Exception expected = new StudentException(400,"Student cannot be null");
    //  when(repository.save(null)).thenThrow(expected);
    try {
      service.save(null);
    } catch (StudentException e) {
      assertEquals("Student cannot be null", e.getMessage());
      return;
    }
    fail();
  }

  @Test
  public void Should_ThrowException_When_StudentID_NotPresent() {
    Student student = new Student();
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    Exception expected = new RuntimeException();
    when(repository.findById(1)).thenThrow(expected);
    try {
      service.getByStudentId(1);
    } catch (Exception e) {
      assertEquals(expected, e);
      return;
    }
    fail(); // when it is success , it will not enter the catch block so expictly failing the test
            // case
  }

  @Test
  public void Should_get_Student_if_StudentID_Exists() throws StudentException {
    Student student = new Student();
    student.setStudentId(1);
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    when(repository.findById(1)).thenReturn(java.util.Optional.of(student));
    Student student1 = service.getByStudentId(1);
    assertEquals(student, student1);
  }

  @Test
  public void Should_get_Student_if_ClassName_Exists() throws StudentNotFoundException {
    List<Student> list = new ArrayList<>();
    Student student = new Student();
    student.setStudentId(1);
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    list.add(student);
    when(repository.findByClassName("4A")).thenReturn(list);
    List<Student> student1 = service.getByClassName("4A");
    assertEquals(student, student1.get(0));
  }

  @Test
  public void Should_Delete_When_StudentID_Exists() throws StudentException {
    Student student = new Student();
    student.setStudentId(1);
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    when(repository.findById(1)).thenReturn(java.util.Optional.of(student));
    service.delete(1);
    verify(repository).deleteById(1);
  }

  public void Should_ThrowException_When_StudnetID_NotExists() throws StudentException {
    Student student = new Student();
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    service.save(student);
    service.delete(2);
    verify(repository).deleteById(2);
  }

  @Test
  public void Should_Update_Student_With_New_ClassName() throws StudentNotFoundException {
    Student student = new Student();
    student.setStudentId(1);
    student.setClassName("4A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    when(repository.findById(1)).thenReturn(java.util.Optional.of(student));
    Student updated = new Student();
    updated.setStudentId(1);
    updated.setClassName("5A");
    updated.setFirstName("Sushma");
    updated.setLastName("TM");
    updated.setNationality("Indian");
    service.update(updated);
    verify(repository).save(updated);
  }
}
