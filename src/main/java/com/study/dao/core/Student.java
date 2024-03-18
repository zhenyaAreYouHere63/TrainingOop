package com.study.dao.core;

import com.study.dao.Counter;
import java.util.UUID;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Student {
    private Integer id;
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String faculty;
    private String specialty;
    private String group;

    private Map<CourseType, List<Subject>> subjects;
    private List<Subject> optionalSubjects;
    private List<Subject> compulsorySubjects;
    private HashMap<Subject, List<Integer>> grades;

    public Student(String firstName, String lastName, String faculty, String specialty, String group, List<Subject> compulsorySubjects) {
        this.id = Counter.getStudentInstance().generateStudentId();
        this.uuid = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.specialty = specialty;
        this.group = group;
        this.compulsorySubjects = compulsorySubjects;
        this.optionalSubjects = new ArrayList<>();
        grades = new HashMap<>();
    }

    public void addOptionalSubject(Subject subject){
        optionalSubjects.add(subject);
        grades.put(subject, new ArrayList<>());
    }


    public Integer getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    public List<Subject> getOptionalSubjects() {
        return optionalSubjects;
    }

    public void setOptionalSubjects(List<Subject> optionalSubjects) {
        this.optionalSubjects = optionalSubjects;
    }

    public Map<Subject, List<Integer>> getGrades() {
        return grades;
    }

    public void setGrades(HashMap<Subject, List<Integer>> grades) {
        this.grades = grades;
    }

    public List<Subject> getCompulsorySubjects() {
        return compulsorySubjects;
    }

    public void setCompulsorySubjects(List<Subject> compulsorySubjects) {
        this.compulsorySubjects = compulsorySubjects;
    }

    public Map<CourseType, List<Subject>> getSubjects() {
        return subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id) && Objects.equals(uuid, student.uuid) && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(faculty, student.faculty) && Objects.equals(specialty, student.specialty) && Objects.equals(group, student.group) && Objects.equals(optionalSubjects, student.optionalSubjects) && Objects.equals(compulsorySubjects, student.compulsorySubjects) && Objects.equals(grades, student.grades);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, firstName, lastName, faculty, specialty, group, optionalSubjects, compulsorySubjects, grades);
    }

    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", uuid=" + uuid +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", faculty='" + faculty + '\'' +
               ", specialty='" + specialty + '\'' +
               ", group='" + group + '\'' +
               ", optionalSubjects=" + optionalSubjects +
               ", compulsorySubjects=" + compulsorySubjects +
               '}';
    }
}
