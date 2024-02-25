package com.study.dao.pojo;

import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Student {
    private static int lastId = 0;
    private int id;
    private String firstName;
    private String lastName;
    private Faculty faculty;
    private Specialty specialty;
    private Group group;
    private List<SubjectName> attendedSubjectName;
    private Map<SubjectName, Integer> grades;

    public Student(String firstName, String lastName, Faculty faculty, Specialty specialty, Group group,
                   List<SubjectName> attendedSubjectName) {
        this.id = ++lastId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.specialty = specialty;
        this.group = group;
        this.attendedSubjectName = attendedSubjectName;
    }

    public Student() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<SubjectName> getAttendedSubject() {
        return attendedSubjectName;
    }

    public void setAttendedSubject(List<SubjectName> attendedSubjectName) {
        this.attendedSubjectName = attendedSubjectName;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && faculty == student.faculty && specialty == student.specialty && group == student.group && Objects.equals(attendedSubjectName, student.attendedSubjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, faculty, specialty, group, attendedSubjectName);
    }

    @Override
    public String toString() {
        return "Student{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", faculty=" + faculty +
               ", specialty=" + specialty +
               ", group=" + group +
               ", attendedSubjectName=" + attendedSubjectName +
               '}';
    }
}
