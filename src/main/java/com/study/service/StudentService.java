package com.study.service;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dto.StudentDto;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface StudentService {

    UUID createStudent(StudentDto studentDto);

    UUID deleteStudent(int studentId);

    Set<Subject> addStudentToCourse(int studentId, Subject subject);

    Set<Subject> viewAllSubjects(int studentId);

    HashMap<Subject, List<Integer>> viewAllGrades(int studentId);

    Double averageGradeOfSubject(int studentId, String subjectName);

    List<Student> viewAllStudents();
}
