package com.study.operation;

import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.input.ClientTeacherInput;
import com.study.mapper.TeacherMapper;
import java.util.Scanner;

public class TeacherOperations {

    private Scanner scanner;

    private ClientTeacherInput teacherInput;

    public TeacherOperations(Scanner scanner, StudentList students, TeacherMapper teacherMapper, GroupList groups, TeacherList teachers) {
        this.scanner = scanner;
        this.teacherInput = new ClientTeacherInput(scanner, students, teacherMapper, groups, teachers);
    }

    public void displayMenu() {
        while (true) {
            printOptionsToChoose();

            int menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {
                case 1 -> teacherInput.createTeacher();
                case 2 -> teacherInput.viewAllTeachers();
                case 3 -> teacherInput.viewAllStudentsAttachedInSubject();
                case 4 -> teacherInput.gradeStudent();
                case 5 -> teacherInput.addToGroup();
                case 6 -> teacherInput.getByGroup();
                case 7 -> teacherInput.removeFromGroup();
                case 8 -> teacherInput.deleteTeacher();
                case 0 -> {
                    System.out.println("Returning to main menu");
                    return;
                }
                default -> System.out.println("Invalid option. Please enter 1-8 or 0");
            }
        }
    }

    private void printOptionsToChoose() {
        System.out.println("_______________");
        System.out.println("1 Create new teacher");
        System.out.println("2 View all teachers");
        System.out.println("3 Who will attend the subject?");
        System.out.println("4 Grade of student");
        System.out.println("5 Add to group");
        System.out.println("6 Get teachers by group");
        System.out.println("7 Remove from group");
        System.out.println("8 Delete teacher");
        System.out.println("0 Exit");
        System.out.println("_______________");
    }
}
