package com.study.controller;

import com.study.dao.SubjectType;
import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dto.SubjectDtoForTeacher;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import com.study.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    @InjectMocks
    private TeacherController teacherController;

    @Mock
    private TeacherService teacherService;

    @Mock
    private TeacherMapper teacherMapper;

    private TeacherDto teacherDto;

    @Test
    void addTeacher_shouldCreateTeacherAndReturnId() {
        UUID randomUuid = UUID.randomUUID();

        teacherDto = new TeacherDto("userName", "lastName", new SubjectDtoForTeacher("History"));

        when(teacherService.createNewTeacher(teacherDto)).thenReturn(randomUuid);

        teacherService.createNewTeacher(teacherDto);

        verify(teacherService, times(1)).createNewTeacher(teacherDto);
    }

    @Test
    void deleteTeacher_shouldDeleteTeacherAndReturnId() {
        String randomUuid = UUID.randomUUID().toString();

        when(teacherService.removeTeacher(randomUuid)).thenReturn(UUID.fromString(randomUuid));

        teacherService.removeTeacher(randomUuid);

        verify(teacherService, times(1)).removeTeacher(randomUuid);
    }

    @Test
    void getAllStudentsForTeacherSubject_shouldGetAllStudentsForTeacherSubject() {
        String randomUuid = UUID.randomUUID().toString();

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, UUID.randomUUID(), "Username", "Lastname", "Sociology", "Radio_electronics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("IT", SubjectType.COMPULSORY), new Subject("Chemistry", SubjectType.COMPULSORY)))));

        when(teacherService.viewEnrolledStudents(randomUuid)).thenReturn(students);

        teacherService.viewEnrolledStudents(randomUuid);

        verify(teacherService, times(1)).viewEnrolledStudents(randomUuid);
    }

    @Test
    void evaluateStudent_shouldEvaluateStudent() {
        String teacherUuid = UUID.randomUUID().toString();
        String studentUuid = UUID.randomUUID().toString();

        HashMap<Subject, List<Integer>> gradesForSubject = new HashMap<>();
        List<Integer> grades = List.of(90, 95, 75);
        gradesForSubject.put(new Subject("Math"), grades);

        when(teacherService.evaluateStudent(teacherUuid, studentUuid, grades))
                .thenReturn(gradesForSubject);

        teacherService.evaluateStudent(teacherUuid, studentUuid, grades);

        verify(teacherService, times(1)).evaluateStudent(teacherUuid, studentUuid, grades);
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
        String randomUuid = UUID.randomUUID().toString();
        String group = "SP_11";
        Teacher teacher = new Teacher(1, UUID.fromString(randomUuid), "firstName", "lastName",
                new Subject("IT"));

        when(teacherService.assignTeacherToGroup(randomUuid, group)).thenReturn(teacher);

        teacherService.assignTeacherToGroup(randomUuid, group);

        verify(teacherService, times(1)).assignTeacherToGroup(randomUuid, group);
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
        String randomUuid = UUID.randomUUID().toString();
        String group = "REE_11";
        Teacher teacher = new Teacher(1, UUID.randomUUID(), "firstName", "lastName",
                new Subject("Math"));

        when(teacherService.removeTeacherFromGroup(randomUuid, group)).thenReturn(teacher);

        teacherService.removeTeacherFromGroup(randomUuid, group);

        verify(teacherService, times(1)).removeTeacherFromGroup(randomUuid, group);
    }
}