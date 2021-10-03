package my.studentapi.api;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-09-13T13:27:50.449Z[GMT]")
public class StudentNotFoundException extends StudentException {
  private int code;

  public StudentNotFoundException(int code, String msg) {
    super(code, msg);
    this.code = code;
  }

  @Override
  public int getCode() {
    return code;
  }
}
