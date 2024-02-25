package com.study.service;

import com.study.dao.program.Faculty;
import com.study.dao.program.Group;
import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import java.util.HashMap;
import java.util.List;

public interface StudentService {

    void createNewStudent(String firstName, String lastName, Faculty faculty, Group group,
                          Specialty specialty, List<SubjectName> attendedSubjectName);

    void addStudentToCourse(SubjectName subjectName);

    void viewAllSubjects(int studentId);

    void viewAllGrades();

    double averageGradeOfSubject();
}
