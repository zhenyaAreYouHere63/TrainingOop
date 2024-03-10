package com.study.dao.data;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentList {

    public List<Student> studentList;

    public StudentList() {
        studentList = new ArrayList<>(List.of(
                new Student("Ivan", "Ivanenko", "Radioengineering", "Radio_engineering", "Ree_11",
                        new ArrayList<>(List.of(new Subject("Math"), new Subject("Physics")))
        )));
    }

    public Optional<Student> findStudentById(int studentId) {
        return studentList.stream()
                .filter(student -> student.getId() == studentId)
                .findFirst();
    }
}
