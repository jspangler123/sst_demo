# Single Stone Demo
The Single Stone Demo application is a sample web service application which stores and retrieves basic Contact information. The application is written in Java using Spring Boot with an H2 in-memory database store.

This sample code base demonstrates base competence in the following areas of application design and development:

* Spring Boot
* RESTful API development
* JPA and database application development
* Swagger documentation
* Unit testing with the Karate framework

## Software Requirements
To run the demo, you will need the following components installed on the target platform:

* Maven [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* Tomcat 8.5 or above
* GIT
* Java 1.8 JRE

## Compiling the WAR file
To compile the demo application, first open a terminal in a directory to which you wish to clone the code base. From the terminal command line, clone the repository to the target platform using the following GIT command:

`git clone https://github.com/jspangler123/sst_demo.git`

Once the repository is cloned, change directories into the sst_demo directory created by the clone. You may use the following maven command to build the WAR file:

`mvn clean install`

Upon build success, the WAR file will be built in the *target* directory of the current project directory.

Copy the WAR file to the *webapps* directory of your Tomcat installation.

## Running the Application
The application is deployed as a WAR within Tomcat. Once the WAR is copied to the *webapps* directory, you may start your Tomcat server locally using the following command:

Windows

1. From the terminal command prompt, navigate to the Tomcat bin directory. E.g. `cd c:\Tomcat\apache-tomcat-9.0.0.M21\bin`
1. Type the command `startup.bat` and press enter

The tomcat server will start, with the console output reflecting the status of the application and server health.

## Running the Karate Test Suite
Once the application is running, the Karate suite of tests may be run from the command line through the following steps:

1. Open a separate command terminal
1. Navigate back to the *sst_demo* directory to which the code base is cloned.
1. Use the following command to start the tests:

`mvn clean test -Dtest=ContactTest`

Once the tests have finished running, the test results are compiled to an HTML file found in the *target/surefire-reports* directory of the cloned repository.


#### Note:
 If you are seeing the following exception:
```
2019-06-11T22:12:16,168 ERROR [main] c.i.k.Logger: javascript function call failed: ReferenceError: "karate" is not defined
```

Pkease ensure that your Maven JRE version of Java is 1.8_112 or newer.


## Viewing Swagger Documentation
To view the swagger documentation, use the following URL in your browser while running the service in the Tomcat installation:

`http://localhost:8080/demo/swagger-ui.html`

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)


