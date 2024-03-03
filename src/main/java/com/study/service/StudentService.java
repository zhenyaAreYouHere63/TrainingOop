package com.study.service;

import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;

public interface StudentService {

    void createNewStudent(String firstName, String lastName, Faculty faculty, Group group,
                             Specialty specialty);

    void addStudentToCourse(int studentId, SubjectName subjectName);

    void viewAllSubjects(int studentId);

    void viewAllGrades(int studentId);

    double averageGradeOfSubject(int studentId, SubjectName subjectName);

    void viewAllStudents();
}
