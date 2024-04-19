package com.study.dao.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class Group {

    private String name;

    private Set<Student> students;

    private Set<Teacher> teachers;

    public Group(String name) {
        this.name = name;
        this.students = new HashSet<>();
        this.teachers = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Group{" +
               "name='" + name + '\'' +
               '}';
    }
}
