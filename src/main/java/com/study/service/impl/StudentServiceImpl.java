package com.study.service.impl;

import com.study.dao.core.CourseType;
import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import com.study.dao.core.Student;
import com.study.service.StudentService;
import com.study.service.exception.MaxSubjectException;
import com.study.service.exception.NotFoundException;
import com.study.service.exception.RepeatException;
import com.study.service.handler.ErrorHandler;
import com.study.service.handler.ErrorHandlerSingleton;

import java.util.*;

public class StudentServiceImpl implements StudentService {

//    private ErrorHandler errorHandlerChain;
    private StudentList students;

    public StudentServiceImpl(StudentList students) {
        this.students = students;
//        this.errorHandlerChain = ErrorHandlerSingleton.getInstance();
    }

    //TODO: add validations for all fields
    @Override
    public UUID createStudent(String firstName, String lastName, String faculty, String group,
                              String specialty, List<Subject> compulsorySubjects) {

        Student newStudent = new Student(firstName, lastName, faculty, specialty, group, compulsorySubjects);
        students.addStudent(newStudent);

        return newStudent.getUuid();
    }


    //TODO: add boolean var - isCompulsory
    @Override
    public List<Subject> addCourseToTheStudent(int studentId, Subject subject) {

        Student foundStudent = students.findStudentById(studentId);

        List<Subject> compulsorySubjects = foundStudent.getCompulsorySubjects();

        if (compulsorySubjects.contains(subject)) {
            throw new RepeatException("Student with id " + studentId + " is already studying this subject");
        }

        if (foundStudent.getOptionalSubjects().size() >= 3) {
            throw new MaxSubjectException("Sorry, the number of subjects has been exceeded");
        }

        foundStudent.addOptionalSubject(subject);

        return foundStudent.getOptionalSubjects();
    }

    @Override
    public Map<CourseType, List<Subject>> getAllSubjects(int studentId) {
        Student student = students.findStudentById(studentId);
        return student.getSubjects();
    }


    @Override
    public Map<Subject, List<Integer>> viewAllGrades(int studentId) {
        Student student = students.findStudentById(studentId);
        return student.getGrades();
    }

    @Override
    public Double calculateAverageGradeOfSubject(int studentId, String subject) {
        Student foundStudent = students.findStudentById(studentId);

        Map<Subject, List<Integer>> studentGrades = foundStudent.getGrades();

        IntSummaryStatistics gradesSummaryStatistics = studentGrades.entrySet().stream()
                .filter(entry -> entry.getKey().getName().equals(subject))
                .flatMapToInt(entry -> entry.getValue().stream().mapToInt(Integer::intValue))
                .summaryStatistics();

        return gradesSummaryStatistics.getAverage();
    }

    @Override
    public List<Student> viewAllStudents() {
        return students.getStudentList();
    }
}
