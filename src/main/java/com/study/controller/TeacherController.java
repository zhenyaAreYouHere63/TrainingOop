package com.study.controller;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dao.data.StudentList;
import com.study.service.TeacherService;
import com.study.service.impl.TeacherServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(StudentList students) {
        teacherService = new TeacherServiceImpl(students);
    }

    public void addTeacher(String firstName, String lastName, Subject subject) {
        UUID teacherUuid = teacherService.createNewTeacher(firstName, lastName, subject);
        System.out.println(teacherUuid);
    }

    public void getAllStudents(int teacherId) {
        List<Student> students = teacherService.viewEnrolledStudents(teacherId);
        for(Student student: students) {
            System.out.println(student);
        }
    }

    public void evaluateStudent(int studentId, String subject, List<Integer> newGrades) {
        HashMap<Subject, List<Integer>> evaluateGradesForSubject = teacherService.evaluateStudent(studentId, subject, newGrades);

        for(Map.Entry<Subject, List<Integer>> entry: evaluateGradesForSubject.entrySet()) {
            String subjectToEvaluate = entry.getKey().getName();
            List<Integer> grades = entry.getValue();

            System.out.println("Subject: " + subjectToEvaluate);

            for(Integer grade: grades) {
                System.out.println("Grade: " + grade);
            }
        }
    }

    public void getAllTeacherList() {
        List<Teacher> teachers = teacherService.viewTeachers();
        for(Teacher teacher: teachers) {
            System.out.println(teacher);
        }
    }

    public void addTeacherToGroup(int teacherId, String group) {
        Teacher teacher = teacherService.assignTeacherToGroup(teacherId, group);
        System.out.println("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " successfully added to group " + group);
    }

    public void getTeacherByGroup(String subjectName, String group) {
        Teacher teacherByGroup = teacherService.getTeacherByGroup(subjectName, group);
        System.out.println(teacherByGroup);
    }
 }
