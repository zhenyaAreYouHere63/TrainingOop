package com.study.dao.pojo;

import com.study.dao.program.SubjectName;
import java.util.Objects;

public class Teacher {

    private String firstName;

    private String lastName;

    private SubjectName subjectName;

    public Teacher() {

    }
    public Teacher(String firstName, String lastName, SubjectName subjectName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjectName = subjectName;
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

    public SubjectName getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(SubjectName subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && subjectName == teacher.subjectName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, subjectName);
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", subjectName=" + subjectName +
               '}';
    }
}
