package com.study.dao.core;

import java.util.Map;
import java.util.HashMap;
import java.util.Objects;



public class Subject {

    private String name;

    private static Map<String, Teacher> teachersByGroup = new HashMap<>();

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, Teacher> getTeachersByGroup() {
        return teachersByGroup;
    }

    public void setTeachersByGroup(Map<String, Teacher> teachersByGroup) {
        this.teachersByGroup = teachersByGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) && Objects.equals(teachersByGroup, subject.teachersByGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, teachersByGroup);
    }

    @Override
    public String toString() {
        return "Subject{" +
               "name='" + name + '\'' +
               '}';
    }

    public void assignTeacherToGroup(String group, Teacher teacher) {
        if(!isTeacherAssignedToGroup(group, teacher.getSubject().getName())) {
            teachersByGroup.put(group, teacher);
        } else {
            System.out.println("A teacher is already assigned to this group for the subject.");
        }
    }

    public boolean isTeacherAssignedToGroup(String group, String subject) {
        Teacher teacher = teachersByGroup.get(group);
        if (teacher != null && teacher.getSubject().getName().equals(subject)) {
            return true;
        } else {
            return false;
        }
    }

    public Teacher getTeacherByGroupAndSubject(String group, String subject) {
        return teachersByGroup.values()
                .stream()
                .filter(teacher -> teacher.getSubject().getName().equals(subject) &&
                        teacher.getSubject().isTeacherAssignedToGroup(group, subject))
                .findFirst()
                .orElse(null);
    }
}
