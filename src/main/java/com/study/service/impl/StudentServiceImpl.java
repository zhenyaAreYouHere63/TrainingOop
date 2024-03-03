package com.study.service.impl;

import com.study.dao.collections.StudentList;
import com.study.dao.pojo.Student;
import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import com.study.service.StudentService;
import com.study.service.validation.IdValidation;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StudentServiceImpl implements StudentService, IdValidation {

    private StudentList students;

    private Specialty studentSpecialty;

    public StudentServiceImpl(StudentList students) {
        this.students = students;
    }

    @Override
    public void createNewStudent(String firstName, String lastName, Faculty faculty, Group group,
                                 Specialty specialty) {

        Student newStudent = new Student(firstName, lastName, faculty, specialty, group);
        students.students.add(newStudent);
        System.out.println(newStudent + " successfully added");
    }

    @Override
    public void addStudentToCourse(int studentId, SubjectName subjectName) {

        if (idValidation(studentId)) {
            Optional<Student> optionalStudent = students.students.stream().filter(student -> student.getId() == studentId)
                    .findFirst();

            if (optionalStudent.isPresent()) {
                studentSpecialty = optionalStudent.get().getSpecialty();
                SubjectName compulsorySubjectOfStudent = studentSpecialty.getCompulsorySubject();
                if (optionalStudent.get().getAttendedSubject().size() < 3 && !compulsorySubjectOfStudent.equals(subjectName)) {
                    optionalStudent.get().getAttendedSubject().add(subjectName);
                } else {
                    if (optionalStudent.get().getAttendedSubject().size() >= 3) {
                        System.out.println("Sorry, the number of subjects has been exceeded");
                    } else if (studentSpecialty.getCompulsorySubject().equals(subjectName)) {
                        System.out.println("Student with id " + studentId + " is already studying this subject");
                    }
                }
            } else {
                System.out.println("Student with ID " + studentId + " not found");
            }
        }
    }

    @Override
    public void viewAllSubjects(int studentId) {
        if (idValidation(studentId)) {
            students.students.stream()
                    .filter(student -> student.getId() == studentId)
                    .findFirst()
                    .ifPresentOrElse(student -> {
                                System.out.println("Subjects of the student's choice");
                                student.getAttendedSubject()
                                        .stream().map(SubjectName::name)
                                        .forEach(subject -> System.out.println(" -" + subject));
                                studentSpecialty = student.getSpecialty();
                                SubjectName compulsorySubjectOfStudent = studentSpecialty.getCompulsorySubject();
                                System.out.println("Compulsory subject");
                                System.out.println(" -" + compulsorySubjectOfStudent);
                            },
                            () -> System.out.println("Student with ID " + studentId + " not found"));
        }
    }

    @Override
    public void viewAllGrades(int studentId) {
        if (idValidation(studentId)) {
            Optional<Student> student = students.students.stream().filter(s -> s.getId() == studentId).findFirst();

            if (student.isPresent()) {
                student.get().getGrades().forEach((key, value) -> System.out.println("subject: " + key + ", grade: " + value));
            } else {
                System.out.println("Sorry, student with ID " + studentId + " not found");
            }
        }
    }

    @Override
    public double averageGradeOfSubject(int studentId, SubjectName subjectName) {
        if (idValidation(studentId)) {
            Optional<Student> student = students.students.stream().filter(s -> s.getId() == studentId).findFirst();

            if (student.isPresent()) {
                Student foundStudent = student.get();

                List<SubjectName> attendedSubjects = foundStudent.getAttendedSubject();
                Map<SubjectName, List<Integer>> studentGrades = foundStudent.getGrades();

                long numberOfGrades = studentGrades.entrySet().stream()
                        .filter(entry -> entry.getKey().equals(subjectName))
                        .flatMapToInt(entry -> entry.getValue().stream().mapToInt(Integer::intValue))
                        .count();

                if (numberOfGrades > 0) {
                    int totalGrades = studentGrades.entrySet().stream()
                            .filter(entry -> entry.getKey().equals(subjectName))
                            .flatMapToInt(entry -> entry.getValue().stream().mapToInt(Integer::intValue))
                            .sum();

                    return (double) totalGrades / numberOfGrades;
                } else {
                    System.out.println("Sorry, student with ID " + studentId + " did not attend the subject " + subjectName);
                }
            } else {
                System.out.println("Sorry, student with ID " + studentId + " not found");
            }
        }
        return 0.0;
    }

    @Override
    public void viewAllStudents() {
        students.students.forEach(System.out::println);
    }
}
