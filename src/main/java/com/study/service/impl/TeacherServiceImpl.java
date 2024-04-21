package com.study.service.impl;

import com.study.dao.core.Group;
import com.study.dao.core.Subject;
import com.study.dao.data.GroupList;
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
    private TeacherList teachers;
    private StudentList students;
    private TeacherMapper teacherMapper;
    private GroupList groups;

    public TeacherServiceImpl(StudentList students, TeacherMapper teacherMapper, GroupList groups, TeacherList teachers) {
        this.teachers = teachers;
        this.groups = groups;
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
    public List<Student> viewEnrolledStudents(String teacherId) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        if (!groups.isTeacherAssignedToGroup(teacherId)) {
            throw new NotFoundException("This teacher " + foundTeacher.getFirstName()
                                        + " " + foundTeacher.getLastName() + " not joined any group");
        }

        List<Student> studentsEnrolledToSubject = new ArrayList<>();

        students.getStudents()
                .stream()
                .filter(student -> student.getSubjects().stream()
                        .anyMatch(subject -> subject.getName().equalsIgnoreCase(foundTeacher.getSubject().getName())))
                .filter(student -> foundTeacher.getGroups().stream().anyMatch(group -> group.getName().equals(student.getGroup().getName())))
                .forEach(studentsEnrolledToSubject::add);

        return studentsEnrolledToSubject;
    }

    @Override
    public UUID removeTeacher(String teacherId) {
        groups.removeTeacherFromAllGroups(teacherId);
        return teachers.deleteTeacher(teacherId);
    }

    @Override
    public HashMap<Subject, List<Integer>> evaluateStudent(String teacherId,
                                                           String studentId,
                                                           List<Integer> newGrades) {

        Student foundStudent = students.findStudentById(studentId);
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        Group studentGroup = groups.getStudentGroup(foundStudent);
        Set<Group> teacherGroups = groups.getTeacherGroups(foundTeacher);

        Subject subject = foundTeacher.getSubject();

        if (foundStudent.getSubjects().stream().noneMatch(currentSubject ->
                currentSubject.getName().equalsIgnoreCase(subject.getName()))) {
            throw new NotFoundException("Sorry, this student not study " + subject);
        }

        if (!teacherGroups.contains(studentGroup)) {
            throw new NotFoundException("Sorry, this teacher " + foundTeacher
                                        + " don't assigned to this group");
        }

        if (foundStudent.getSubjects().stream()
                .noneMatch(currentSubject -> currentSubject.getName()
                        .equals(foundTeacher.getSubject().getName()))) {
            throw new NotFoundException("Sorry, this teacher " + foundTeacher + " does not teach this subject");
        }

        HashMap<Subject, List<Integer>> result = new HashMap<>();

        HashMap<Subject, List<Integer>> grades = foundStudent.getGrades();

        List<Integer> gradesForSubject = grades
                .computeIfAbsent(subject, k -> new ArrayList<>());

        gradesForSubject.addAll(newGrades);
        result.put(subject, gradesForSubject);

        return result;
    }

    @Override
    public Teacher assignTeacherToGroup(String teacherId, String group) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        foundTeacher.getGroups().add(new Group(group));
        groups.assignTeacherToGroup(foundTeacher, group);

        return foundTeacher;
    }

    @Override
    public Teacher removeTeacherFromGroup(String teacherId, String groupName) {
        Teacher foundTeacher = teachers.findTeacherById(teacherId);

        groups.removeTeacherFromGroup(teacherId, groupName);
        foundTeacher.getGroups().removeIf(group -> group.getName().equals(groupName));

        return foundTeacher;
    }

    @Override
    public Set<Teacher> getTeacherByGroup(String group) {
        return groups.getTeachersByGroup(group);
    }

    @Override
    public List<Teacher> viewTeachers() {
        return teachers.getTeachers();
    }
}
