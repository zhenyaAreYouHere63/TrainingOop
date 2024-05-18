package com.study.input;

import com.study.controller.TeacherController;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dto.SubjectDtoForTeacher;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientTeacherInput {
    private Scanner scanner;

    private TeacherController teacherController;

    public ClientTeacherInput(Scanner scanner, StudentList students, TeacherMapper teacherMapper, GroupList groups, TeacherList teachers) {
        this.scanner = scanner;
        teacherController = new TeacherController(students, teacherMapper, groups, teachers);
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
        String id = scanner.nextLine();

        teacherController.deleteTeacher(id);
    }

    public void viewAllStudentsAttachedInSubject() {
        System.out.println("Enter id");
        String id = scanner.nextLine();

        teacherController.getAllStudentsForTeacherSubject(id);
     }

     public void viewAllTeachers() {
        teacherController.getAllTeacherList();
     }

     public void gradeStudent() {
         System.out.println("Enter teacherId");
         String idOfTeacher = scanner.nextLine();

         System.out.println("Enter studentId");
         String idOfStudent = scanner.nextLine();

         System.out.println("Enter grade of subject or write '-1' to finish");
         List<Integer> grades = readGrades();

         scanner.nextLine();

         teacherController.evaluateStudent(idOfTeacher, idOfStudent, grades);
     }

     public void addToGroup() {
         System.out.println("Enter teacherId");
         String id = scanner.nextLine();

         System.out.println("Enter the group name");
         String group = scanner.nextLine();

         teacherController.addTeacherToGroup(id, group);
     }

     public void getByGroup() {
         System.out.println("Enter the group");
         String group = scanner.nextLine();

         teacherController.getTeachersByGroup(group);
     }

     public void removeFromGroup() {
         System.out.println("Enter the teacher id");
         String id = scanner.nextLine();

         System.out.println("Enter the group");
         String group = scanner.nextLine();

         teacherController.removeTeacherFromGroup(id, group);
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
