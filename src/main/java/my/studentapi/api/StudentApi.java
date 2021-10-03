/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.27).
 * https://github.com/swagger-api/swagger-codegen Do not edit the class manually.
 */
package my.studentapi.api;

import my.studentapi.model.StudentInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@javax.annotation.Generated(
    value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
    date = "2021-09-19T07:15:57.469Z[GMT]")
@Validated
public interface StudentApi {

  @Operation(
      summary = "adds new student",
      description = "Adds an item to the system",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "201", description = "item created"),
        @ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
        @ApiResponse(responseCode = "409", description = "an existing item already exists")
      })
  @RequestMapping(
      value = "/student",
      consumes = {"application/json"},
      method = RequestMethod.POST)
  ResponseEntity<StudentInfo> addStudent(
      @Parameter(in = ParameterIn.DEFAULT, description = "Student added", schema = @Schema())
          @Valid
          @RequestBody
          StudentInfo body)
      throws StudentException;

  @Operation(
      summary = "delete student",
      description = "Adds an item to the system",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "delete successfully"),
        @ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
        @ApiResponse(responseCode = "420", description = "item does not exists")
      })
  @RequestMapping(value = "/student", method = RequestMethod.DELETE)
  ResponseEntity<Void> deleteStudent(
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
          Integer studentID)
      throws StudentNotFoundException, StudentException;

  @Operation(
      summary = "searches Student info",
      description = "get the student info",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "search results matching criteria",
            content =
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = StudentInfo.class)))),
        @ApiResponse(responseCode = "400", description = "bad input parameter")
      })
  @RequestMapping(
      value = "/student",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<List<StudentInfo>> getStudentByClass(
      @NotNull
          @Size(max = 20)
          @Parameter(
              in = ParameterIn.QUERY,
              description = "search for student info",
              required = true,
              schema = @Schema())
          @Valid
          @RequestParam(value = "className", required = true)
          String className);

  @Operation(
      summary = "searches Student info",
      description = "get the student info",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "search results matching criteria",
            content =
                @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = StudentInfo.class)))),
        @ApiResponse(responseCode = "400", description = "bad input parameter")
      })
  @RequestMapping(
      value = "/student/{studentID}",
      produces = {"application/json"},
      method = RequestMethod.GET)
  ResponseEntity<StudentInfo> getStudentId(
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
          Integer studentID)
      throws StudentException;

  @Operation(
      summary = "update student details",
      description = "Adds an item to the system",
      tags = {})
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Updated successfully"),
        @ApiResponse(responseCode = "400", description = "invalid input, object invalid"),
        @ApiResponse(responseCode = "409", description = "an existing item already exists")
      })
  @RequestMapping(
      value = "/student",
      consumes = {"application/json"},
      method = RequestMethod.PUT)
  ResponseEntity<StudentInfo> updateStudentDetails(
      @Parameter(in = ParameterIn.DEFAULT, description = "Student info updated", schema = @Schema())
          @Valid
          @RequestBody
          StudentInfo body)
      throws StudentNotFoundException;
}