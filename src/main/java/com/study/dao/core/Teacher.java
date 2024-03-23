package com.study.dao.core;

import lombok.Setter;
import lombok.Getter;
import lombok.ToString;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.util.UUID;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    private int id;

    private UUID uuid;

    private String firstName;

    private String lastName;

    private Subject subject;

    public Teacher(Integer id, UUID uuid, String firstName, String lastName, Subject subject) {
        this.id = id;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }
}
