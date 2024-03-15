package com.study.service;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface StudentService {

    UUID createStudent(String firstName, String lastName, String faculty, String group,
                       String specialty, List<Subject> compulsorySubjects);

    List<Subject> addStudentToCourse(int studentId, Subject subject);

    Student viewAllSubjects(int studentId);

    HashMap<Subject, List<Integer>> viewAllGrades(int studentId);

    Double averageGradeOfSubject(int studentId, String subjectName);

    List<Student> viewAllStudents();
}
