package com.study.controller;

import com.study.dao.core.CourseType;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import com.study.service.StudentService;
import com.study.service.exception.IncorrectIdException;
import com.study.service.handler.ErrorHandler;
import com.study.service.handler.ErrorHandlerSingleton;
import com.study.service.impl.StudentServiceImpl;
import com.study.service.validation.IdValidator;
import java.util.*;

public class StudentController implements IdValidator {
    private ErrorHandler errorHandlerChain;
    private StudentService studentService;

    public StudentController(StudentList students) {
        studentService = new StudentServiceImpl(students);
        this.errorHandlerChain = ErrorHandlerSingleton.getInstance();
    }

    public void addStudent(String firstName, String lastName, String faculty, String group,
                           String specialty, List<Subject> compulsorySubjects) {
        UUID studentUuid = studentService.createStudent(firstName, lastName, faculty, group, specialty, compulsorySubjects);
        System.out.println(studentUuid);
    }

    public void addStudentToCourse(int studentId, Subject subject) {

        try {
            validate(studentId);

            List<Subject> subjects = studentService.addCourseToTheStudent(studentId, subject);
            for (Subject value : subjects) {
                System.out.println(value);
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAllSubjectList(int studentId) {
        try {
            validate(studentId);

            Map<CourseType, List<Subject>> courses = studentService.getAllSubjects(studentId);

            System.out.println("Subjects of the student's choice");
            courses.get(CourseType.OPTIONAL).forEach(System.out::println);

            System.out.println("Compulsory subject");
            courses.get(CourseType.COMPULSORY).forEach(System.out::println);
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAllGrades(int studentId) {
        try {
            validate(studentId);

            Map<Subject, List<Integer>> gradesForSubjects = studentService.viewAllGrades(studentId);
            gradesForSubjects.forEach((key, value) -> {
                System.out.println("Subject: " + key.getName());
                value.forEach(grade -> System.out.println("Grade: " + grade));
            });
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAverageGrade(int studentId, String subject) {
        try {
            validate(studentId);

            Double averageGrade = studentService.calculateAverageGradeOfSubject(studentId, subject);
            System.out.println(averageGrade);

        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAllStudentList() {
        try {
            List<Student> students = studentService.viewAllStudents();
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void validate(int id) {
        if (id <= 0) {
            throw new IncorrectIdException("Id cannot be less than 1");
        }
    }

    private void handleException(Exception exception) {
        errorHandlerChain.handleRequest(exception);
    }
}
