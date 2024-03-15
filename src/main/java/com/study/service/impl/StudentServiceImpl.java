package com.study.service.impl;

import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import com.study.dao.core.Student;
import com.study.service.StudentService;
import com.study.service.exception.MaxSubjectException;
import com.study.service.exception.NotFoundException;
import com.study.service.exception.RepeatException;
import com.study.service.handler.ErrorHandler;
import com.study.service.handler.ErrorHandlerSingleton;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.IntSummaryStatistics;

public class StudentServiceImpl implements StudentService {

    private ErrorHandler errorHandlerChain;
    private StudentList students;

    public StudentServiceImpl(StudentList students) {
        this.students = students;
        this.errorHandlerChain = ErrorHandlerSingleton.getInstance();
    }

    @Override
    public UUID createStudent(String firstName, String lastName, String faculty, String group,
                              String specialty, List<Subject> compulsorySubjects) {

        Student newStudent = new Student(firstName, lastName, faculty, specialty, group, compulsorySubjects);
        students.studentList.add(newStudent);

        return newStudent.getUuid();
    }

    @Override
    public List<Subject> addStudentToCourse(int studentId, Subject subject) {

        Optional<Student> optionalStudent = students.findStudentById(studentId);

        Student foundStudent = optionalStudent.orElseThrow(() ->
                new NotFoundException("Student with id " + studentId + " not found"));

        List<Subject> compulsorySubjects = foundStudent.getCompulsorySubjects();

        if (foundStudent.getOptionalSubjects().size() >= 3) {
            throw new MaxSubjectException("Sorry, the number of subjects has been exceeded");
        }

        if (compulsorySubjects.contains(subject)) {
            throw new RepeatException("Student with id " + studentId + " is already studying this subject");
        }

        foundStudent.getOptionalSubjects().add(subject);

        return foundStudent.getOptionalSubjects();
    }

    @Override
    public Student viewAllSubjects(int studentId) {

        Optional<Student> optionalStudent = students.findStudentById(studentId);

        return optionalStudent.orElseThrow(() ->
                new NotFoundException("Student with id " + studentId + " not found"));
    }


    @Override
    public HashMap<Subject, List<Integer>> viewAllGrades(int studentId) {

        Optional<Student> optionalStudent = students.findStudentById(studentId);

        return optionalStudent.map(Student::getGrades)
                .orElseGet(() -> {
                    System.out.println("Sorry, student with id " + studentId + " not found");
                    return new HashMap<>();
                });
    }

    @Override
    public Double averageGradeOfSubject(int studentId, String subject) {

        Optional<Student> optionalStudent = students.findStudentById(studentId);

        if (optionalStudent.isPresent()) {
            Student foundStudent = optionalStudent.get();

            Map<Subject, List<Integer>> studentGrades = foundStudent.getGrades();

            IntSummaryStatistics gradesSummaryStatistics = studentGrades.entrySet().stream()
                    .filter(entry -> entry.getKey().getName().equals(subject))
                    .flatMapToInt(entry -> entry.getValue().stream().mapToInt(Integer::intValue))
                    .summaryStatistics();

            return gradesSummaryStatistics.getAverage();

        } else {
            System.out.println("Sorry, student with id " + studentId + " not found");
        }
        return 0.0;
    }

    @Override
    public List<Student> viewAllStudents() {
        return students.studentList;
    }
}
