package com.study.service;

import com.study.dao.core.CourseType;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface StudentService {

    UUID createStudent(String firstName, String lastName, String faculty, String group,
                       String specialty, List<Subject> compulsorySubjects);

    List<Subject> addCourseToTheStudent(int studentId, Subject subject);

    Map<CourseType, List<Subject>> getAllSubjects(int studentId);

    Map<Subject, List<Integer>> viewAllGrades(int studentId);

    Double calculateAverageGradeOfSubject(int studentId, String subjectName);

    List<Student> viewAllStudents();
}
