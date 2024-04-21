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

    private static Map<String, Teacher> teachersByGroup = new HashMap<>();

    public Subject(String name, SubjectType type) {
        this.name = name;
        this.type = type;
    }

    public Subject(String name) {
        this.name = name;
    }



}
