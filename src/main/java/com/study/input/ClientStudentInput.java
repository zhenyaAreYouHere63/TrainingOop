package com.study.input;

import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import com.study.service.StudentService;
import com.study.service.StudentServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientStudentInput {
    private Scanner scanner;
    private StudentService studentService;

    private SubjectName subjectName;

    public ClientStudentInput(Scanner scanner) {
        this.scanner = scanner;
        this.studentService = new StudentServiceImpl();
    }

    public void createNewStudentWithInput() {
        System.out.println("Enter username");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastname");
        String lastName = scanner.nextLine();

        System.out.println("Enter faculty");
        Faculty faculty = Faculty.valueOf(scanner.nextLine());

        System.out.println("Enter specialty");
        Specialty specialty = Specialty.valueOf(scanner.nextLine());

        System.out.println("Enter group");
        Group group = Group.valueOf(scanner.nextLine());

        System.out.println("Enter subject");
        List<SubjectName> subjects = new ArrayList<>(List.of(SubjectName.valueOf(scanner.nextLine()),
                SubjectName.valueOf(scanner.nextLine()), SubjectName.valueOf(scanner.nextLine())));

        studentService.createNewStudent(firstName, lastName, faculty, group, specialty, subjects);
    }

    public void viewAllSubject() {

        System.out.println("Enter id of student");
        int id = scanner.nextInt();
        studentService.viewAllSubjects(id);

    }

    public void viewAllGrades() {
        studentService.viewAllGrades();
    }

    public void getAverageGradeOfStudent() {
        studentService.averageGradeOfSubject();
    }

    public void addStudentToCourse() {
        studentService.addStudentToCourse(subjectName);
    }
}
