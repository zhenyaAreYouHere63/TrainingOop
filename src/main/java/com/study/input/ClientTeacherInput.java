package com.study.input;

import com.study.controller.TeacherController;
import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientTeacherInput {
    private Scanner scanner;

    private TeacherController teacherController;

    public ClientTeacherInput(Scanner scanner, StudentList students) {
        this.scanner = scanner;
        teacherController = new TeacherController(students);
    }

    public void createNewTeacherWithInput() {
        System.out.println("Enter username");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastname");
        String lastName = scanner.nextLine();

        System.out.println("Enter your subject");
        String subjectName = scanner.nextLine();

        Subject subject = new Subject(subjectName);

        teacherController.addTeacher(firstName, lastName, subject);
    }

    public void viewAllStudentsEnrolledInSubject() {
        System.out.println("Enter id");
        int teacherId = scanner.nextInt();

        scanner.nextLine();

        teacherController.getAllStudents(teacherId);
     }

     public void viewAllTeachers() {
        teacherController.getAllTeacherList();
     }

     public void gradeStudent() {
         System.out.println("Enter studentId");
         int idOfStudent = scanner.nextInt();

         scanner.nextLine();

         System.out.println("Enter the subject");
         String subject = scanner.nextLine();

         System.out.println("Enter grade of subject or write '-1' to finish");
         List<Integer> grades = readGrades();

         scanner.nextLine();

         teacherController.evaluateStudent(idOfStudent, subject, grades);
     }

     public void addTeacherToGroup() {
         System.out.println("Enter teacherId");
         int idOfTeacher = scanner.nextInt();

         scanner.nextLine();

         System.out.println("Enter the group");
         String group = scanner.nextLine();

         teacherController.addTeacherToGroup(idOfTeacher, group);
     }

     public void getTeacherByGroup() {
         System.out.println("Enter the subject");
         String subject = scanner.nextLine();

         System.out.println("Enter the group");
         String group = scanner.nextLine();

         teacherController.getTeacherByGroup(subject, group);
     }

    private List<Integer> readGrades() {
        List<Integer> grades = new ArrayList<>();

        while (true) {
            Integer grade = scanner.nextInt();

            if (grade == -1) {
                break;
            }

            grades.add(grade);
        }
        return grades;
    }
}
