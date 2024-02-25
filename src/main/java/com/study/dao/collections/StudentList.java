package com.study.dao.collections;

import com.study.dao.pojo.Student;
import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentList {

    public List<Student> students;

    public StudentList() {
        students = new ArrayList<>(List.of(
                new Student("Ivan", "Ivanenko", Faculty.RADIOENGINEERING, Specialty.RADIO_ENGINEERING, Group.REE_11, new ArrayList<>(List.of(SubjectName.PHYSICS, SubjectName.MATH, SubjectName.ENGLISH))
        )));
    }
}
