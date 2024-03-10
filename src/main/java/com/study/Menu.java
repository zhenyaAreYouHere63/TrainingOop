package com.study;

import com.study.dao.data.StudentList;
import com.study.display.StudentManager;
import com.study.display.TeacherManager;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentList students = new StudentList();
        StudentManager studentManager = new StudentManager(scanner, students);
        TeacherManager teacherManager = new TeacherManager(scanner, students);

        while (true) {
            System.out.println("_______________");
            System.out.println("Student or Teacher?");
            System.out.println("Enter 1 for student, 2 for teacher, 0 to exit");
            System.out.println("_______________");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    studentManager.displayMenu();
                    break;
                case 2:
                    teacherManager.displayMenu();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1, 2 or 0");
            }
        }
    }
}
