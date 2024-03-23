package com.study.dao.data;

import com.study.dao.Counter;
import com.study.dao.SubjectType;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.service.exception.NotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.HashSet;

public class StudentList {

    public List<Student> students;

    public StudentList() {
        students = new ArrayList<>(Arrays.asList(
                new Student(Counter.getStudentInstance().generateStudentId(), UUID.randomUUID(),
                        "Ivan", "Ivanenko", "Radioengineering", "Radio_engineering", "Ree_11",
                        new HashSet<>(List.of(new Subject("Math", SubjectType.COMPULSORY), new Subject("Physics", SubjectType.COMPULSORY)))
        )));
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
}
