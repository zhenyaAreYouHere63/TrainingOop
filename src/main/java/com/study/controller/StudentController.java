package com.study.controller;

import com.study.dao.SubjectType;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dto.StudentDto;
import com.study.mapper.StudentMapper;
import com.study.service.StudentService;
import com.study.service.exception.IncorrectIdException;
import com.study.service.impl.StudentServiceImpl;
import com.study.service.validation.IdValidator;
import java.util.*;

public class StudentController implements IdValidator {
    private StudentService studentService;

    public StudentController(StudentList students, StudentMapper studentMapper, GroupList groups) {
        studentService = new StudentServiceImpl(students, studentMapper, groups);
    }

    public void addStudent(StudentDto studentDto) {
        List<Exception> maybeExceptions = StudentDto.validateStudentDto(studentDto.firstName(), studentDto.lastName(),
                studentDto.faculty(), studentDto.specialty(), studentDto.group().name(), studentDto.subjects());

        if (!maybeExceptions.isEmpty()) {
            System.out.println("Validation errors");
            for(Exception exception: maybeExceptions) {
                System.out.println(exception);;
            }
            return;
        }

        UUID studentUuid = studentService.createStudent(studentDto);
        System.out.println(studentUuid);
    }

    public void deleteStudent(int studentId) {
        UUID studentUuid = studentService.deleteStudent(studentId);
        System.out.println(studentUuid);
    }

    public void addStudentToCourse(int studentId, Subject subject) {

        validateId(studentId);

        Set<Subject> subjects = studentService.addStudentToCourse(studentId, subject);

        subjects.forEach(System.out::println);
    }

    public void getAllSubjectList(int studentId) {
        validateId(studentId);

        Set<Subject> subjectsForStudent = studentService.viewAllSubjects(studentId);

        System.out.println("Compulsory subject's");
        subjectsForStudent.stream().filter(subject -> subject.getType() == SubjectType.COMPULSORY)
                .map(Subject::getName)
                .forEach(System.out::println);

        System.out.println();

        System.out.println("Subject's of the student's choice");
        subjectsForStudent.stream().filter(subject -> subject.getType() == SubjectType.OPTIONAL)
                .map(Subject::getName)
                .forEach(System.out::println);
    }

    public void getAllGrades(int studentId) {
        validateId(studentId);

        HashMap<Subject, List<Integer>> gradesForSubject = studentService.viewAllGrades(studentId);

        gradesForSubject.forEach((key, value) -> {
            System.out.println("Subject: " + key.getName());
            value.forEach(grade -> System.out.println("Grade: " + grade));
        });
    }

    public void getAverageGrade(int studentId, String subject) {
        validateId(studentId);

        Double averageGrade = studentService.averageGradeOfSubject(studentId, subject);
        System.out.println(averageGrade);
    }

    public void getAllStudentList() {
        List<Student> students = studentService.viewAllStudents();

        students.forEach(System.out::println);
    }

    public void validateId(int id) {
        if (id <= 0) {
            throw new IncorrectIdException("Id cannot be less than 1");
        }
    }
}
