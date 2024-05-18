package com.study.mapper;

import com.study.dao.core.Student;
import com.study.dto.StudentDto;

public interface StudentMapper {
    Student mapStudentDtoToStudent(StudentDto studentDto);
}
