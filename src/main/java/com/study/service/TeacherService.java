package com.study.service;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public interface TeacherService {

    UUID createNewTeacher(String firstName, String lastName, Subject subject);

    List<Student> viewEnrolledStudents(int id);

    HashMap<Subject, List<Integer>> evaluateStudent(int studentId, String subject, List<Integer> grades);

    List<Teacher> viewTeachers();

    Teacher assignTeacherToGroup(int teacherId, String group);

    Teacher getTeacherByGroup(String subjectName, String group);
}
