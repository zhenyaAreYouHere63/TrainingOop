package com.study.controller;

import static com.study.dao.SubjectType.COMPULSORY;
import static com.study.dao.SubjectType.OPTIONAL;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dto.GroupDto;
import com.study.dto.StudentDto;
import com.study.dto.SubjectDtoForStudent;
import com.study.mapper.StudentMapper;
import com.study.service.StudentService;
import com.study.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

class StudentControllerTest {

    @InjectMocks
    StudentController studentController;

    @Mock
    private StudentList studentList;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private GroupList groupList;

    @Mock
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentServiceImpl(studentList, studentMapper, groupList);
        studentController = new StudentController(studentList, studentMapper, groupList);
    }

    @Test
    void addStudent() {
        UUID randomUuid = UUID.randomUUID();

        StudentDto studentDto = new StudentDto("userName", "userLastName", "Sociology",
                "Sociology", new GroupDto("REE_11"),
                new HashSet<>(Arrays.asList(new SubjectDtoForStudent("Math", COMPULSORY),
                new SubjectDtoForStudent("IT", OPTIONAL))));

        when(studentService.createStudent(studentDto)).thenReturn(randomUuid);

        studentController.addStudent(studentDto);

        verify(studentService, times(1)).createStudent(studentDto);
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void addStudentToCourse() {
    }

    @Test
    void getAllSubjectList() {
    }

    @Test
    void getAllGrades() {
    }

    @Test
    void getAverageGrade() {
    }

    @Test
    void getAllStudentList() {
    }

    @Test
    void validateId() {
    }
}