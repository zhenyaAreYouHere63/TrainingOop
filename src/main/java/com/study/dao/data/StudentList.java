package com.study.dao.data;

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

    public Student findStudentById(int studentId) {
        return students.stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student with id " + studentId + " not found"));
    }

    public UUID addStudent(Student student) {
        students.add(student);
        return student.getUuid();
    }

    public UUID deleteStudent(int studentId) {
        Student studentToRemove = students.stream().filter(student -> student.getId() == studentId)
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

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
