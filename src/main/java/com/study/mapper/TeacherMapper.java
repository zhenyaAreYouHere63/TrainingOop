package com.study.mapper;

import com.study.dao.core.Teacher;
import com.study.dto.TeacherDto;

public interface TeacherMapper {
    Teacher mapTeacherDtoToTeacher(TeacherDto teacherDto);
    TeacherDto mapTeacherToTeacherDto(Teacher teacher);
}
