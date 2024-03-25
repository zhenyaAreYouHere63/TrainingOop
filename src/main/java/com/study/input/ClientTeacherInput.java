package com.study.input;

import com.study.controller.TeacherController;
import com.study.dao.data.StudentList;
import com.study.dto.SubjectDtoForTeacher;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is responsible for.../ is need for.../ serves as...
 */
public class ClientTeacherInput {
    private final Scanner scanner;

    private final TeacherController teacherController;

    public ClientTeacherInput(Scanner scanner, StudentList students, TeacherMapper teacherMapper) {
        this.scanner = scanner;
        teacherController = new TeacherController(students, teacherMapper);
    }

    public void createTeacher() {
        System.out.println("Enter username");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastname");
        String lastName = scanner.nextLine();

        System.out.println("Enter your subject");
        String subjectName = scanner.nextLine();

        SubjectDtoForTeacher subject = new SubjectDtoForTeacher(subjectName);

        teacherController.addTeacher(new TeacherDto(firstName, lastName, subject));
    }

    public void deleteTeacher() {
        System.out.println("Enter teacherId");
        int teacherId = scanner.nextInt();

        teacherController.deleteTeacher(teacherId);
    }

    public void viewAllStudentsAttachedInSubject() {
        System.out.println("Enter id");
        int teacherId = scanner.nextInt();

        scanner.nextLine();

        teacherController.getAllStudentsForTeacherSubject(teacherId);
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

     public void addToGroup() {
         System.out.println("Enter teacherId");
         int idOfTeacher = scanner.nextInt();

         scanner.nextLine();

         System.out.println("Enter the group");
         String group = scanner.nextLine();

         teacherController.addTeacherToGroup(idOfTeacher, group);
     }

     public void getByGroup() {
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
