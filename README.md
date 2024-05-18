## Student Learning Management System

Created a console application to manage the learning process of students and their results.
Each student can have a first name, last name, faculty, specialty, group, a list of subjects they attend, and
corresponding grades for those subjects.  
Students have compulsory and optional subjects.
Each subject can be taught by several teachers, but in one group, a subject is taught by only one teacher.

A student can:
- View the entire list of subjects and enroll in a maximum of 3 optional subjects.
- View the list of subjects they attend, sorted by different fields.
- View their grades and calculate their average grade.

  A teacher can:
- View who is enrolled in their subject.
- Assign grades to students.

## Requirements for launching the application

- Java 21
- Maven

## Launch the application

1) Save the application to your computer
2) Install Java 21 and Maven on your computer
3) Clean up the project using the command: mvn clean
4) To compile and build project use the command: mvn package
5) Run the program using the following command:
  java -cp target/student_management-1.0-SNAPSHOT.jar com.study.Menu
