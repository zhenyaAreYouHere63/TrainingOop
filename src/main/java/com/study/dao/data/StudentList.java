package com.study.dao.data;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.service.exception.NotFoundException;

import java.util.*;

public class StudentList {

    private List<Student> studentList;

    public StudentList() {
        studentList = new ArrayList<>(Collections.singletonList(
                new Student("Ivan", "Ivanenko", "Radioengineering", "Radio_engineering", "Ree_11",
                        new ArrayList<>(List.of(new Subject("Math"), new Subject("Physics")))
        )));
    }

    public Student findStudentById(int studentId) {
        return studentList.stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student with id " + studentId + " not found"));
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
