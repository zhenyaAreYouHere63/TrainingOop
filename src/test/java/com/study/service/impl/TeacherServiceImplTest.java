package com.study.service.impl;

import com.study.dao.SubjectType;
import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dto.SubjectDtoForTeacher;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import com.study.service.exception.NotFoundException;
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

@ExtendWith(MockitoExtension.class)
class TeacherServiceImplTest {

    @InjectMocks
    TeacherServiceImpl teacherService;

    @Mock
    TeacherList teachers;

    @Mock
    StudentList students;

    @Mock
    TeacherMapper teacherMapper;

    @Mock
    GroupList groups;

    private String teacherId;

    private String studentId;

    private TeacherDto teacherDto;

    private Teacher teacher;

    private Student student;

    @BeforeEach
    void setUp() {
        teacherId = UUID.randomUUID().toString();
        studentId = UUID.randomUUID().toString();

        teacherDto = new TeacherDto("firstName", "lastName", new SubjectDtoForTeacher("Math"));

        teacher = new Teacher(1, UUID.fromString(teacherId), "firstName", "lastName", new Subject("Math"));

        student = new Student(1, UUID.fromString(studentId), "userName", "lastName", "Sociology", "Radio_electronics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("Math", SubjectType.COMPULSORY))));
    }

    @Test
    void createNewTeacher_shouldCreateTeacherAndReturnTeacherId() {
        when(teacherMapper.mapTeacherDtoToTeacher(teacherDto)).thenReturn(teacher);
        when(teachers.addTeacher(teacher)).thenReturn(teacher);

        UUID actualUuid = teacherService.createNewTeacher(teacherDto);

        assertThat(actualUuid).isEqualTo(teacher.getUuid());
    }

    @Test
    void viewEnrolledStudents_shouldGetEnrolledStudentsToTeacherSubject() {
        teacher.getGroups().add(new Group("SS_11"));

        Student student2 = new Student(2, UUID.randomUUID(), "userName2", "lastName2", "Chemistry", "International_economics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("Math", SubjectType.OPTIONAL))));
        List<Student> expectedStudents = List.of(student, student2);

        when(teachers.findTeacherById(teacherId)).thenReturn(teacher);
        when(groups.isTeacherAssignedToGroup(teacherId)).thenReturn(true);
        when(students.getStudents()).thenReturn(expectedStudents);

        List<Student> actualStudents = teacherService.viewEnrolledStudents(teacherId);

        assertThat(actualStudents).isEqualTo(expectedStudents);
    }

    @Test
    void viewEnrolledStudents_shouldThrowExceptionWhenTeacherNotAssignedToGroup() {
        when(teachers.findTeacherById(teacherId)).thenReturn(teacher);
        when(groups.isTeacherAssignedToGroup(teacherId)).thenReturn(false);

        assertThatThrownBy(() -> teacherService.viewEnrolledStudents(teacherId))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("This teacher " + teacher.getFirstName()
                            + " " + teacher.getLastName() + " not joined any group");
    }

    @Test
    void removeTeacher_shouldRemoveTeacher() {
        when(teacherService.removeTeacher(teacherId)).thenReturn(UUID.fromString(teacherId));

        teacherService.removeTeacher(teacherId);

        verify(teachers, times(1)).deleteTeacher(teacherId);
    }

    @Test
    void evaluateStudent_shouldEvaluateStudent() {
        List<Integer> expectedGrades = List.of(80, 90);

        List<Integer> grades = List.of(80, 90);

        teacher.getGroups().add(student.getGroup());
        groups.assignTeacherToGroup(teacher, "SS_11");
        Group studentGroup = student.getGroup();
        Set<Group> teacherGroups = teacher.getGroups();

        when(teachers.findTeacherById(teacherId)).thenReturn(teacher);
        when(students.findStudentById(studentId)).thenReturn(student);
        when(groups.getStudentGroup(student)).thenReturn(studentGroup);
        when(groups.getTeacherGroups(teacher)).thenReturn(teacherGroups);

        HashMap<Subject, List<Integer>> actualGrades = teacherService.evaluateStudent(teacherId, studentId, grades);

        assertThat(actualGrades).containsValue(expectedGrades);
    }

    @Test
    void evaluateStudent_shouldThrowExceptionWhenStudentNotStudyCurrentTeacherSubject() {
        List<Integer> grades = List.of(80, 90);

        String studentId = UUID.randomUUID().toString();
        Student student = new Student(1, UUID.fromString(studentId), "firstName", "lastName", "Chemistry", "International_economics", new Group("SS_11"),
                new HashSet<>(List.of(new Subject("History", SubjectType.OPTIONAL))));

        teacher.getGroups().add(new Group("SS_11"));
        groups.assignTeacherToGroup(teacher, "SS_11");
        Group studentGroup = student.getGroup();
        Set<Group> teacherGroups = teacher.getGroups();

        when(teachers.findTeacherById(teacherId)).thenReturn(teacher);
        when(students.findStudentById(studentId)).thenReturn(student);
        when(groups.getStudentGroup(student)).thenReturn(studentGroup);
        when(groups.getTeacherGroups(teacher)).thenReturn(teacherGroups);

        assertThatThrownBy(() -> teacherService.evaluateStudent(teacherId, studentId, grades))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Sorry, this student not study " + teacher.getSubject());
    }

    @Test
    void evaluateStudent_shouldThrowExceptionWhenTeacherNotAssignedToThisGroup() {
        List<Integer> grades = List.of(80, 90);

        Group studentGroup = student.getGroup();
        Set<Group> teacherGroups = teacher.getGroups();

        when(teachers.findTeacherById(teacherId)).thenReturn(teacher);
        when(students.findStudentById(studentId)).thenReturn(student);
        when(groups.getStudentGroup(student)).thenReturn(studentGroup);
        when(groups.getTeacherGroups(teacher)).thenReturn(teacherGroups);

        assertThatThrownBy(() -> teacherService.evaluateStudent(teacherId, studentId, grades))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("Sorry, this teacher " + teacher + " don't assigned to this group");
    }

    @Test
    void assignTeacherToGroup_shouldAssignTeacherToGroup() {
        when(teachers.findTeacherById(teacherId)).thenReturn(teacher);

        Teacher actualTeacher = teacherService.assignTeacherToGroup(teacherId, "SS_11");

        assertThat(actualTeacher).isEqualTo(teacher);
    }

    @Test
    void removeTeacherFromGroup_shouldRemoveTeacherFromGroup() {
        when(teachers.findTeacherById(teacherId)).thenReturn(teacher);

        Teacher actualTeacher = teacherService.removeTeacherFromGroup(teacherId, "SS_11");

        assertThat(actualTeacher).isEqualTo(teacher);
    }

    @Test
    void getTeachersByGroup_shouldGetTeachersByGroup() {
        Group group = new Group("SS_11");

        teacher.getGroups().add(group);

        Teacher teacher2 = new Teacher(2, UUID.randomUUID(), "firstName2",
                "lastName2", new Subject("History"));
        teacher2.getGroups().add(group);

        Set<Teacher> expectedTeachers = Set.of(teacher, teacher2);

        when(groups.getTeachersByGroup(group.getName())).thenReturn(expectedTeachers);

        Set<Teacher> actualTeachers = teacherService.getTeachersByGroup(group.getName());

        assertThat(actualTeachers).isEqualTo(expectedTeachers);
    }

    @Test
    void viewTeachers_shouldGetAllTeachers() {
        Teacher teacher2 = new Teacher(2, UUID.randomUUID(), "firstName2",
                "lastName2", new Subject("History"));

        List<Teacher> expectedTeachers = List.of(teacher, teacher2);

        when(teachers.getTeachers()).thenReturn(expectedTeachers);

        List<Teacher> actualTeachers = teacherService.viewTeachers();

        assertThat(actualTeachers).isEqualTo(expectedTeachers);
    }
}
