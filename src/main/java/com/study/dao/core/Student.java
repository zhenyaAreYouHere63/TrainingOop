package com.study.dao.core;

import java.util.UUID;
import java.util.List;
import java.util.Set;
import java.util.HashMap;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Integer id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String faculty;
    private String specialty;
    private Group group;
    private Set<Subject> subjects;
    private HashMap<Subject, List<Integer>> grades;

    public Student(Integer id, UUID uuid, String firstName, String lastName, String faculty, String specialty, Group group, Set<Subject> subjects) {
        this.id = id;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.specialty = specialty;
        this.group = group;
        this.subjects = subjects;
        this.grades = new HashMap<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(uuid, student.uuid) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Student{" +
               "uuid=" + uuid +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", faculty='" + faculty + '\'' +
               ", specialty='" + specialty + '\'' +
               ", group=" + group +
               ", subjects=" + subjects +
               '}';
    }
}
