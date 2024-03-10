package com.study.service.impl;

import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dao.core.Student;
import com.study.dao.core.Teacher;
import com.study.service.TeacherService;
import com.study.service.exception.IncorrectIdException;
import com.study.service.validation.IdValidator;
import java.util.*;
import java.util.stream.Stream;

public class TeacherServiceImpl implements TeacherService, IdValidator {
    private final TeacherList teachers;
    private StudentList students;
    private final Teacher teacher;

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

        if (!isIdValid(teacherId)) {
            throw new IncorrectIdException("Invalid student id: " + teacherId);
        }

        Teacher teacher = optionalTeacher.orElseThrow(() ->
                new IllegalArgumentException("Teacher with id " + teacherId + " not found"));

        students.studentList
                .stream()
                .filter(student -> student.getOptionalSubjects().contains(teacher.getSubject()) ||
                                   student.getCompulsorySubjects().contains(teacher.getSubject()))
                .forEach(studentsEnrolledToSubject::add);

        return studentsEnrolledToSubject;
    }

    @Override
    public HashMap<Subject, List<Integer>> evaluateStudent(int studentId, String subject, List<Integer> newGrades) {
        HashMap<Subject, List<Integer>> result = new HashMap<>();

        if (!isIdValid(studentId)) {
            throw new IncorrectIdException("Invalid student id: " + studentId);
        }

        Optional<Student> optionalStudent = students.findStudentById(studentId);

        Student student = optionalStudent.orElseThrow(() ->
                new IllegalArgumentException("Student with id " + studentId + " not found"));

        Optional<Teacher> optionalTeacher = teachers.findTeacherBySubject(subject);

        Teacher teacher = optionalTeacher.orElseThrow(() ->
                new IllegalArgumentException("Teacher for subject " + subject + " not found"));

        List<Subject> compulsorySubjects = student.getCompulsorySubjects();
        List<Subject> optionalSubjects = student.getOptionalSubjects();

        List<Subject> subjectToCheck = Stream.concat(compulsorySubjects.stream(), optionalSubjects.stream())
                .filter(subjectName -> subjectName.getName().equals(subject))
                .toList();

        if (!subjectToCheck.isEmpty()) {
            Subject subjectToProcess = subjectToCheck.get(0);

            HashMap<Subject, List<Integer>> grades = student.getGrades();

            List<Integer> gradesForSubject = grades
                    .computeIfAbsent(subjectToProcess, k -> new ArrayList<>());

            gradesForSubject.addAll(newGrades);

            result.put(subjectToProcess, gradesForSubject);
        }
        return result;
    }

    @Override
    public List<Teacher> viewTeachers() {
        return teachers.teacherList;
    }

    @Override
    public void assignTeacherToGroup(int teacherId, String group) {
        Optional<Teacher> optionalTeacher = teachers.findTeacherById(teacherId);

        Teacher teacher = optionalTeacher.orElseThrow(() ->
                new IllegalArgumentException("Teacher with id " + teacherId + " not found"));

        Subject subject = teacher.getSubject();

        if (!subject.isTeacherAssignedToGroup(group, subject.getName())) {
            teacher.getSubject().assignTeacherToGroup(group, teacher);
            System.out.println("Teacher with id " + teacherId + " assigned to group ");
        } else {
            System.out.println("Error: Another teacher is already assigned to group ");
        }
    }

    @Override
    public Teacher getTeacherByGroup(String subjectName, String group) {
        return teachers.teacherList.stream()
                .map(t -> t.getSubject().getTeacherByGroupAndSubject(group, subjectName))
                .findFirst()
                .orElse(null);
    }
}
