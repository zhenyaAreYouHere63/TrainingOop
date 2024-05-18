package com.study.operation;

import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.input.ClientStudentInput;
import com.study.mapper.StudentMapper;
import java.util.Scanner;

public class StudentOperations {
    private Scanner scanner;
    private StudentList students;
    private ClientStudentInput studentInput;

    public StudentOperations(Scanner scanner, StudentList students, StudentMapper studentMapper, GroupList groups) {
        this.scanner = scanner;
        this.students = students;
        studentInput = new ClientStudentInput(scanner, students, studentMapper, groups);
    }

    public void displayMenu() {
        while (true) {
            printOptionsToChoose();

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1 -> studentInput.createStudent();
                case 2 -> studentInput.viewAllStudents();
                case 3 -> studentInput.addToCourse();
                case 4 -> studentInput.viewAllSubject();
                case 5 -> studentInput.viewAllGrades();
                case 6 -> studentInput.getAverageGrade();
                case 7 -> studentInput.deleteStudent();
                case 0 -> {
                    System.out.println("Returning to main menu");
                    return;
                }
                default -> System.out.println("Invalid option. Please enter 1-7 or 0");
            }
        }
    }

    private void printOptionsToChoose() {
        System.out.println("_______________");
        System.out.println("1 Create new student");
        System.out.println("2 View all students");
        System.out.println("3 Add to course");
        System.out.println("4 View all list of subjects");
        System.out.println("5 View all the grades");
        System.out.println("6 Get the average score in the subject");
        System.out.println("7 Remove student");
        System.out.println("0 Exit");
        System.out.println("_______________");
    }
}
