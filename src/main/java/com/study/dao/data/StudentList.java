package com.study.dao.data;

import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.store.DataProvider;
import com.study.service.exception.NotFoundException;
import java.util.List;
import java.util.UUID;

public class StudentList {

    private List<Student> students;

    public StudentList() {
        this.students = DataProvider.generateStudents();
    }

    public Student findStudentById(String studentId) {
        return students.stream()
                .filter(student -> student.getUuid().equals(UUID.fromString(studentId)))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student with id " + studentId + " not found"));
    }

    public Group findGroupByStudent(Student student) {
        return student.getGroup();
    }

    public UUID addStudent(Student student) {
        students.add(student);
        return student.getUuid();
    }

    public UUID deleteStudent(String studentId) {
        Student studentToRemove = students.stream().filter(student -> student.getUuid().equals(UUID.fromString(studentId)))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student with id " + studentId + " not found"));

        students.remove(studentToRemove);
        return studentToRemove.getUuid();
    }

    public List<Student> viewAllStudents() {
        return students.stream().toList();
    }

    public List<Student> getStudents() {
        return students;
    }
}
