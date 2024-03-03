package com.study.input;

import com.study.dao.collections.StudentList;
import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import com.study.service.StudentService;
import com.study.service.impl.StudentServiceImpl;
import java.util.Scanner;

public class ClientStudentInput {
    private StudentList students;
    private Scanner scanner;
    private StudentService studentService;
    private SubjectName subjectName;
    public ClientStudentInput(Scanner scanner, StudentList students) {
        this.scanner = scanner;
        this.students = students;
        this.studentService = new StudentServiceImpl(students);
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

        if (!specialty.isMatchingFaculty(faculty)) {
            System.out.println("Mistake: the specialty does not correspond to the faculty");
            return;
        }

        System.out.println("Enter group");
        Group group = Group.valueOf(scanner.nextLine());

        if (!group.isMatchingGroup(specialty)) {
            System.out.println("Mistake: the group does not correspond to the specialty");
            return;
        }

        studentService.createNewStudent(firstName, lastName, faculty, group, specialty);
    }

    public void viewAllSubject() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();
        studentService.viewAllSubjects(id);

    }

    public void viewAllGrades() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        studentService.viewAllGrades(id);
    }

    public void getAverageGradeOfStudent() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter subject name");
        SubjectName subjectName = SubjectName.valueOf(scanner.nextLine());

        double averageGradeOfSubject = studentService.averageGradeOfSubject(id, subjectName);
        System.out.println("Average grade of subject " + subjectName + ": " + averageGradeOfSubject);
    }

    public void addStudentToCourse() {
        System.out.println("Enter id of student");
        int id = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Enter subject name");
        SubjectName subjectName = SubjectName.valueOf(scanner.nextLine());

        studentService.addStudentToCourse(id, subjectName);
    }

    public void viewAllStudents() {
        studentService.viewAllStudents();
    }
}
