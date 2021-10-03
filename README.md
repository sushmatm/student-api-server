This is a sample REST API project for evaluation.


1. Running the server

    Prerequisite:
      A new DB instance, Student needs to be created.

    Steps:
        1. git clone this repo - https://github.com/sushmatm/student-api-server.git
        2. cd to the folder
        3. run `mvn clean install`
        4. Go to target folder. Create an application properties file with the following values
        `jdbc.driverClassName=
         jdbc.url=
         jdbc.user=
         jdbc.pass=`
        5. Execute the jar. `java -jar mystudentapi-1.0.0.jar`
 
 2. To check for the coverage
     1. Run `mvn test`
     2. Open in browser `target/site/index.html`
     Note: In production application, the UT coverage will be much higher.
     
 
