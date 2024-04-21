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

    UUID deleteStudent(String studentId);

    Set<Subject> addStudentToCourse(String studentId, Subject subject);

    Set<Subject> viewAllSubjects(String studentId);

    HashMap<Subject, List<Integer>> viewAllGrades(String studentId);

    Double averageGradeOfSubject(String studentId, String subjectName);

    List<Student> viewAllStudents();
}
