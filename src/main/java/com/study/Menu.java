package com.study;

import com.study.input.ClientStudentInput;
import com.study.input.ClientTeacherInput;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClientStudentInput studentInput = new ClientStudentInput(scanner);
        ClientTeacherInput teacherInput = new ClientTeacherInput(scanner);

        while (true) {
            System.out.println("_______________");
            System.out.println("Student or Teacher?");
            System.out.println("Enter the number for info to student");
            System.out.println("1 Create new Student");
            System.out.println("2 Add Student to course");
            System.out.println("3 View all list of subjects");
            System.out.println("4 View all the grades");
            System.out.println("5 Get the average score in the subject");
            System.out.println();
            System.out.println();
            System.out.println("Enter the number for info to teacher");
            System.out.println("6 Create new Teacher");
            System.out.println("7 Who will attend the subject?");
            System.out.println("8 Give grades to students");
            System.out.println("0 Exit");
            System.out.println("_______________");

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1:
                    studentInput.createNewStudentWithInput();
                    break;
                case 2:
                    studentInput.addStudentToCourse();
                    break;
                case 3:
                    studentInput.viewAllSubject();
                    break;
                case 4:
                    studentInput.viewAllGrades();
                    break;
                case 5:
                    studentInput.getAverageGradeOfStudent();
                    break;
                case 6:
                    teacherInput.createNewTeacherWithInput();
            }
        }
    }
}
