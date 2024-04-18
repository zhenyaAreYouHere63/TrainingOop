package com.study.dao.store;

import com.study.dao.IdCounter;
import com.study.dao.SubjectType;
import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Subject;
import com.study.dao.core.Teacher;
import lombok.experimental.UtilityClass;
import java.util.*;

@UtilityClass
public class DataProvider {
    private static final IdCounter ID_COUNTER_STUDENT = IdCounter.getStudentInstance();
    private static final IdCounter ID_COUNTER_TEACHER = IdCounter.getTeacherInstance();

    public static List<Student> generateStudents() {
        return new ArrayList<>(List.of((new Student(ID_COUNTER_STUDENT.generateStudentId(), UUID.randomUUID(),
                "Taras", "Ivanenko", "Radioengineering", "Radio_engineering", new Group("REE_11"),
                new HashSet<>(List.of(new Subject("Math", SubjectType.COMPULSORY), new Subject("Physics", SubjectType.COMPULSORY)))))));
    }

    public static List<Teacher> generateTeachers() {
        return new ArrayList<>(List.of(
                new Teacher(ID_COUNTER_TEACHER.generateTeacherId(), UUID.randomUUID(),"Petro", "Ivaniv", new Subject("Physics")),
                new Teacher(ID_COUNTER_TEACHER.generateTeacherId(), UUID.randomUUID(), "Larisa", "Volodina", new Subject("Physics")),
                new Teacher(ID_COUNTER_TEACHER.generateTeacherId(), UUID.randomUUID(), "Tanya", "Sobchuk", new Subject("Math")),
                new Teacher(ID_COUNTER_TEACHER.generateTeacherId(), UUID.randomUUID(), "Vitalina", "Stepashko", new Subject("Math")),
                new Teacher(ID_COUNTER_TEACHER.generateTeacherId(), UUID.randomUUID(), "Olesya", "Pochaina", new Subject("English"))));
    }

    public static List<Group> generateGroups() {
        return new ArrayList<>(List.of(
                new Group("REE_11"), new Group("REL_11"), new Group("SP_11"),
                new Group("XE_11"), new Group("SS_11"), new Group("MIE_11"),
                new Group("MME_11"), new Group("MEC_11")));
    }

    public static List<String> getLearningsSubject() {
        return List.of("Math", "Physics", "IT", "History", "Geography", "Chemistry", "Biology", "Art");
    }

    public static List<String> getExistFaculties() {
        return List.of("Radioengineering", "Sociology", "Management", "Chemistry", "Linguistic");
    }

    public static List<String> getExistSpecialties() {
        return List.of("Radio_engineering", "Radio_electronics", "Philosophy", "Sociology",
                "International_economics", "Management_of_enterprises", "Economic_cybernetics");
    }

    public static List<Group> getGroups() {
        return generateGroups();
    }
}
