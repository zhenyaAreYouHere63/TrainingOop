package com.study.controller;

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
            validateId(studentId);

            List<Subject> subjects = studentService.addStudentToCourse(studentId, subject);
            for (Subject value : subjects) {
                System.out.println(value);
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAllSubjectList(int studentId) {
        try {
            validateId(studentId);

            Student student = studentService.viewAllSubjects(studentId);

            List<Subject> optionalSubjects = student.getOptionalSubjects();
            List<Subject> compulsorySubjects = student.getCompulsorySubjects();

            System.out.println("Subjects of the student's choice");
            for (Subject subject : optionalSubjects) {
                System.out.println(subject);
            }

            System.out.println("Compulsory subject");
            for (Subject subject : compulsorySubjects) {
                System.out.println(subject);
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAllGrades(int studentId) {
        try {
            validateId(studentId);

            HashMap<Subject, List<Integer>> gradesForSubject = studentService.viewAllGrades(studentId);
            for (Map.Entry<Subject, List<Integer>> entry : gradesForSubject.entrySet()) {
                String subject = entry.getKey().getName();
                List<Integer> grades = entry.getValue();

                System.out.println("Subject: " + subject);

                for (Integer grade : grades) {
                    System.out.println("Grade: " + grade);
                }
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAverageGrade(int studentId, String subject) {
        try {
            validateId(studentId);

            Double averageGrade = studentService.averageGradeOfSubject(studentId, subject);
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

    public void validateId(int id) {
        if (id <= 0) {
            throw new IncorrectIdException("Id cannot be less than 1");
        }
    }

    private void handleException(Exception exception) {
        errorHandlerChain.handleRequest(exception);
    }
}
