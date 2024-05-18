package com.study.controller;

import com.study.dao.SubjectType;
import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dto.TeacherDto;
import com.study.dto.SubjectDtoForTeacher;
import com.study.mapper.TeacherMapper;
import com.study.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.HashSet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    @InjectMocks
    private TeacherController teacherController;

    @Mock
    private TeacherService teacherService;

    @Mock
    private TeacherMapper teacherMapper;

    private TeacherDto teacherDto;

    private String teacherId;

    @BeforeEach
    public void setUpData() {
        teacherId = UUID.randomUUID().toString();

        teacherDto = new TeacherDto("userName", "lastName", new SubjectDtoForTeacher("History"));
    }

    @Test
    void addTeacher_shouldCreateTeacherAndReturnId() {
        when(teacherService.createNewTeacher(teacherDto)).thenReturn(UUID.fromString(teacherId));

        teacherService.createNewTeacher(teacherDto);

        verify(teacherService, times(1)).createNewTeacher(teacherDto);
    }

    @Test
    void deleteTeacher_shouldDeleteTeacherAndReturnId() {
        teacherService.removeTeacher(teacherId);

        verify(teacherService, times(1)).removeTeacher(teacherId);
    }

    @Test
    void getAllStudentsForTeacherSubject_shouldGetAllStudentsForTeacherSubject() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, UUID.randomUUID(), "Username", "Lastname", "Sociology", "Radio_electronics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("IT", SubjectType.COMPULSORY), new Subject("Chemistry", SubjectType.COMPULSORY)))));

        when(teacherService.viewEnrolledStudents(teacherId)).thenReturn(students);

        teacherService.viewEnrolledStudents(teacherId);

        verify(teacherService, times(1)).viewEnrolledStudents(teacherId);
    }

    @Test
    void evaluateStudent_shouldEvaluateStudent() {
        String studentUuid = UUID.randomUUID().toString();

        HashMap<Subject, List<Integer>> gradesForSubject = new HashMap<>();
        List<Integer> grades = List.of(90, 95, 75);
        gradesForSubject.put(new Subject("Math"), grades);

        when(teacherService.evaluateStudent(teacherId, studentUuid, grades))
                .thenReturn(gradesForSubject);

        teacherService.evaluateStudent(teacherId, studentUuid, grades);

        verify(teacherService, times(1)).evaluateStudent(teacherId, studentUuid, grades);
    }

    @Test
    void getAllTeacherList_shouldGetTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1, UUID.randomUUID(), "firstName", "lastName", new Subject("Math")));

        when(teacherService.viewTeachers()).thenReturn(teachers);

        teacherService.viewTeachers();

        verify(teacherService, times(1)).viewTeachers();
    }

    @Test
    void addTeacherToGroup() {
        String group = "SP_11";
        Teacher teacher = new Teacher(1, UUID.fromString(teacherId), "firstName", "lastName",
                new Subject("IT"));

        when(teacherService.assignTeacherToGroup(teacherId, group)).thenReturn(teacher);

        teacherService.assignTeacherToGroup(teacherId, group);

        verify(teacherService, times(1)).assignTeacherToGroup(teacherId, group);
    }

    @Test
    void getTeachersByGroup() {
        String group = "SP_11";
        Set<Teacher> teachersByGroup = new HashSet<>();
        teachersByGroup.add(new Teacher(1, UUID.randomUUID(), "firstName", "lastName",
                new Subject("Math")));

        when(teacherService.getTeachersByGroup(group)).thenReturn(teachersByGroup);

        teacherService.getTeachersByGroup(group);

        verify(teacherService, times(1)).getTeachersByGroup(group);
    }

    @Test
    void removeTeacherFromGroup() {
        String group = "REE_11";
        Teacher teacher = new Teacher(1, UUID.randomUUID(), "firstName", "lastName",
                new Subject("Math"));

        when(teacherService.removeTeacherFromGroup(teacherId, group)).thenReturn(teacher);

        teacherService.removeTeacherFromGroup(teacherId, group);

        verify(teacherService, times(1)).removeTeacherFromGroup(teacherId, group);
    }
}