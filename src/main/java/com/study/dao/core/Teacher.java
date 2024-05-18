package com.study.dao.core;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Teacher {

    private int id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private Set<Group> groups;
    private Subject subject;

    public Teacher(Integer id, UUID uuid, String firstName, String lastName, Subject subject) {
        this.id = id;
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
        this.groups = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", groups=" + groups +
               ", subject=" + subject +
               ", uuid=" + uuid +
               '}';
    }
}
