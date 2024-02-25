package com.study.service;

import com.study.dao.pojo.Student;
import com.study.dao.program.SubjectName;

public interface TeacherService {

    void createNewTeacher(String firstName, String lastName, SubjectName subjectName);

    void viewEnrolledStudents(SubjectName subjectName);

    void gradeStudent(Student student, SubjectName subjectName, int grade);
}
