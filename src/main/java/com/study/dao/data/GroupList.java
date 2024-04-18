package com.study.dao.data;

import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Teacher;
import com.study.dao.store.DataProvider;
import com.study.service.exception.NotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class GroupList {

    public List<Group> groups;

    private final StudentList listOfStudents;

    private final TeacherList listOfTeachers;

    public GroupList(StudentList students, TeacherList teachers) {
        this.listOfStudents = students;
        this.listOfTeachers = teachers;
        this.groups = DataProvider.generateGroups();
    }

    public void addStudentToGroup(Student student) {
        Optional<Group> optionalGroup = groups.stream().filter(
                        group -> group.getName().equals(student.getGroup().getName()))
                .findFirst();

        optionalGroup.ifPresent(group -> {
            group.getStudents().add(student);
        });
    }

    public void removeStudentFromGroup(int studentId) {
        Student studentToRemove = listOfStudents.getStudents().stream().filter(student -> student.getId() == studentId)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student with id " + studentId + " not found"));

        Group group = findGroupByName(studentToRemove.getGroup().getName());
        group.getStudents().remove(studentToRemove);
    }

    public void assignTeacherToGroup(Teacher teacher, String group) {
        Group foundGroup = findGroupByName(group);
        foundGroup.addTeacherToGroup(teacher);
    }

    public Teacher removeTeacherFromGroup(int teacherId) {
        Teacher teacherById = listOfTeachers.findTeacherById(teacherId);
        listOfTeachers.deleteTeacher(teacherId);
        return teacherById;
    }

    public Set<Teacher> getTeacherByGroup(String group) {
        Group foundGroup = findGroupByName(group);
        return foundGroup.getTeachers();
    }

    private Group findGroupByName(String name) {
        return groups.stream().filter(
                group -> group.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Group with name " + name + " not found"));
    }
}
