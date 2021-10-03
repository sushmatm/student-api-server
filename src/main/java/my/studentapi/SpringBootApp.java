package my.studentapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableOpenApi
@Configuration
@ComponentScan(basePackages = {"my.studentapi", "my.studentapi.api", "my.studentapi.configuration"})
public class SpringBootApp implements CommandLineRunner {

  public static void main(String[] args) throws Exception {
    new SpringApplication(SpringBootApp.class).run(args);
  }

  @Override
  public void run(String... arg0) throws Exception {
    if (arg0.length > 0 && arg0[0].equals("exitcode")) {
      throw new ExitException();
    }
  }

  class ExitException extends RuntimeException implements ExitCodeGenerator {
    private static final long serialVersionUID = 1L;

    @Override
    public int getExitCode() {
      return 10;
    }
  }
}
