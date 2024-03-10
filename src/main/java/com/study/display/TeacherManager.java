package com.study.display;

import com.study.dao.data.StudentList;
import com.study.input.ClientTeacherInput;
import java.util.Scanner;

public class TeacherManager {

    private Scanner scanner;

    private ClientTeacherInput teacherInput;

    public TeacherManager(Scanner scanner, StudentList students) {
        this.scanner = scanner;
        this.teacherInput = new ClientTeacherInput(scanner, students);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("_______________");
            System.out.println("1 Create new teacher");
            System.out.println("2 View all teachers");
            System.out.println("3 Who will attend the subject?");
            System.out.println("4 Grade of student");
            System.out.println("5 Add teacher to group");
            System.out.println("6 Get teacher by group");
            System.out.println("0 Exit");
            System.out.println("_______________");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    teacherInput.createNewTeacherWithInput();
                    break;
                case 2:
                    teacherInput.viewAllTeachers();
                    break;
                case 3:
                    teacherInput.viewAllStudentsEnrolledInSubject();
                    break;
                case 4:
                    teacherInput.gradeStudent();
                    break;
                case 5:
                    teacherInput.addTeacherToGroup();
                    break;
                case 6:
                    teacherInput.getTeacherByGroup();
                    break;
                case 0:
                    System.out.println("Returning to main menu");
                    return;
            }
        }
    }
}
