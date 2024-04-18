package com.study.dao.core;

import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Set;
import java.util.UUID;
import java.util.List;
import java.util.HashMap;

@Getter
@Setter
@EqualsAndHashCode
@ToString
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
}
