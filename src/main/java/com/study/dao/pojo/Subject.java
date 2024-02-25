package com.study.dao.pojo;

import com.study.dao.program.SubjectName;
import java.util.ArrayList;
import java.util.List;

public class Subject {

    private SubjectName subjectName;
    private List<Teacher> teachers;

    public Subject() {
        this.teachers = new ArrayList<>();
    }

    private void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }
}
