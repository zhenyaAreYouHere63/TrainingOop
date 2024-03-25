package com.study.service;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dto.TeacherDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface TeacherService {

    UUID createNewTeacher(TeacherDto teacherDto);

    List<Student> getEnrolledStudents(int id);

    UUID deleteTeacher(int teacherId);
    Map<Subject, List<Integer>> evaluateStudent(int studentId, String subject, List<Integer> grades);

    List<Teacher> getTeachers();

    Teacher assignTeacherToGroup(int teacherId, String group);

    Teacher getTeacherByGroup(String subjectName, String group);
}
