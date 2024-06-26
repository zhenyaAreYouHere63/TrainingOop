package com.study.mapper;

import com.study.dao.IdCounter;
import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dto.GroupDto;
import com.study.dto.StudentDto;
import com.study.dto.SubjectDtoForStudent;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StudentMapperImpl implements StudentMapper {
    private static final IdCounter ID_COUNTER = IdCounter.getStudentInstance();

    @Override
    public Student mapStudentDtoToStudent(StudentDto studentDto) {
        return new Student(
                ID_COUNTER.generateStudentId(),
                UUID.randomUUID(),
                studentDto.firstName(),
                studentDto.lastName(),
                studentDto.faculty(),
                studentDto.specialty(),
                mapGroupDtoToGroup(studentDto.group()),
                mapSubjectDtosToSubjects(studentDto.subjects()));
    }

    private Set<Subject> mapSubjectDtosToSubjects(Set<SubjectDtoForStudent> subjectDtoForStudents) {
        Set<Subject> subjects = new HashSet<>();
        for(SubjectDtoForStudent subjectDtoForStudent : subjectDtoForStudents) {
            subjects.add(new Subject(subjectDtoForStudent.subject(), subjectDtoForStudent.subjectType()));
        }
        return subjects;
    }

    private Group mapGroupDtoToGroup(GroupDto groupDto) {
        return new Group(groupDto.name());
    }
}
