package com.study.dao.collections;

import com.study.dao.pojo.Teacher;
import com.study.dao.program.SubjectName;
import java.util.ArrayList;
import java.util.List;

public class TeacherList {
    public List<Teacher> teachers;

    public TeacherList() {
        teachers = new ArrayList<>(List.of(
                new Teacher("Petro", "Ivaniv", SubjectName.PHYSICS),
                new Teacher("Larisa", "Volodina", SubjectName.PHYSICS),
                new Teacher("Tanya", "Sobchuk", SubjectName.MATH),
                new Teacher("Vitalina", "Stepashko", SubjectName.MATH),
                new Teacher("Olesya", "Pochaina", SubjectName.ENGLISH)));
    }
}
