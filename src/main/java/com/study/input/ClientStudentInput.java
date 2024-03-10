package com.study.input;

import com.study.controller.StudentController;
import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientStudentInput {
    private Scanner scanner;
    private StudentController studentController;
    public ClientStudentInput(Scanner scanner, StudentList students) {
        this.scanner = scanner;
        studentController = new StudentController(students);
    }

    public void createNewStudentWithInput() {
        System.out.println("Enter username");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastname");
        String lastName = scanner.nextLine();

        System.out.println("Enter faculty");
        String faculty = scanner.nextLine();

        System.out.println("Enter specialty");
        String specialty = scanner.nextLine();

        System.out.println("Enter group");
        String group = scanner.nextLine();

        System.out.println("Enter a compulsory subject or write 'done' to finish");
        List<Subject> readCompulsorySubjects = readSubjects();

        studentController.addStudent(firstName, lastName, faculty, group, specialty, readCompulsorySubjects);
    }

    public void viewAllSubject() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        studentController.getAllSubjectList(id);
    }

    public void viewAllGrades() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        studentController.getAllGrades(id);
    }

    public void getAverageGradeOfStudent() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter subject name");
        String subject = scanner.nextLine();

        studentController.getAverageGrade(id, subject);
    }

    public void addStudentToCourse() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter subject name");
        String subjectName = scanner.nextLine();

        Subject subject = new Subject(subjectName);

        studentController.addStudentToCourse(id, subject);
    }

    public void viewAllStudents() {
        studentController.getAllStudentList();
    }

    private List<Subject> readSubjects() {
        List<Subject> subjects = new ArrayList<>();

        while (true) {
            String subjectName = scanner.nextLine();

            if (subjectName.equals("done")) {
                break;
            }

            Subject subject = new Subject(subjectName);
            subjects.add(subject);
        }
        return subjects;
    }
}
