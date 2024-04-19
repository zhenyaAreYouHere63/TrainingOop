package com.study.service;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dto.TeacherDto;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface TeacherService {

    UUID createNewTeacher(TeacherDto teacherDto);

    List<Student> viewEnrolledStudents(int id);

    UUID removeTeacher(int teacherId);

    HashMap<Subject, List<Integer>> evaluateStudent(int teacherId, int studentId, String subject, List<Integer> grades);

    List<Teacher> viewTeachers();

    Teacher assignTeacherToGroup(int teacherId, String group);

    Teacher removeTeacherFromGroup(int teacherId, String group);

    Set<Teacher> getTeacherByGroup(String group);
}
