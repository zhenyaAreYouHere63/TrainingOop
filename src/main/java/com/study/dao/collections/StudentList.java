package com.study.dao.collections;

import com.study.dao.pojo.Student;
import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import java.util.ArrayList;
import java.util.List;

public class StudentList {

    public List<Student> students;

    public StudentList() {
        students = new ArrayList<>(List.of(
                new Student("Ivan", "Ivanenko", Faculty.RADIOENGINEERING, Specialty.RADIO_ENGINEERING, Group.REE_11)
        ));
    }
}
