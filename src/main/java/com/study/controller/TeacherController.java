package com.study.controller;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dao.data.StudentList;
import com.study.service.TeacherService;
import com.study.service.exception.IncorrectIdException;
import com.study.service.handler.ErrorHandler;
import com.study.service.handler.ErrorHandlerSingleton;
import com.study.service.impl.TeacherServiceImpl;
import com.study.service.validation.IdValidator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TeacherController implements IdValidator {
    private TeacherService teacherService;

    private ErrorHandler errorHandlerChain;

    public TeacherController(StudentList students) {
        teacherService = new TeacherServiceImpl(students);
        this.errorHandlerChain = ErrorHandlerSingleton.getInstance();
    }

    public void addTeacher(String firstName, String lastName, Subject subject) {
        UUID teacherUuid = teacherService.createNewTeacher(firstName, lastName, subject);
        System.out.println(teacherUuid);
    }

    public void getAllStudents(int teacherId) {
        try {
            validateId(teacherId);

            List<Student> students = teacherService.viewEnrolledStudents(teacherId);
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void evaluateStudent(int studentId, String subject, List<Integer> newGrades) {
        try {
            validateId(studentId);

            HashMap<Subject, List<Integer>> evaluateGradesForSubject = teacherService.evaluateStudent(studentId, subject, newGrades);

            for (Map.Entry<Subject, List<Integer>> entry : evaluateGradesForSubject.entrySet()) {
                String subjectToEvaluate = entry.getKey().getName();
                List<Integer> grades = entry.getValue();

                System.out.println("Subject: " + subjectToEvaluate);

                for (Integer grade : grades) {
                    System.out.println("Grade: " + grade);
                }
            }
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getAllTeacherList() {
        List<Teacher> teachers = teacherService.viewTeachers();
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }

    public void addTeacherToGroup(int teacherId, String group) {
        try {
            validateId(teacherId);

            Teacher teacher = teacherService.assignTeacherToGroup(teacherId, group);
            System.out.println("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " successfully added to group " + group);
        } catch (Exception exception) {
            handleException(exception);
        }
    }

    public void getTeacherByGroup(String subjectName, String group) {
        Teacher teacherByGroup = teacherService.getTeacherByGroup(subjectName, group);
        System.out.println(teacherByGroup);
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
