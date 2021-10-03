package my.studentapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/** StudentInfo */
@Validated
@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-09-19T13:55:49.106Z[GMT]")
public class StudentInfo {
  @JsonProperty("studentID")
  private Integer studentID = null;

  @JsonProperty("firstName")
  private String firstName = null;

  @JsonProperty("lastName")
  private String lastName = null;

  @JsonProperty("className")
  private String className = null;

  @JsonProperty("nationality")
  private String nationality = null;

  public StudentInfo studentID(Integer studentID) {
    this.studentID = studentID;
    return this;
  }

  /**
   * Get studentID
   *
   * @return studentID
   */
  @Schema(example = "20", required = true, description = "")
  @NotNull
  public Integer getStudentID() {
    return studentID;
  }

  public void setStudentID(Integer studentID) {
    this.studentID = studentID;
  }

  public StudentInfo firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  /**
   * Get firstName
   *
   * @return firstName
   */
  @Schema(example = "Sushma", required = true, description = "")
  @NotNull
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public StudentInfo lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  /**
   * Get lastName
   *
   * @return lastName
   */
  @Schema(example = "Thippenahalli Mayanna", required = true, description = "")
  @NotNull
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public StudentInfo className(String className) {
    this.className = className;
    return this;
  }

  /**
   * Get className
   *
   * @return className
   */
  @Schema(example = "3 A", required = true, description = "")
  @NotNull
  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public StudentInfo nationality(String nationality) {
    this.nationality = nationality;
    return this;
  }

  /**
   * Get nationality
   *
   * @return nationality
   */
  @Schema(example = "Indian", required = true, description = "")
  @NotNull
  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    StudentInfo studentInfo = (StudentInfo) o;
    return Objects.equals(this.studentID, studentInfo.studentID)
        && Objects.equals(this.firstName, studentInfo.firstName)
        && Objects.equals(this.lastName, studentInfo.lastName)
        && Objects.equals(this.className, studentInfo.className)
        && Objects.equals(this.nationality, studentInfo.nationality);
  }

  @Override
  public int hashCode() {
    return Objects.hash(studentID, firstName, lastName, className, nationality);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class StudentInfo {\n");

    sb.append("    studentID: ").append(toIndentedString(studentID)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    nationality: ").append(toIndentedString(nationality)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
