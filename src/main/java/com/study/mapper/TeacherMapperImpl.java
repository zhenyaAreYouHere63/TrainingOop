package com.study.mapper;

import com.study.dao.Counter;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dto.SubjectDtoForTeacher;
import com.study.dto.TeacherDto;
import java.util.UUID;

public class TeacherMapperImpl implements TeacherMapper {
    private static final Counter counter = Counter.getTeacherInstance();

    @Override
    public Teacher mapTeacherDtoToTeacher(TeacherDto teacherDto) {
        return new Teacher(counter.generateTeacherId(),
                UUID.randomUUID(),
                teacherDto.firstName(),
                teacherDto.lastName(),
                mapSubjectDtoToSubject(teacherDto.subject()));
    }

    @Override
    public TeacherDto mapTeacherToTeacherDto(Teacher teacher) {
        return new TeacherDto(
                teacher.getFirstName(),
                teacher.getLastName(),
                mapSubjectToSubjectDto(teacher.getSubject())
        );
    }

    private Subject mapSubjectDtoToSubject(SubjectDtoForTeacher subject) {
        return new Subject(subject.name());
    }

    private SubjectDtoForTeacher mapSubjectToSubjectDto(Subject subject) {
        return new SubjectDtoForTeacher(subject.getName());
    }
}
