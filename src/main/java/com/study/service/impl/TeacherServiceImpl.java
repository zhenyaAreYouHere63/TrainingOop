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
        Teacher teacherToSave = teacherMapper.mapTeacherDtoToTeacher(teacherDto);
        return teachers.addTeacher(teacherToSave).getUuid();
    }

    @Override
    public List<Student> getEnrolledStudents(int teacherId) {
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
    public Map<Subject, List<Integer>> evaluateStudent(int studentId, String subject, List<Integer> newGrades) {

        Student foundStudent = students.findStudentById(studentId);

        teachers.findTeacherBySubject(subject); // if teacher doesn't exist, it throws an exeption

        Subject studentSubject = foundStudent.getSubjects().stream()
                .filter(currentSubject -> currentSubject.getName().equalsIgnoreCase(subject))
                .findAny()
                .orElseThrow(() -> new NotFoundException("Sorry, this student not study " + subject));

        Map<Subject, List<Integer>> grades = foundStudent.getGrades();

        //TODO: I would move this logic to the stusent subj enrollment part
        List<Integer> gradesForSubject = grades
                .computeIfAbsent(studentSubject, k -> new ArrayList<>());

        gradesForSubject.addAll(newGrades);

        Map<Subject, List<Integer>> result = new HashMap<>();
        result.put(studentSubject, gradesForSubject);

        return result;
    }

    @Override
    public Teacher assignTeacherToGroup(int teacherId, String groupName) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        Subject subject = foundTeacher.getSubject();

        if (!subject.isTeacherAssignedToGroup(groupName, subject.getName())) {
            teacher.getSubject().assignTeacherToGroup(groupName, teacher);
        } else {
            System.out.println("Error: Another teacher is already assigned to group ");
        }

        return teacher;
    }
    @Override
    public Teacher getTeacherByGroup(String subjectName, String group) {
        return teachers.getTeachers().stream()
                .map(t -> t.getSubject().getTeacherByGroupAndSubject(group, subjectName))
                .findFirst()
                .orElse(null); //TODO: ot would be better to throw exception
    }
    @Override
    public List<Teacher> getTeachers() {
        return teachers.getTeachers();
    }
}
