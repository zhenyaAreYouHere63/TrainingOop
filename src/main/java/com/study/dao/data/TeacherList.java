package com.study.dao.data;

import com.study.dao.store.DataProvider;
import com.study.dao.core.Teacher;
import com.study.service.exception.NotFoundException;
import java.util.List;
import java.util.UUID;

public class TeacherList {

    public List<Teacher> teachers = DataProvider.generateTeachers();

    public Teacher findTeacherById(String teacherId) {
        return teachers.stream()
                .filter(student -> student.getUuid().equals(UUID.fromString(teacherId)))
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

    public UUID deleteTeacher(String teacherId) {
        Teacher teacherToDelete = teachers.stream().filter(teacher -> teacher.getUuid().equals(UUID.fromString(teacherId)))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Teacher with id " + teacherId + " not found"));

        teachers.remove(teacherToDelete);
        return teacherToDelete.getUuid();
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }
}
