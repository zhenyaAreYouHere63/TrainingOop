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
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.study.dao.SubjectType.OPTIONAL;
import static com.study.dao.SubjectType.COMPULSORY;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    @Mock
    private StudentMapper studentMapper;

    private StudentDto studentDto;

    private String studentId;

    @BeforeEach
    public void setUpData() {
        studentId = UUID.randomUUID().toString();

        studentDto = new StudentDto("userName", "userLastName", "Sociology",
                "Sociology", new GroupDto("REE_11"),
                new HashSet<>(Arrays.asList(new SubjectDtoForStudent("Math", COMPULSORY),
                        new SubjectDtoForStudent("IT", OPTIONAL))));
    }

    @Test
    void addStudent_shouldCreateStudentAndReturnStudentId() {
        when(studentService.createStudent(studentDto)).thenReturn(UUID.fromString(studentId));

        studentService.createStudent(studentDto);

        verify(studentService, times(1)).createStudent(studentDto);
    }

    @Test
    void deleteStudent_shouldDeleteUserAndReturnStudentId() {
        when(studentService.deleteStudent(studentId)).thenReturn(UUID.fromString(studentId));

        studentService.deleteStudent(studentId);

        verify(studentService, times(1)).deleteStudent(studentId);
    }

    @Test
    void addStudentToCourse_ShouldAddStudentToCourse() {
        Subject subject = new Subject("Math");

        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject("Physics"));
        subjects.add(subject);

        when(studentService.addStudentToCourse(studentId, subject)).thenReturn(subjects);

        studentService.addStudentToCourse(studentId, subject);

        verify(studentService, times(1)).addStudentToCourse(studentId, subject);
    }

    @Test
    void getAllSubjectList_shouldGetAllSubject() {
        Set<Subject> subjects = new HashSet<>();
        subjects.add(new Subject("Math", COMPULSORY));
        subjects.add(new Subject("Physics", OPTIONAL));

        when(studentService.viewAllSubjects(studentId)).thenReturn(subjects);

        studentService.viewAllSubjects(studentId);

        verify(studentService, times(1)).viewAllSubjects(studentId);
    }

    @Test
    void getAllGrades_shouldGetAllGrades() {
        HashMap<Subject, List<Integer>> gradesForSubject = new HashMap<>();
        List<Integer> grades = List.of(90, 95, 75);
        gradesForSubject.put(new Subject("Math"), grades);

        when(studentService.viewAllGrades(studentId)).thenReturn(gradesForSubject);

        studentService.viewAllGrades(studentId);

        verify(studentService, times(1)).viewAllGrades(studentId);
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
