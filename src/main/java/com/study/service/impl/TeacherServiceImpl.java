package com.study.service.impl;

import com.study.dao.core.Subject;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dao.core.Student;
import com.study.dao.core.Teacher;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import com.study.service.TeacherService;
import com.study.service.exception.NotFoundException;
import java.util.*;

public class TeacherServiceImpl implements TeacherService {
    private final TeacherList teachers;
    private final Teacher teacher;
    private StudentList students;
    private TeacherMapper teacherMapper;

    public TeacherServiceImpl(StudentList students, TeacherMapper teacherMapper) {
        this.teacher = new Teacher();
        this.teachers = new TeacherList();
        this.students = students;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public UUID createNewTeacher(TeacherDto teacherDto) {
        Teacher mappedTeacher = teacherMapper.mapTeacherDtoToTeacher(teacherDto);

        Teacher savedTeacher = teachers.addTeacher(mappedTeacher);

        return savedTeacher.getUuid();
    }

    @Override
    public List<Student> viewEnrolledStudents(int teacherId) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        List<Student> studentsEnrolledToSubject = new ArrayList<>();

        students.students
                .stream()
                .filter(student -> student.getSubjects().stream()
                        .anyMatch(subject -> subject.getName().equalsIgnoreCase(foundTeacher.getSubject().getName())))
                .forEach(studentsEnrolledToSubject::add);

        return studentsEnrolledToSubject;
    }

    @Override
    public UUID deleteTeacher(int teacherId) {
        return teachers.deleteTeacher(teacherId);
    }

    @Override
    public HashMap<Subject, List<Integer>> evaluateStudent(int studentId, String subject, List<Integer> newGrades) {

        Student foundStudent = students.findStudentById(studentId);

        Teacher foundTeacher = teachers.findTeacherBySubject(subject);

        HashMap<Subject, List<Integer>> result = new HashMap<>();

        Optional<Subject> optionalSubject = foundStudent.getSubjects().stream()
                .filter(currentSubject -> currentSubject.getName().equalsIgnoreCase(subject))
                .findAny();

        if (optionalSubject.isEmpty()) {
            throw new NotFoundException("Sorry, this student not study " + subject);
        } else {
            HashMap<Subject, List<Integer>> grades = foundStudent.getGrades();

            List<Integer> gradesForSubject = grades
                    .computeIfAbsent(optionalSubject.get(), k -> new ArrayList<>());

            gradesForSubject.addAll(newGrades);

            result.put(optionalSubject.get(), gradesForSubject);

            return result;
        }
    }

    @Override
    public Teacher assignTeacherToGroup(int teacherId, String group) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        Subject subject = foundTeacher.getSubject();

        if (!subject.isTeacherAssignedToGroup(group, subject.getName())) {
            teacher.getSubject().assignTeacherToGroup(group, teacher);
        } else {
            System.out.println("Error: Another teacher is already assigned to group ");
        }

        return teacher;
    }
    @Override
    public Teacher getTeacherByGroup(String subjectName, String group) {
        return teachers.teachers.stream()
                .map(t -> t.getSubject().getTeacherByGroupAndSubject(group, subjectName))
                .findFirst()
                .orElse(null);
    }
    @Override
    public List<Teacher> viewTeachers() {
        return teachers.teachers;
    }
}
