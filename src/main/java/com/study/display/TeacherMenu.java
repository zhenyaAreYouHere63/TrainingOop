package com.study.display;

import com.study.input.ClientTeacherInput;
import java.util.Scanner;

public class TeacherMenu {

    private Scanner scanner;

    private ClientTeacherInput teacherInput;

    public TeacherMenu(Scanner scanner) {
        this.scanner = scanner;
        this.teacherInput = new ClientTeacherInput(scanner);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("_______________");
            System.out.println("1 Create new teacher");
            System.out.println("2 Who will attend the subject?");
            System.out.println("3 View all teachers");
            System.out.println("4 Grade of student");
            System.out.println("0 Exit");
            System.out.println("_______________");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    teacherInput.createNewTeacherWithInput();
                    break;
                case 2:
                    teacherInput.viewAllStudentsEnrolledInSubject();
                    break;
                case 3:
                    teacherInput.viewAllTeachers();
                    break;
                case 4:
                    teacherInput.gradeStudent();
                    break;
                case 0:
                    System.out.println("Returning to main menu");
                    return;
            }
        }
    }
}
