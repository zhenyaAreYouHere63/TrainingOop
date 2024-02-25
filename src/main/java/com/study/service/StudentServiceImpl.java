package com.study.service;

import com.study.dao.collections.StudentList;
import com.study.dao.pojo.Student;
import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    private final StudentList students;

    public StudentServiceImpl() {
        this.students = new StudentList();
    }

    @Override
    public void createNewStudent(String firstName, String lastName, Faculty faculty, Group group,
                                 Specialty specialty, List<SubjectName> attendedSubjectName) {

        var newStudent = new Student(firstName, lastName, faculty, specialty, group, attendedSubjectName);
        students.students.add(newStudent);
        System.out.println(newStudent + " successfully added");
    }

    @Override
    public void addStudentToCourse(SubjectName subjectName) {
//        if (student.getAttendedSubject().size() < 3) {
//            student.getAttendedSubject().add(subjectName);
//        } else {
//            System.out.println("Sorry, the number of subjects has been exceeded");
//        }
    }

    @Override
    public void viewAllSubjects(int studentId) {
        students.students.stream()
                .filter(student -> student.getId() == studentId)
                .findFirst()
                .ifPresentOrElse(student -> {
                            student.getAttendedSubject()
                                    .stream().map(SubjectName::name)
                                    .forEach(subject -> System.out.println(" -" + subject));
                        },
                        () -> System.out.println("Student with ID " + studentId + " not found"));
    }

    @Override
    public void viewAllGrades() {
//        student.getGrades().entrySet().stream().map(entry -> entry.getKey().name() + ": " + entry.getValue())
//                .forEach(System.out::println);
    }

    @Override
    public double averageGradeOfSubject() {
//        int totalGrades = 0;
//        int numberOfSubject = 0;
//        System.out.println("Average grade of subject");
//        for(int grade: student.getGrades().values()) {
//            totalGrades += grade;
//            numberOfSubject++;
//        }
//        return (double) totalGrades / numberOfSubject;
//    }
        return 0;
    }
}
