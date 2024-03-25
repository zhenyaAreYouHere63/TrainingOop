package com.study.controller;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dao.data.StudentList;
import com.study.dto.TeacherDto;
import com.study.dto.ValidationResult;
import com.study.mapper.TeacherMapper;
import com.study.service.TeacherService;
import com.study.service.exception.IncorrectIdException;
import com.study.service.impl.TeacherServiceImpl;
import com.study.service.validation.IdValidator;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class TeacherController implements IdValidator {
    private TeacherService teacherService;

    public TeacherController(StudentList students, TeacherMapper teacherMapper) {
        teacherService = new TeacherServiceImpl(students, teacherMapper);
    }

    public void addTeacher(TeacherDto teacherDto) {
        List<ValidationResult> validationErrors = TeacherDto.validateTeacherDto(teacherDto.firstName(), teacherDto.lastName(), teacherDto.subject());

        if(!validationErrors.isEmpty()) {
            System.out.println("Validation exceptions");
            validationErrors.forEach(System.out::println);
            return;
        }

        UUID teacherUuid = teacherService.createNewTeacher(teacherDto);
        System.out.printf("\n Teacher with id %s was saved%n", teacherUuid.toString());
    }

    public void deleteTeacher(int teacherId) {
        UUID deletedTeacherUuid = teacherService.deleteTeacher(teacherId);
        System.out.println(deletedTeacherUuid);
    }

    public void getAllStudentsForTeacherSubject(int teacherId) {
        validateId(teacherId);
        List<Student> students = teacherService.getEnrolledStudents(teacherId);
        students.forEach(System.out::println);
    }

    public void evaluateStudent(int studentId, String subject, List<Integer> newGrades) {
        validateId(studentId);

        Map<Subject, List<Integer>> evaluateGradesForSubject = teacherService.evaluateStudent(studentId, subject, newGrades);

        for (Map.Entry<Subject, List<Integer>> entry : evaluateGradesForSubject.entrySet()) {
            String subjectToEvaluate = entry.getKey().getName();
            List<Integer> grades = entry.getValue();

            System.out.println("SubjectType: " + subjectToEvaluate);

            for (Integer grade : grades) {
                System.out.println("Grade: " + grade);
            }
        }
    }

    public void getAllTeacherList() {
        List<Teacher> teachers = teacherService.getTeachers();
        teachers.forEach(System.out::println);
    }

    public void addTeacherToGroup(int teacherId, String group) {
        validateId(teacherId);

        Teacher teacher = teacherService.assignTeacherToGroup(teacherId, group);
        System.out.println("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " successfully added to group " + group);
    }

    public void getTeacherByGroup(String subjectName, String group) {
        Teacher teacherByGroup = teacherService.getTeacherByGroup(subjectName, group);
        System.out.println(teacherByGroup);
    }

    //TODO: move to a separate class - IdValidator (it might be even a static method)
    public void validateId(int id) {
        if (id <= 0) {
            throw new IncorrectIdException("Id cannot be less than 1");
        }
    }
}
