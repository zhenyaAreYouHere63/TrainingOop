package com.study.dao.core;

import com.study.service.exception.NotFoundException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;
import com.study.service.exception.RepeatException;

@NoArgsConstructor
@Getter
@Setter
public class Group {

    private String name;

    private Set<Student> students = new HashSet<>();

    private Set<Teacher> teachers = new HashSet<>();

    public Group(String name) {
        this.name = name;
    }

    public void addTeacherToGroup(Teacher teacher) {
        if (teachers.stream().anyMatch(currentTeacher -> currentTeacher.getSubject().getName()
                .equals(teacher.getSubject().getName()))) {
            throw new RepeatException("A teacher with subject " + teacher.getSubject().getName()  + " is already assigned to this group");
        }

        teachers.add(teacher);
    }

    public void removeTeacherByGroup(int teacherId) {
        Teacher foundTeacher = teachers.stream().filter(teacher -> teacher.getId() == teacherId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Teacher with id " + teacherId + " not found"));

        teachers.remove(foundTeacher);
    }

    @Override
    public String toString() {
        return "Group{" +
               "name='" + name + '\'' +
               '}';
    }
}
