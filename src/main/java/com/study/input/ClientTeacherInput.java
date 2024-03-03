package com.study.input;

import com.study.dao.program.SubjectName;
import com.study.service.TeacherService;
import com.study.service.impl.TeacherServiceImpl;
import java.util.Scanner;

public class ClientTeacherInput {
    private Scanner scanner;
    private TeacherService teacherService;

    public ClientTeacherInput(Scanner scanner) {
        this.scanner = scanner;
        this.teacherService = new TeacherServiceImpl();
    }

    public void createNewTeacherWithInput() {
        System.out.println("Enter username");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastname");
        String lastName = scanner.nextLine();

        System.out.println("Enter your subject");
        SubjectName subject = SubjectName.valueOf(scanner.nextLine());

        teacherService.createNewTeacher(firstName, lastName, subject);
    }

    public void viewAllStudentsEnrolledInSubject() {
        System.out.println("Enter id");
        int teacherId = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter subject");
        SubjectName subject = SubjectName.valueOf(scanner.nextLine());

        teacherService.viewEnrolledStudents(teacherId, subject);
     }

     public void viewAllTeachers() {
        teacherService.viewTeachers();
     }

     public void gradeStudent() {
         System.out.println("Enter studentId");
         int idOfStudent = scanner.nextInt();

         scanner.nextLine();

         System.out.println("Enter subject for evaluation");
         SubjectName subjectName = SubjectName.valueOf(scanner.nextLine());

         System.out.println("Enter grade of subject");
         int gradeOfSubject = scanner.nextInt();

         scanner.nextLine();

         teacherService.gradeStudent(idOfStudent, subjectName, gradeOfSubject);
     }
}
