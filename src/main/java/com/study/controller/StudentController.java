package com.study.controller;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import com.study.service.StudentService;
import com.study.service.impl.StudentServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class StudentController {
    private StudentService studentService;

    public StudentController(StudentList students) {
        studentService = new StudentServiceImpl(students);
    }

    public void addStudent(String firstName, String lastName, String faculty, String group,
                           String specialty, List<Subject> compulsorySubjects) {
        UUID studentUuid = studentService.createStudent(firstName, lastName, faculty, group, specialty, compulsorySubjects);
        System.out.println(studentUuid);
    }

    public void addStudentToCourse(int studentId, Subject subject) {
        List<Subject> subjects = studentService.addStudentToCourse(studentId, subject);
        for(Subject value: subjects) {
            System.out.println(value);
        }
    }

    public void getAllSubjectList(int studentId) {
        studentService.viewAllSubjects(studentId);
    }

    public void getAllGrades(int studentId) {
        HashMap<Subject, List<Integer>> gradesForSubject = studentService.viewAllGrades(studentId);
        for(Map.Entry<Subject, List<Integer>> entry: gradesForSubject.entrySet()) {
            String subject = entry.getKey().getName();
            List<Integer> grades = entry.getValue();

            System.out.println("Subject: " + subject);

            for(Integer grade: grades) {
                System.out.println("Grade: " + grade);
            }
        }
    }

    public void getAverageGrade(int studentId, String subject) {
        Double averageGrade = studentService.averageGradeOfSubject(studentId, subject);
        System.out.println(averageGrade);
    }

    public void getAllStudentList() {
        List<Student> students = studentService.viewAllStudents();
        for(Student student: students) {
            System.out.println(student);
        }
    }
}
