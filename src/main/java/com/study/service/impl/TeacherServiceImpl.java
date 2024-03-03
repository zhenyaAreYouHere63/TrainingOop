package com.study.service.impl;

import com.study.dao.collections.StudentList;
import com.study.dao.collections.TeacherList;
import com.study.dao.pojo.Student;
import com.study.dao.pojo.Teacher;
import com.study.dao.program.SubjectName;
import com.study.service.TeacherService;
import com.study.service.validation.IdValidation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TeacherServiceImpl implements TeacherService, IdValidation {
    private final TeacherList teachers;
    private StudentList students;
    private final Teacher teacher;

    public TeacherServiceImpl() {
        this.teacher = new Teacher();
        this.teachers = new TeacherList();
    }

    @Override
    public void createNewTeacher(String firstName, String lastName, SubjectName subjectName) {
        var newTeacher = new Teacher(firstName, lastName, subjectName);
        teachers.teachers.add(newTeacher);
        System.out.println(newTeacher + " successfully added");
    }

    @Override
    public void viewEnrolledStudents(int teacherId, SubjectName subjectName) {
        if (idValidation(teacherId)) {
            Optional<Teacher> teacher = teachers.teachers.stream().filter(t -> t.getId() == teacherId)
                    .findFirst();

            if (teacher.isPresent()) {
                students.students
                        .stream()
                        .filter(student -> student.getAttendedSubject().contains(subjectName))
                        .forEach(System.out::println);
            } else {
                System.out.println(String.format("Teacher with id %d not found %n", teacherId));
            }
        }
    }

    @Override
    public void gradeStudent(int studentId, SubjectName subject, int grade) {
        if (idValidation(studentId)) {
            Optional<Student> student = students.students.stream().filter(s -> s.getId() == studentId)
                    .findFirst();

            if (student.isPresent()) {
                Optional<Teacher> teacher = teachers.teachers.stream().filter(t -> t.getSubjectName() == subject)
                        .findFirst();

                if (teacher.isPresent()) {
                    if (student.get().getAttendedSubject().contains(subject)) {
                        Map<SubjectName, List<Integer>> studentGrades = student.get().getGrades();

                        List<Integer> gradesForSubject = studentGrades.computeIfAbsent(subject, k -> new ArrayList<>());

                        gradesForSubject.add(grade);
                    } else {
                        System.out.println("Sorry, this student is not studying this subject");
                    }
                } else {
                    System.out.printf(String.format("This teacher does not teach the subject %s %n", subject));
                }
            } else {
                System.out.printf("Student with id %d not found%n", studentId);
            }
        }
    }

    @Override
    public void viewTeachers() {
        teachers.teachers.forEach(System.out::println);
    }
}
