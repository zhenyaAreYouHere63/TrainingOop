package com.study.service.impl;

import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dao.core.Student;
import com.study.dao.core.Teacher;
import com.study.service.TeacherService;
import com.study.service.exception.NotFoundException;

import java.util.*;
import java.util.stream.Stream;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherList teachers;
    private final Teacher teacher;
    private StudentList students;

    public TeacherServiceImpl(StudentList students) {
        this.teacher = new Teacher();
        this.teachers = new TeacherList();
        this.students = students;
    }

    @Override
    public UUID createNewTeacher(String firstName, String lastName, Subject subject) {

        var newTeacher = new Teacher(firstName, lastName, subject);
        teachers.teacherList.add(newTeacher);

        return newTeacher.getUuid();
    }

    @Override
    public List<Student> viewEnrolledStudents(int teacherId) {

        List<Student> studentsEnrolledToSubject = new ArrayList<>();

        Optional<Teacher> optionalTeacher = teachers.findTeacherById(teacherId);

        Teacher teacher = optionalTeacher.orElseThrow(() ->
                new NotFoundException("Teacher with id " + teacherId + " not found"));

        students.getStudentList()
                .stream()
                .filter(student -> student.getOptionalSubjects().contains(teacher.getSubject()) ||
                                   student.getCompulsorySubjects().contains(teacher.getSubject()))
                .forEach(studentsEnrolledToSubject::add);

        return studentsEnrolledToSubject;
    }

    @Override
    public Map<Subject, List<Integer>> evaluateStudent(int studentId, String subject, List<Integer> newGrades) {
        Student student = students.findStudentById(studentId);

        Optional<Teacher> optionalTeacher = teachers.findTeacherBySubject(subject);

        Teacher teacher = optionalTeacher.orElseThrow(() ->
                new NotFoundException("Teacher for subject " + subject + " not found"));

        //TODO: use map instead

        List<Subject> compulsorySubjects = student.getCompulsorySubjects();
        List<Subject> optionalSubjects = student.getOptionalSubjects();

        //TODO: there should be 1 subject with the name, u can use not list, but Set in Student
        List<Subject> subjectToCheck = Stream.concat(compulsorySubjects.stream(), optionalSubjects.stream())
                .filter(subjectName -> subjectName.getName().equals(subject))
                .toList();

        if (!subjectToCheck.isEmpty()) {
            Subject subjectToProcess = subjectToCheck.getFirst();

            Map<Subject, List<Integer>> grades = student.getGrades();
            grades.get(subjectToProcess).addAll(newGrades);

            Map<Subject, List<Integer>> result = new HashMap<>();
            result.put(subjectToProcess, newGrades);

            return result;
        }

        return null;
    }

    @Override
    public List<Teacher> viewTeachers() {
        return teachers.teacherList;
    }

    @Override
    public Teacher assignTeacherToGroup(int teacherId, String group) {
        Optional<Teacher> optionalTeacher = teachers.findTeacherById(teacherId);

        Teacher teacher = optionalTeacher.orElseThrow(() ->
                new NotFoundException("Teacher with id " + teacherId + " not found"));

        Subject subject = teacher.getSubject();

        if (!subject.isTeacherAssignedToGroup(group, subject.getName())) {
            teacher.getSubject().assignTeacherToGroup(group, teacher);
        } else {
            System.out.println("Error: Another teacher is already assigned to group ");
        }

        return teacher;
    }

    @Override
    public Teacher getTeacherByGroup(String subjectName, String group) {
        return teachers.teacherList.stream()
                .map(t -> t.getSubject().getTeacherByGroupAndSubject(group, subjectName))
                .findFirst()
                .orElse(null);
    }
}
