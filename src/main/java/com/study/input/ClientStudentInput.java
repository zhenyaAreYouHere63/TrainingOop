package com.study.input;

import com.study.controller.StudentController;
import com.study.dao.SubjectType;
import com.study.dao.core.Subject;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dto.GroupDto;
import com.study.dto.StudentDto;
import com.study.dto.SubjectDtoForStudent;
import com.study.mapper.StudentMapper;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;

public class ClientStudentInput {
    private Scanner scanner;
    private StudentController studentController;

    public ClientStudentInput(Scanner scanner, StudentList students, StudentMapper studentMapper, GroupList groups) {
        this.scanner = scanner;
        studentController = new StudentController(students, studentMapper, groups);
    }

    public void createStudent() {
        System.out.println("Enter username");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastname");
        String lastName = scanner.nextLine();

        System.out.println("Enter faculty");
        String faculty = scanner.nextLine();

        System.out.println("Enter specialty");
        String specialty = scanner.nextLine();

        System.out.println("Enter group");
        String group = scanner.nextLine();

        Set<SubjectDtoForStudent> subjectDtoForStudents = readSubjects();

        studentController.addStudent(new StudentDto(firstName, lastName, faculty, specialty, new GroupDto(group), subjectDtoForStudents));
    }

    public void deleteStudent() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        studentController.deleteStudent(id);
    }

    public void viewAllSubject() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        studentController.getAllSubjectList(id);
    }

    public void viewAllGrades() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        studentController.getAllGrades(id);
    }

    public void getAverageGrade() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter subject subject");
        String subject = scanner.nextLine();

        studentController.getAverageGrade(id, subject);
    }

    public void addToCourse() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter subject subject");
        String subjectName = scanner.nextLine();

        System.out.println("This subject is compulsory?");
        String isCompulsorySubjectString = scanner.nextLine();
        boolean isCompulsorySubject = isCompulsorySubjectString.equalsIgnoreCase("yes");

        Subject subject = new Subject(subjectName, isCompulsorySubject ? SubjectType.COMPULSORY : SubjectType.OPTIONAL);

        studentController.addStudentToCourse(id, subject);
    }

    public void viewAllStudents() {
        studentController.getAllStudentList();
    }

    private Set<SubjectDtoForStudent> readSubjects() {
        Set<SubjectDtoForStudent> subjects = new HashSet<>();

        while (true) {
            System.out.println("Enter a subject's or write 'done' to finish");
            String subjectName = scanner.nextLine();
            if (subjectName.equalsIgnoreCase("done")) {
                break;
            }

            System.out.println("Is this subject is compulsory? (yes/no)");
            String isCompulsorySubjectString = scanner.nextLine();

            validateInput(isCompulsorySubjectString);

            boolean isCompulsorySubject = isCompulsorySubjectString.equalsIgnoreCase("yes");

            SubjectDtoForStudent subject = new SubjectDtoForStudent(subjectName, isCompulsorySubject ? SubjectType.COMPULSORY : SubjectType.OPTIONAL);

            subjects.add(subject);
        }
        return subjects;
    }

    private void validateInput(String isCompulsorySubjectString) {
        if (!isCompulsorySubjectString.equalsIgnoreCase("yes") &&
            !isCompulsorySubjectString.equalsIgnoreCase("no")) {
            throw new IllegalArgumentException("Please, enter 'yes' or 'no'");
        }
    }
}
