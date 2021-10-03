package my.studentapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
public class Student {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int studentId;

  @Column private String firstName;
  @Column private String lastName;
  @Column private String nationality;
  @Column private String className;
  @Column private String statuscode;

  public int getStudentId() {
    return studentId;
  }

  public void setStudentId(int studentId) {
    this.studentId = studentId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getStatuscode() {
    return statuscode;
  }

  public void setStatuscode(String statuscode) {
    this.statuscode = statuscode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Student student = (Student) o;
    return studentId == student.studentId
        && Objects.equals(firstName, student.firstName)
        && Objects.equals(lastName, student.lastName)
        && Objects.equals(nationality, student.nationality)
        && Objects.equals(className, student.className)
        && Objects.equals(statuscode, student.statuscode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentId, firstName, lastName, nationality, className, statuscode);
  }
}
