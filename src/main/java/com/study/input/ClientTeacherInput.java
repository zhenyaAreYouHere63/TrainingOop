package com.study.input;

import com.study.dao.program.SubjectName;
import com.study.service.TeacherService;
import com.study.service.TeacherServiceImpl;
import java.util.Scanner;

public class ClientTeacherInput {
    private Scanner scanner;
    private TeacherService teacherService;

    public ClientTeacherInput(Scanner scanner) {
        this.scanner = scanner;
        this.teacherService = new TeacherServiceImpl();
    }

    public void createNewTeacherWithInput() {
        System.out.println("Enter username");
        String firstName = scanner.nextLine();

        System.out.println("Enter lastname");
        String lastName = scanner.nextLine();

        System.out.println("Enter your subject");
        SubjectName subject = SubjectName.valueOf(scanner.nextLine());

        teacherService.createNewTeacher(firstName, lastName, subject);
    }
}
