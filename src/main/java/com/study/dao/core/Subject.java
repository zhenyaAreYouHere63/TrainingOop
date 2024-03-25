package com.study.dao.core;

import com.study.dao.SubjectType;
import com.study.service.exception.NotFoundException;
import com.study.service.exception.RepeatException;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Map;
import java.util.HashMap;

@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Subject {

    private String name;

    private SubjectType type;

    //TODO: it should be Group, not string
    private static Map<String, Teacher> teachersByGroup = new HashMap<>();

    public Subject(String name, SubjectType type) {
        this.name = name;
        this.type = type;
    }

    public Subject(String name) {
        this.name = name;
    }

    public void assignTeacherToGroup(String group, Teacher teacher) {
        if(!isTeacherAssignedToGroup(group, teacher.getSubject().getName())) {
            teachersByGroup.put(group, teacher);
        } else {
            throw new RepeatException("A teacher is already assigned to this group for the subject");
        }
    }

    public Teacher getTeacherByGroupAndSubject(String group, String subject) {
        return teachersByGroup.values()
                .stream()
                .filter(teacher -> teacher.getSubject().getName().equals(subject) &&
                        teacher.getSubject().isTeacherAssignedToGroup(group, subject))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Not found teacher to this group for the subject"));
    }

    public boolean isTeacherAssignedToGroup(String group, String subject) {
        Teacher teacher = teachersByGroup.get(group);
        return teacher != null && teacher.getSubject().getName().equals(subject);
    }
}
