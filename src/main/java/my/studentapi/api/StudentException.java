package my.studentapi.api;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-09-13T13:27:50.449Z[GMT]")
public class StudentException extends Exception {
  private int code;

  public StudentException(int code, String msg) {
    super(msg);
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
