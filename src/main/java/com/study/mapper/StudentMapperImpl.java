package com.study.mapper;

import com.study.dao.Counter;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dto.StudentDto;
import com.study.dto.SubjectDtoForStudent;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class StudentMapperImpl implements StudentMapper {
    private static final Counter counter = Counter.getStudentInstance();

    @Override
    public StudentDto mapStudentToStudentDto(Student student) {
        return new StudentDto(
                student.getFirstName(),
                student.getLastName(),
                student.getFaculty(),
                student.getSpecialty(),
                student.getGroup(),
                mapSubjectsToSubjectDto(student.getSubjects())
        );
    }

    @Override
    public Student mapStudentDtoToStudent(StudentDto studentDto) {
        return new Student(
                counter.generateStudentId(),
                UUID.randomUUID(),
                studentDto.firstName(),
                studentDto.lastName(),
                studentDto.faculty(),
                studentDto.specialty(),
                studentDto.group(),
                mapSubjectDtosToSubjects(studentDto.subjects()));
    }

    private Set<Subject> mapSubjectDtosToSubjects(Set<SubjectDtoForStudent> subjectDtoForStudents) {
        Set<Subject> subjects = new HashSet<>();
        for(SubjectDtoForStudent subjectDtoForStudent : subjectDtoForStudents) {
            subjects.add(new Subject(subjectDtoForStudent.name(), subjectDtoForStudent.subjectType()));
        }
        return subjects;
    }

    private Set<SubjectDtoForStudent> mapSubjectsToSubjectDto(Set<Subject> subjects) {
        Set<SubjectDtoForStudent> subjectDtoForStudents = new HashSet<>();
        for(Subject subject: subjects) {
            subjectDtoForStudents.add(new SubjectDtoForStudent(subject.getName(), subject.getType()));
        }
        return subjectDtoForStudents;
    }
}
