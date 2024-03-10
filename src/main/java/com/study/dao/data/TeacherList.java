package com.study.dao.data;

import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherList {
    public List<Teacher> teacherList;

    public TeacherList() {
        teacherList = new ArrayList<>(List.of(
                new Teacher("Petro", "Ivaniv", new Subject("Physics")),
                new Teacher("Larisa", "Volodina", new Subject("Physics")),
                new Teacher("Tanya", "Sobchuk", new Subject("Math")),
                new Teacher("Vitalina", "Stepashko", new Subject("Math")),
                new Teacher("Olesya", "Pochaina", new Subject("English"))));
    }

    public Optional<Teacher> findTeacherById(int teacherId) {
        return teacherList.stream()
                .filter(student -> student.getId() == teacherId)
                .findFirst();
    }

    public Optional<Teacher> findTeacherBySubject(String subject) {
        return teacherList.stream()
                .filter(t -> t.getSubject().getName().equals(subject))
                .findFirst();
    }
}
