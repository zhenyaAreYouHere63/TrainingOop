package com.study.display;

import com.study.dao.data.StudentList;
import com.study.input.ClientStudentInput;
import java.util.Scanner;

public class StudentManager {
    private Scanner scanner;
    private StudentList students;
    private ClientStudentInput studentInput;

    public StudentManager(Scanner scanner, StudentList students) {
        this.scanner = scanner;
        this.students = students;
        studentInput = new ClientStudentInput(scanner, students);
    }

    //TODO: rename
    public void displayMenu() {
        while (true) {
            printOptionsToChoose();

            int menu = scanner.nextInt();
            scanner.nextLine();

            //TODO: add default
            switch (menu) {
                case 1 -> studentInput.createNewStudentWithInput();
                case 2 -> studentInput.viewAllStudents();
                case 3 -> studentInput.addStudentToCourse();
                case 4 -> studentInput.viewAllSubject();
                case 5 -> studentInput.viewAllGrades();
                case 6 -> studentInput.getAverageGradeOfStudent();
                case 0 -> {
                    System.out.println("Returning to main menu");
                    return;
                }
            }
        }
    }

    private static void printOptionsToChoose() {
        System.out.println("_______________");
        System.out.println("1 Create new student");
        System.out.println("2 View all students");
        System.out.println("3 Add student to course");
        System.out.println("4 View all list of subjects");
        System.out.println("5 View all the grades");
        System.out.println("6 Get the average score in the subject");
        System.out.println("0 Exit");
        System.out.println("_______________");
    }
}
