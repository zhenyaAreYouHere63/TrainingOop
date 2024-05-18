package com.study.mapper;

import com.study.dao.IdCounter;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import com.study.dto.SubjectDtoForTeacher;
import com.study.dto.TeacherDto;
import java.util.UUID;

public class TeacherMapperImpl implements TeacherMapper {
    private static final IdCounter ID_COUNTER = IdCounter.getTeacherInstance();

    @Override
    public Teacher mapTeacherDtoToTeacher(TeacherDto teacherDto) {
        return new Teacher(ID_COUNTER.generateTeacherId(),
                UUID.randomUUID(),
                teacherDto.firstName(),
                teacherDto.lastName(),
                mapSubjectDtoToSubject(teacherDto.subject()));
    }

    private Subject mapSubjectDtoToSubject(SubjectDtoForTeacher subject) {
        return new Subject(subject.subject());
    }
}
