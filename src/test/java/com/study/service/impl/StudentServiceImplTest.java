package com.study.service.impl;

import com.study.dao.SubjectType;
import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dto.GroupDto;
import com.study.dto.StudentDto;
import com.study.dto.SubjectDtoForStudent;
import com.study.mapper.StudentMapper;
import com.study.service.exception.MaxSubjectException;
import com.study.service.exception.RepeatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Set;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;
import java.util.HashSet;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentList students;

    @Mock
    private StudentMapper studentMapper;

    @Mock
    private GroupList groups;

    private String studentId;

    private StudentDto studentDto;

    private Student student;

    @BeforeEach
    public void setUpData() {
        studentId = UUID.randomUUID().toString();

        student = new Student(1, UUID.fromString(studentId), "userName", "lastName", "Sociology", "Radio_electronics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("IT", SubjectType.COMPULSORY), new Subject("Chemistry", SubjectType.COMPULSORY))));

        studentDto = new StudentDto("userName", "lastName", "Sociology", "Radio_electronics", new GroupDto("SS_11"),
                new HashSet<>(List.of(new SubjectDtoForStudent("IT", SubjectType.COMPULSORY), new SubjectDtoForStudent("Chemistry", SubjectType.COMPULSORY))));
    }

    @Test
    void createStudent_shouldCreateStudentAndReturnStudentId() {
        when(studentMapper.mapStudentDtoToStudent(studentDto)).thenReturn(student);
        when(students.addStudent(student)).thenReturn(UUID.fromString(studentId));

        UUID actualUuid = studentService.createStudent(studentDto);

        assertThat(actualUuid).isEqualTo(UUID.fromString(studentId));
    }

    @Test
    void createStudent_shouldThrowExceptionWhenMaxOptimalSubjectExceeded() {
        Student student = new Student(1, UUID.fromString(studentId), "userName", "lastName", "Sociology", "Radio_electronics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("IT", SubjectType.COMPULSORY), new Subject("Chemistry", SubjectType.OPTIONAL),
                        new Subject("Physics", SubjectType.OPTIONAL), new Subject("History", SubjectType.OPTIONAL))));

        StudentDto studentDto = new StudentDto("Username", "Lastname", "Sociology", "Radio_electronics", new GroupDto("SS_11"),
                new HashSet<>(List.of(new SubjectDtoForStudent("IT", SubjectType.COMPULSORY), new SubjectDtoForStudent("Chemistry", SubjectType.OPTIONAL),
                        new SubjectDtoForStudent("Physics", SubjectType.OPTIONAL), new SubjectDtoForStudent("History", SubjectType.OPTIONAL))));

        when(studentMapper.mapStudentDtoToStudent(studentDto)).thenReturn(student);

        assertThrows(MaxSubjectException.class, () -> studentService.createStudent(studentDto));
    }

    @Test
    void deleteStudent_shouldDeleteStudent() {
        when(studentService.deleteStudent(studentId)).thenReturn(UUID.fromString(studentId));

        studentService.deleteStudent(studentId);

        verify(students, times(1)).deleteStudent(studentId);
    }

    @Test
    void addStudentToCourse_shouldAddStudentToCourse() {
        Subject subject = new Subject("Physics", SubjectType.OPTIONAL);

        Set<Subject> expectedSubjects = Set.of(subject,
                new Subject("IT", SubjectType.COMPULSORY),
                new Subject("Chemistry", SubjectType.COMPULSORY));

        when(students.findStudentById(studentId)).thenReturn(student);

        Set<Subject> actualSubjects = studentService.addStudentToCourse(studentId, subject);

        assertThat(actualSubjects).isEqualTo(expectedSubjects);
    }

    @Test
    void addStudentToCourse_shouldThrowExceptionWhenSubjectNameRepeated() {
        Subject subject = new Subject("IT", SubjectType.OPTIONAL);

        when(students.findStudentById(studentId)).thenReturn(student);

        assertThatThrownBy(() -> studentService.addStudentToCourse(studentId, subject))
                .isInstanceOf(RepeatException.class)
                .hasMessage("Student with id " + studentId + " is already studying this subject");
    }


    @Test
    void addStudentToCourse_shouldThrowExceptionWhenMaxOptimalSubjectExceeded() {
        Subject subject = new Subject("Biology", SubjectType.OPTIONAL);
        Student student = new Student(1, UUID.fromString(studentId), "userName", "lastName", "Sociology", "Radio_electronics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("IT", SubjectType.COMPULSORY), new Subject("Chemistry", SubjectType.OPTIONAL),
                        new Subject("Physics", SubjectType.OPTIONAL), new Subject("Art", SubjectType.OPTIONAL))));

        when(students.findStudentById(studentId)).thenReturn(student);

        assertThrows(MaxSubjectException.class, () -> studentService.addStudentToCourse(studentId, subject));
    }

    @Test
    void viewAllSubjects_shouldGetAllSubjects() {
        Set<Subject> expectedSubjects = Set.of(
                new Subject("IT", SubjectType.COMPULSORY), new Subject("Chemistry", SubjectType.COMPULSORY)
        );

        when(students.findStudentById(studentId)).thenReturn(student);

        Set<Subject> actualSubjects = studentService.viewAllSubjects(studentId);

        assertThat(actualSubjects).isEqualTo(expectedSubjects);
    }

    @Test
    void viewAllGrades_shouldGetAllGrades() {
        HashMap<Subject, List<Integer>> expectedGrades = new HashMap<>();
        List<Integer> gradesOfMath = List.of(90, 95, 75);
        expectedGrades.put(new Subject("IT"), gradesOfMath);

        List<Integer> gradesOfPhysics = List.of(70, 75, 85);
        expectedGrades.put(new Subject("Chemistry"), gradesOfPhysics);

        student.setGrades(expectedGrades);

        when(students.findStudentById(studentId)).thenReturn(student);

        HashMap<Subject, List<Integer>> actualGrades = studentService.viewAllGrades(studentId);

        assertThat(actualGrades).isEqualTo(expectedGrades);
    }

    @Test
    void averageGradeOfSubject_shouldGetAverageGrade() {
        Double expectedGrade = 95.0;
        Subject subject = new Subject("IT");

        HashMap<Subject, List<Integer>> expectedGrades = new HashMap<>();
        List<Integer> gradesOfMath = List.of(90, 100);
        expectedGrades.put(subject, gradesOfMath);
        student.setGrades(expectedGrades);

        when(students.findStudentById(studentId)).thenReturn(student);

        Double actualGrade = studentService.averageGradeOfSubject(studentId, subject.getName());

        assertThat(actualGrade).isEqualTo(expectedGrade);
    }

    @Test
    void viewAllStudents_shouldViewAllStudents() {
        Student student2 = new Student(2, UUID.randomUUID(), "userName2", "lastName2", "Management", "International_economics", new Group("MEC_11"),
                new HashSet<>(List.of(new Subject("History", SubjectType.OPTIONAL),
                        new Subject("Biology", SubjectType.COMPULSORY))));

        List<Student> expectedStudents = List.of(student, student2);

        when(students.viewAllStudents()).thenReturn(expectedStudents);

        List<Student> actualStudents = studentService.viewAllStudents();

        assertThat(actualStudents).isEqualTo(expectedStudents);
    }
}
