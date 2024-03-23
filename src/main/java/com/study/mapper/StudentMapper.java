package com.study.mapper;

import com.study.dao.core.Student;
import com.study.dto.StudentDto;

public interface StudentMapper {

    StudentDto mapStudentToStudentDto(Student student);

    Student mapStudentDtoToStudent(StudentDto studentDto);
}
