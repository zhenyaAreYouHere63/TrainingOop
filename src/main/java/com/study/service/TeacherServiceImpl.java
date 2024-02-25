package com.study.service;

import com.study.dao.collections.TeacherMap;
import com.study.dao.pojo.Student;
import com.study.dao.pojo.Teacher;
import com.study.dao.program.SubjectName;

public class TeacherServiceImpl implements TeacherService {

    private final Teacher teacher;

    private final TeacherMap teacherMap;

    public TeacherServiceImpl() {
        this.teacher = new Teacher();
        this.teacherMap = new TeacherMap();
    }


    @Override
    public void createNewTeacher(String firstName, String lastName, SubjectName subjectName) {
        int key = 1;
        var newTeacher = new Teacher(firstName, lastName, subjectName);
        teacherMap.teachers.put(key, newTeacher);
        key += 1;
        System.out.println(newTeacher + " successfully added");
    }

    @Override
    public void viewEnrolledStudents(SubjectName subjectName) {

    }

    @Override
    public void gradeStudent(Student student, SubjectName subjectName, int grade) {

    }
}
