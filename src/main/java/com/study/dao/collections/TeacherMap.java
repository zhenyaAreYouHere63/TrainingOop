package com.study.dao.collections;

import com.study.dao.pojo.Teacher;
import com.study.dao.program.SubjectName;
import java.util.HashMap;
import java.util.Map;

public class TeacherMap {
    public Map<Integer, Teacher> teachers;

    public TeacherMap() {
        teachers = new HashMap<>(Map.of(1, new Teacher("Petro", "Ivaniv", SubjectName.PHYSICS),
                2, new Teacher("Larisa", "Volodina", SubjectName.PHYSICS),
                3, new Teacher("Tanya", "Sobchuk", SubjectName.MATH),
                4, new Teacher("Vitalina", "Stepashko", SubjectName.MATH),
                5, new Teacher("Olesya", "Pochaina", SubjectName.ENGLISH)));

    }
}
