package my.studentapi.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import my.studentapi.entity.Student;
import my.studentapi.model.StudentInfo;
import my.studentapi.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-09-13T13:27:50.449Z[GMT]")
@RestController
public class StudentApiController implements StudentApi {

  private static final Logger log = LoggerFactory.getLogger(StudentApiController.class);

  private final ObjectMapper objectMapper;

  private final HttpServletRequest request;

  @Autowired private ModelMapper modelMapper;

  @Autowired private StudentService studentService;

  @org.springframework.beans.factory.annotation.Autowired
  public StudentApiController(ObjectMapper objectMapper, HttpServletRequest request) {
    this.objectMapper = objectMapper;
    this.request = request;
  }

  void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public ResponseEntity<StudentInfo> addStudent(
      @Parameter(in = ParameterIn.DEFAULT, description = "Student added", schema = @Schema())
          @Valid
          @RequestBody
          StudentInfo body) {
    StudentInfo info;
    try {
      Student student = convertToEntity(body);
      Student result = studentService.save(student);
      info = convertToDTO(result);
    } catch (StudentException e) {
      log.error("Couldn't serialize response for content type application/json", e);
      return new ResponseEntity<StudentInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (log.isDebugEnabled())
      log.debug(
          String.format("Operation: %s, Request: %s, Response: %s", "AddOperation", body, info));
    return new ResponseEntity<StudentInfo>(info, HttpStatus.CREATED);
  }

  public ResponseEntity<Void> deleteStudent(
          @NotNull
          @Min(0)
          @Max(100)
          @Parameter(
                  in = ParameterIn.QUERY,
                  description = "delete the student",
                  required = true,
                  schema =
                  @Schema(
                          allowableValues = {},
                          maximum = "100"))
          @Valid
          @RequestParam(value = "studentID", required = true)
                  Integer studentID){
    //        String accept = request.getHeader("Accept");
    Student student;
    try {
      studentService.delete(studentID);
    } catch (StudentNotFoundException e) {
      return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<List<StudentInfo>> getStudentByClass(
      @NotNull
          @Size(max = 20)
          @Parameter(
              in = ParameterIn.QUERY,
              description = "search for student info",
              required = true,
              schema = @Schema())
          @Valid
          @RequestParam(value = "className", required = true)
          String className) {
    List<Student> entityList;

    try {
      entityList = studentService.getByClassName(className);
    } catch (StudentNotFoundException sfe) {
      return new ResponseEntity<List<StudentInfo>>(HttpStatus.NOT_FOUND);
    }
    List<StudentInfo> dtoList = new ArrayList<>(entityList.size());
    Iterator<Student> itr = entityList.iterator();
    while (itr.hasNext()) {
      dtoList.add(convertToDTO(itr.next()));
    }
    if (log.isDebugEnabled())
      log.debug(String.format("Operation:, Request:, Response: %s", "AddOperation", dtoList));
    return new ResponseEntity<List<StudentInfo>>(dtoList, HttpStatus.OK);
  }

  public ResponseEntity<StudentInfo> getStudentId(
      @Min(0)
          @Max(100)
          @Parameter(
              in = ParameterIn.PATH,
              description = "search for student info",
              required = true,
              schema =
                  @Schema(
                      allowableValues = {},
                      maximum = "100"))
          @PathVariable("studentID")
          Integer studentID) {
    Student result;
    try {
      result = studentService.getByStudentId(studentID);
    } catch (StudentNotFoundException sfe) {
      return new ResponseEntity<StudentInfo>(HttpStatus.NOT_FOUND);
    } catch (StudentException e) {
      return new ResponseEntity<StudentInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    StudentInfo info = convertToDTO(result);
    if (log.isDebugEnabled())
      log.debug(String.format("Operation:, Request:, Response: %s", "AddOperation", info));
    return new ResponseEntity<StudentInfo>(info, HttpStatus.OK);
  }

  public ResponseEntity<StudentInfo> updateStudentDetails(
      @Parameter(in = ParameterIn.DEFAULT, description = "Student info updated", schema = @Schema())
          @Valid
          @RequestBody
          StudentInfo body) {
    StudentInfo info;
    try {
      Student student = convertToEntity(body);
      Student result = studentService.update(student);
      info = convertToDTO(result);
    } catch (StudentNotFoundException e) {
      return new ResponseEntity<StudentInfo>(HttpStatus.NOT_FOUND);
    } catch (StudentException e) {
      return new ResponseEntity<StudentInfo>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    if (log.isDebugEnabled())
      log.debug(
          String.format("Operation: %s, Request: %s, Response: %s", "AddOperation", body, info));
    return new ResponseEntity<StudentInfo>(info, HttpStatus.ACCEPTED);
  }

  private Student convertToEntity(StudentInfo studentInfo) {
    Student student = modelMapper.map(studentInfo, Student.class);
    return student;
  }

  private StudentInfo convertToDTO(Student student) {
    StudentInfo studentInfo = modelMapper.map(student, StudentInfo.class);
    return studentInfo;
  }

  void setStudentService(StudentService studentService) {
    this.studentService = studentService;
  }
}
