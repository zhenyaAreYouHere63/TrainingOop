package com.study.service;

import com.study.dao.program.SubjectName;

public interface TeacherService {

    void createNewTeacher(String firstName, String lastName, SubjectName subjectName);

    void viewEnrolledStudents(int id, SubjectName subjectName);

    void viewTeachers();

    void gradeStudent(int studentId, SubjectName subject, int grade);
}
