package my.studentapi.api;


import my.studentapi.service.StudentService;
import my.studentapi.entity.Student;
import my.studentapi.model.StudentInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentApiControllerTest {

  @InjectMocks StudentApiController studentApiController;

  @Mock
  StudentService studentService;

  @Before
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void Should_get_Student_if_StudentID_Exists() throws StudentException {
    studentApiController.setModelMapper(new ModelMapper());
    studentApiController.setStudentService(studentService);
    Student student = new Student();
    student.setStudentId(1);
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    when(studentService.getByStudentId(1)).thenReturn(student);
    StudentInfo studentInfo = new StudentInfo();
    //  studentInfo.setStudentID(student.getStudentId());
    studentInfo.setClassName("3A");
    studentInfo.setFirstName("Sushma");
    studentInfo.setLastName("TM");
    studentInfo.setNationality("Indian");
    ResponseEntity<StudentInfo> responseEntity = studentApiController.getStudentId(1);
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
  }

  @Test
  public void Should_get_StudentID_Not_Exists() throws StudentException {
    studentApiController.setModelMapper(new ModelMapper());
    studentApiController.setStudentService(studentService);
    Student student = new Student();
    // student.setStudentId(1);
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    when(studentService.getByStudentId(2))
        .thenThrow(new StudentNotFoundException(400, "Not found!"));
    StudentInfo studentInfo = new StudentInfo();
    //  studentInfo.setStudentID(student.getStudentId());
    studentInfo.setClassName("3A");
    studentInfo.setFirstName("Sushma");
    studentInfo.setLastName("TM");
    studentInfo.setNationality("Indian");
    ResponseEntity<StudentInfo> responseEntity = studentApiController.getStudentId(2);
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(404);
  }

  @Test
  public void Should_Create_Student_With_ValidInputs() throws StudentException {
    studentApiController.setModelMapper(new ModelMapper());
    studentApiController.setStudentService(studentService);
    Student s = new Student();
    s.setStudentId(1);
    s.setClassName("3A");
    s.setFirstName("Sushma");
    s.setLastName("TM");
    s.setNationality("Indian");
    when(studentService.save(any(Student.class))).thenReturn(s);
    StudentInfo student = new StudentInfo();
    student.setClassName("3A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    ResponseEntity<StudentInfo> responseEntity = studentApiController.addStudent(student);
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
  }

  @Test
  public void Should_Update_Student_With_New_ClassName() throws StudentNotFoundException {
    studentApiController.setModelMapper(new ModelMapper());
    studentApiController.setStudentService(studentService);
    Student student = new Student();
    student.setStudentId(1);
    student.setClassName("4A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    Student updated = new Student();
    updated.setStudentId(1);
    updated.setClassName("5A");
    updated.setFirstName("Sushma");
    updated.setLastName("TM");
    updated.setNationality("Indian");
    when(studentService.update(any(Student.class))).thenReturn(updated);
    StudentInfo studentInfo = new StudentInfo();
    studentInfo.setClassName("3A");
    studentInfo.setFirstName("Sushma");
    studentInfo.setLastName("TM");
    studentInfo.setNationality("Indian");
    ResponseEntity<StudentInfo> responseEntity =
        studentApiController.updateStudentDetails(studentInfo);
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
  }

  @Test
  public void Should_Delete_Student_When_StudentID_Exists() throws StudentException {
    studentApiController.setModelMapper(new ModelMapper());
    studentApiController.setStudentService(studentService);
    Student student = new Student();
    student.setStudentId(1);
    student.setClassName("4A");
    student.setFirstName("Sushma");
    student.setLastName("TM");
    student.setNationality("Indian");
    studentService.delete(1);
    StudentInfo studentInfo = new StudentInfo();
    studentInfo.setStudentID(1);
    studentInfo.setClassName("3A");
    studentInfo.setFirstName("Sushma");
    studentInfo.setLastName("TM");
    studentInfo.setNationality("Indian");
    ResponseEntity<Void> responseEntity = studentApiController.deleteStudent(1);
    assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
  }
}
