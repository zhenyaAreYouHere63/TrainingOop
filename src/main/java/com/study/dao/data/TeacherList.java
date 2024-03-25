package com.study.dao.data;

import com.study.dao.IdCounter;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.service.exception.NotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TeacherList {

    private static final IdCounter ID_COUNTER = IdCounter.getTeacherInstance();
    private final List<Teacher> teachers;

    public TeacherList() {
        teachers = new ArrayList<>(Arrays.asList(
                new Teacher(ID_COUNTER.generateTeacherId(), UUID.randomUUID(),"Petro", "Ivaniv", new Subject("Physics")),
                new Teacher(ID_COUNTER.generateTeacherId(), UUID.randomUUID(), "Larisa", "Volodina", new Subject("Physics")),
                new Teacher(ID_COUNTER.generateTeacherId(), UUID.randomUUID(), "Tanya", "Sobchuk", new Subject("Math")),
                new Teacher(ID_COUNTER.generateTeacherId(), UUID.randomUUID(), "Vitalina", "Stepashko", new Subject("Math")),
                new Teacher(ID_COUNTER.generateTeacherId(), UUID.randomUUID(), "Olesya", "Pochaina", new Subject("English"))));
    }

    public Teacher findTeacherById(int teacherId) {
        return teachers.stream()
                .filter(student -> student.getId() == teacherId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Teacher with id " + teacherId + " not found"));
    }

    public Teacher findTeacherBySubject(String subject) {
        return teachers.stream()
                .filter(t -> t.getSubject().getName().equals(subject))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(("Teacher for subject " + subject + " not found")));
    }

    public Teacher addTeacher(Teacher teacher) {
        teachers.add(teacher);
        return teacher;
    }

    public UUID deleteTeacher(int teacherId) {
        Teacher teacherToDelete = teachers.stream().filter(teacher -> teacher.getId() == teacherId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Teacher with id " + teacherId + " not found"));

        teachers.remove(teacherToDelete);
        return teacherToDelete.getUuid();
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
