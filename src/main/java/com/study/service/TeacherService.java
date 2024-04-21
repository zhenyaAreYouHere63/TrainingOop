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

    List<Student> viewEnrolledStudents(String id);

    UUID removeTeacher(String teacherId);

    HashMap<Subject, List<Integer>> evaluateStudent(String teacherId, String studentId, List<Integer> grades);

    List<Teacher> viewTeachers();

    Teacher assignTeacherToGroup(String teacherId, String group);

    Teacher removeTeacherFromGroup(String teacherId, String group);

    Set<Teacher> getTeacherByGroup(String group);
}
