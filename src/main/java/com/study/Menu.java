package com.study;

import com.study.dao.data.StudentList;
import com.study.mapper.StudentMapper;
import com.study.mapper.StudentMapperImpl;
import com.study.mapper.TeacherMapper;
import com.study.mapper.TeacherMapperImpl;
import com.study.operation.StudentOperations;
import com.study.operation.TeacherOperations;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentList students = new StudentList();
        StudentMapper studentMapper = new StudentMapperImpl();
        TeacherMapper teacherMapper = new TeacherMapperImpl();
        StudentOperations studentOperations = new StudentOperations(scanner, students, studentMapper);
        TeacherOperations teacherOperations = new TeacherOperations(scanner, students, teacherMapper);

        while (true) {
            System.out.println("_______________");
            System.out.println("StudentDto or Teacher?");
            System.out.println("Enter 1 for student, 2 for teacher, 0 to exit");
            System.out.println("_______________");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> studentOperations.displayMenu();
                case 2 -> teacherOperations.displayMenu();
                case 0 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please enter 1, 2 or 0");
            }
        }
    }
}
