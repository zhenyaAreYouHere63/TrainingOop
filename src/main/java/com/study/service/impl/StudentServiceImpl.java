package com.study.service.impl;

import com.study.dao.SubjectType;
import com.study.dao.core.Subject;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dao.core.Student;
import com.study.dto.StudentDto;
import com.study.mapper.StudentMapper;
import com.study.service.StudentService;
import com.study.service.exception.MaxSubjectException;
import com.study.service.exception.RepeatException;
import java.util.UUID;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class StudentServiceImpl implements StudentService {

    private StudentList students;

    private StudentMapper studentMapper;

    private GroupList groups;

    public StudentServiceImpl(StudentList students, StudentMapper studentMapper, GroupList groups) {
        this.groups = groups;
        this.students = students;
        this.studentMapper = studentMapper;
    }

    @Override
    public UUID createStudent(StudentDto studentDto) {
        Student mappedStudent = studentMapper.mapStudentDtoToStudent(studentDto);

        groups.assignStudentToGroup(mappedStudent);

        checkedExceededSubjects(mappedStudent);

        return students.addStudent(mappedStudent);

    }

    @Override
    public UUID deleteStudent(int studentId) {

        groups.removeStudentFromGroup(studentId);
        return students.deleteStudent(studentId);
    }

    @Override
    public Set<Subject> addStudentToCourse(int studentId, Subject subject) {
        Student foundStudent = students.findStudentById(studentId);

        Set<Subject> studentSubjects = foundStudent.getSubjects();

        Optional<Subject> maybeRepeatSubject = studentSubjects.stream().filter(foundSubject ->
                        foundSubject.getName().equalsIgnoreCase(subject.getName()))
                .findFirst();

        if (maybeRepeatSubject.isPresent()) {
            throw new RepeatException("Student with id " + studentId + " is already studying this subject");
        }

        checkedExceededSubjects(foundStudent);

        foundStudent.getSubjects().add(subject);

        return foundStudent.getSubjects();
    }

    @Override
    public Set<Subject> viewAllSubjects(int studentId) {
        return students.findStudentById(studentId).getSubjects();
    }

    @Override
    public HashMap<Subject, List<Integer>> viewAllGrades(int studentId) {
        return students.findStudentById(studentId).getGrades();
    }

    @Override
    public Double averageGradeOfSubject(int studentId, String subject) {
        Student foundStudent = students.findStudentById(studentId);

        Map<Subject, List<Integer>> studentGrades = foundStudent.getGrades();

        return studentGrades.entrySet().stream().filter(currentSubject -> currentSubject
                        .getKey().getName().equalsIgnoreCase(subject))
                .flatMapToInt(entry -> entry.getValue().stream().mapToInt(Integer::intValue))
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Student> viewAllStudents() {
        return students.viewAllStudents();
    }

    private void checkedExceededSubjects(Student mappedStudent) {
        Set<Subject> studentSubjects = mappedStudent.getSubjects();

        long countOfOptionalSubjects = studentSubjects.stream()
                .filter(foundsubject -> foundsubject.getType() == SubjectType.OPTIONAL).count();

        if (countOfOptionalSubjects >= 3) {
            throw new MaxSubjectException("Sorry, the number of subjects has been exceeded");
        }
    }
}
