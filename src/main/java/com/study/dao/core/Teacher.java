package com.study.dao.core;

import com.study.dao.Counter;
import java.util.Objects;
import java.util.UUID;

public class Teacher {

    private int id;

    private UUID uuid;

    private String firstName;

    private String lastName;

    private Subject subject;

    public Teacher() {

    }
    public Teacher(String firstName, String lastName, Subject subject) {
        this.id = Counter.getTeacherInstance().generateTeacherId();
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public UUID getUuid() {
        return uuid;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id && Objects.equals(uuid, teacher.uuid) && Objects.equals(firstName, teacher.firstName) && Objects.equals(lastName, teacher.lastName) && Objects.equals(subject, teacher.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, firstName, lastName, subject);
    }

    @Override
    public String toString() {
        return "Teacher{" +
               "id=" + id +
               ", uuid=" + uuid +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", subject='" + subject + '\'' +
               '}';
    }
}
