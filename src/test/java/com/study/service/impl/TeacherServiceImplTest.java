package com.study.service.impl;

import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dto.SubjectDtoForTeacher;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    @InjectMocks
    TeacherServiceImpl teacherService;

    @Mock
    TeacherList teacherList;

    @Mock
    StudentList students;

    @Mock
    TeacherMapper teacherMapper;

    @Mock
    GroupList list;

    private String teacherId;

    private TeacherDto teacherDto;

    private Teacher teacher;

    @BeforeEach
    void setUp() {
        teacherId = UUID.randomUUID().toString();

        teacherDto = new TeacherDto("firstName", "lastName", new SubjectDtoForTeacher("Math"));

        teacher = new Teacher(1, UUID.fromString(teacherId), "firstName", "lastName", new Subject("Math"));
    }

    @Test
    void createNewTeacher() {

    }

    @Test
    void viewEnrolledStudents() {

    }

    @Test
    void removeTeacher() {

    }

    @Test
    void evaluateStudent() {

    }

    @Test
    void assignTeacherToGroup() {

    }

    @Test
    void removeTeacherFromGroup() {

    }

    @Test
    void getTeachersByGroup() {

    }

    @Test
    void viewTeachers() {

    }
}