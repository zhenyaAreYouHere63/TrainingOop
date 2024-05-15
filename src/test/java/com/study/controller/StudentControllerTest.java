package com.study.controller;

import com.study.dao.SubjectType;
import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dto.GroupDto;
import com.study.dto.StudentDto;
import com.study.dto.SubjectDtoForStudent;
import com.study.mapper.StudentMapper;
import com.study.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.study.dao.SubjectType.OPTIONAL;
import static com.study.dao.SubjectType.COMPULSORY;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapper;

    private StudentDto studentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStudent_shouldCreateStudentAndReturnStudentId() {
        UUID randomUuid = UUID.randomUUID();

        studentDto = new StudentDto("userName", "userLastName", "Sociology",
                "Sociology", new GroupDto("REE_11"),
                new HashSet<>(Arrays.asList(new SubjectDtoForStudent("Math", COMPULSORY),
                new SubjectDtoForStudent("IT", OPTIONAL))));

        when(studentService.createStudent(studentDto)).thenReturn(randomUuid);

        studentService.createStudent(studentDto);

        verify(studentService, times(1)).createStudent(studentDto);
    }

    @Test
    void deleteStudent_shouldDeleteUserAndReturnStudentId() {
        String randomUuid = UUID.randomUUID().toString();

        when(studentService.deleteStudent(randomUuid)).thenReturn(UUID.fromString(randomUuid));

        studentService.deleteStudent(randomUuid);

        verify(studentService, times(1)).deleteStudent(randomUuid);
    }

    @Test
    void addStudentToCourse_ShouldAddStudentToCourse() {
        String randomUuid = UUID.randomUUID().toString();
        Subject subject = new Subject("Math");

        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject("Physics"));
        subjects.add(subject);

        when(studentService.addStudentToCourse(randomUuid, subject)).thenReturn(subjects);

        studentService.addStudentToCourse(randomUuid, subject);

        verify(studentService, times(1)).addStudentToCourse(randomUuid, subject);
    }

    @Test
    void getAllSubjectList_shouldGetAllSubject() {
        String randomUuid = UUID.randomUUID().toString();

        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject("Math", COMPULSORY));
        subjects.add(new Subject("Physics", OPTIONAL));

        when(studentService.viewAllSubjects(randomUuid)).thenReturn(subjects);

        studentService.viewAllSubjects(randomUuid);

        verify(studentService, times(1)).viewAllSubjects(randomUuid);
    }

    @Test
    void getAllGrades_shouldGetAllGrades() {
        String randomUuid = UUID.randomUUID().toString();

        HashMap<Subject, List<Integer>> gradesForSubject = new HashMap<>();
        List<Integer> grades = List.of(90, 95, 75);
        gradesForSubject.put(new Subject("Math"), grades);

        when(studentService.viewAllGrades(randomUuid)).thenReturn(gradesForSubject);

        studentService.viewAllGrades(randomUuid);

        verify(studentService, times(1)).viewAllGrades(randomUuid);
    }

    @Test
    void getAverageGrade_shouldGetAverageGrade() {
        String randomUuid = UUID.randomUUID().toString();
        String subject = "Math";
        Double grade = 90.0;

        when(studentService.averageGradeOfSubject(randomUuid, subject)).thenReturn(grade);

        studentService.averageGradeOfSubject(randomUuid, subject);

        verify(studentService, times(1)).averageGradeOfSubject(randomUuid, subject);
    }

    @Test
    void getAllStudentList_shouldGetAllStudentList() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, UUID.randomUUID(), "Username", "Lastname", "Sociology", "Radio_electronics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("IT", SubjectType.COMPULSORY), new Subject("Chemistry", SubjectType.COMPULSORY)))));

        when(studentService.viewAllStudents()).thenReturn(students);

        studentService.viewAllStudents();

        verify(studentService, times(1)).viewAllStudents();
    }

    @Test
    void validateId_shouldThrowExceptionWhenIdEmpty() {
        String id = "";

        assertThrows(IllegalArgumentException.class, () ->
                studentController.validateId(id));
    }

    @Test
    void validateId_shouldNotThrowException() {
        String id = "123";

        assertDoesNotThrow(() ->
                studentController.validateId(id));
    }
}
