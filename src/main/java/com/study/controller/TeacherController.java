package com.study.controller;

import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dao.data.GroupList;
import com.study.dao.data.StudentList;
import com.study.dao.data.TeacherList;
import com.study.dto.TeacherDto;
import com.study.mapper.TeacherMapper;
import com.study.service.TeacherService;
import com.study.service.exception.IncorrectIdException;
import com.study.service.impl.TeacherServiceImpl;
import com.study.service.validation.IdValidator;
import java.util.*;

public class TeacherController implements IdValidator {
    private TeacherService teacherService;

    public TeacherController(StudentList students, TeacherMapper teacherMapper, GroupList groups, TeacherList teachers) {
        teacherService = new TeacherServiceImpl(students, teacherMapper, groups, teachers);
    }

    public void addTeacher(TeacherDto teacherDto) {
        List<Exception> maybeExceptions = TeacherDto.validateTeacherDto(teacherDto.firstName(), teacherDto.lastName(), teacherDto.subject());

        if(!maybeExceptions.isEmpty()) {
            System.out.println("Validation exceptions");
            for (Exception exception: maybeExceptions) {
                System.out.println(exception);
            }
            return;
        }

        UUID teacherUuid = teacherService.createNewTeacher(teacherDto);
        System.out.println(teacherUuid);
    }

    public void deleteTeacher(int teacherId) {
        UUID deletedTeacherUuid = teacherService.removeTeacher(teacherId);
        System.out.println(deletedTeacherUuid);
    }

    public void getAllStudentsForTeacherSubject(int teacherId) {
        validateId(teacherId);

        List<Student> students = teacherService.viewEnrolledStudents(teacherId);

        students.forEach(System.out::println);
    }

    public void evaluateStudent(int teacherId, int studentId, String subject, List<Integer> newGrades) {
        validateId(studentId);

        HashMap<Subject, List<Integer>> evaluateGradesForSubject = teacherService.evaluateStudent(teacherId, studentId, subject, newGrades);

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
        List<Teacher> teachers = teacherService.viewTeachers();

        teachers.forEach(System.out::println);
    }

    public void addTeacherToGroup(int teacherId, String group) {
        validateId(teacherId);

        Teacher teacher = teacherService.assignTeacherToGroup(teacherId, group);
        System.out.println("Teacher " + teacher.getFirstName() + " " + teacher.getLastName() + " successfully added to name " + group);
    }

    public void getTeachersByGroup(String group) {
        Set<Teacher> teachersByGroup = teacherService.getTeacherByGroup(group);
        teachersByGroup.forEach(System.out::println);
    }

    public void removeTeacherFromGroup(int teacherId, String group) {
        Teacher teacher = teacherService.removeTeacherFromGroup(teacherId, group);
        System.out.println("Teacher: " + teacher + " has been removed from the group " + group);
    }

    public void validateId(int id) {
        if (id <= 0) {
            throw new IncorrectIdException("Id cannot be less than 1");
        }
    }
}
