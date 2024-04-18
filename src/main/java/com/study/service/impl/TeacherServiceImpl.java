package com.study.service.impl;

import com.study.dao.core.Group;
import com.study.dao.core.Subject;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dao.core.Student;
import com.study.dao.core.Teacher;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import com.study.service.TeacherService;
import com.study.service.exception.NotFoundException;
import java.util.*;

public class TeacherServiceImpl implements TeacherService {
    private TeacherList teachers;
    private StudentList students;
    private TeacherMapper teacherMapper;
    private GroupList groups;

    public TeacherServiceImpl(StudentList students, TeacherMapper teacherMapper, GroupList groups, TeacherList teachers) {
        this.teachers = teachers;
        this.groups = groups;
        this.students = students;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public UUID createNewTeacher(TeacherDto teacherDto) {
        Teacher mappedTeacher = teacherMapper.mapTeacherDtoToTeacher(teacherDto);

        Teacher savedTeacher = teachers.addTeacher(mappedTeacher);

        return savedTeacher.getUuid();
    }

    @Override
    public List<Student> viewEnrolledStudents(int teacherId) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        List<Student> studentsEnrolledToSubject = new ArrayList<>();

        students.getStudents()
                .stream()
                .filter(student -> student.getSubjects().stream()
                        .anyMatch(subject -> subject.getName().equalsIgnoreCase(foundTeacher.getSubject().getName())))
                .filter(student -> student.getGroup().getName().equals(foundTeacher.getSubject().getName()))
                .forEach(studentsEnrolledToSubject::add);

        return studentsEnrolledToSubject;
    }

    @Override
    public UUID deleteTeacher(int teacherId) {
        groups.removeTeacherFromGroup(teacherId);
        return teachers.deleteTeacher(teacherId);
    }

    @Override
    public HashMap<Subject, List<Integer>> evaluateStudent(int studentId, String subject, List<Integer> newGrades) {

        Student foundStudent = students.findStudentById(studentId);

        Teacher foundTeacher = teachers.findTeacherBySubject(subject);

        HashMap<Subject, List<Integer>> result = new HashMap<>();

        Subject foundSubject = foundStudent.getSubjects().stream()
                .filter(currentSubject -> currentSubject.getName().equalsIgnoreCase(subject))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Sorry, this student not study " + subject));


        HashMap<Subject, List<Integer>> grades = foundStudent.getGrades();

        List<Integer> gradesForSubject = grades
                .computeIfAbsent(foundSubject, k -> new ArrayList<>());

        gradesForSubject.addAll(newGrades);

        result.put(foundSubject, gradesForSubject);

        return result;
    }

    @Override
    public Teacher assignTeacherToGroup(int teacherId, String group) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        groups.assignTeacherToGroup(foundTeacher, group);

        return foundTeacher;
    }

    @Override
    public Teacher removeTeacherFromGroup(int teacherId) {
        return groups.removeTeacherFromGroup(teacherId);
    }

    @Override
    public Set<Teacher> getTeacherByGroup(String group) {
        return groups.getTeacherByGroup(group);
    }

    @Override
    public List<Teacher> viewTeachers() {
        return teachers.getTeachers();
    }
}
