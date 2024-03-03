package com.study;

import com.study.dao.collections.StudentList;
import com.study.display.StudentMenu;
import com.study.display.TeacherMenu;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentList students = new StudentList();
        StudentMenu studentMenu = new StudentMenu(scanner, students);
        TeacherMenu teacherMenu = new TeacherMenu(scanner);

        while (true) {
            System.out.println("_______________");
            System.out.println("Student or Teacher?");
            System.out.println("Enter 1 for student, 2 for teacher, 0 to exit");
            System.out.println("_______________");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    studentMenu.displayMenu();
                    break;
                case 2:
                    teacherMenu.displayMenu();
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
