package com.study.dao.data;

import com.study.dao.core.Group;
import com.study.dao.core.Student;
import com.study.dao.core.Teacher;
import com.study.dao.store.DataProvider;
import com.study.service.exception.NotFoundException;
import com.study.service.exception.RepeatException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class GroupList {

    private List<Group> groups;

    private final StudentList listOfStudents;

    private final TeacherList listOfTeachers;

    public GroupList(StudentList students, TeacherList teachers) {
        this.listOfStudents = students;
        this.listOfTeachers = teachers;
        this.groups = DataProvider.generateGroups();
    }

    public void assignStudentToGroup(Student student) {
        Optional<Group> optionalGroup = groups.stream().filter(
                        group -> group.getName().equals(student.getGroup().getName()))
                .findFirst();

        optionalGroup.ifPresent(group -> {
            group.getStudents().add(student);
        });
    }

    public void removeStudentFromGroup(String studentId) {
        Student studentToRemove = listOfStudents.getStudents().stream().filter(student -> student.getUuid()
                        .equals(UUID.fromString(studentId)))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student with id " + studentId + " not found"));

        Group group = getGroupByName(studentToRemove.getGroup().getName());
        group.getStudents().remove(studentToRemove);
    }

    public void assignTeacherToGroup(Teacher teacher, String group) {
        Group foundGroup = getGroupByName(group);

        if (foundGroup.getTeachers().stream().anyMatch(currentTeacher -> currentTeacher.getSubject().getName()
                .equals(teacher.getSubject().getName()))) {
            throw new RepeatException("A teacher with subject " + teacher.getSubject().getName() + " is already assigned to this group");
        }

        foundGroup.getTeachers().add(teacher);
    }

    public Teacher removeTeacherFromGroup(String teacherId, String groupName) {

        Teacher teacherToRemove = listOfTeachers.findTeacherById(teacherId);

        Group foundGroup = getGroupByName(teacherToRemove.getGroups()
                .stream().filter(group -> group.getName().equals(groupName))
                .findFirst()
                .map(Group::getName)
                .orElseThrow(() -> new NotFoundException("Teacher with this group "
                                                         + groupName + " not found")));

        foundGroup.getTeachers().remove(teacherToRemove);

        return teacherToRemove;
    }

    public void removeTeacherFromAllGroups(String teacherId) {
        Teacher teacherToRemove = listOfTeachers.findTeacherById(teacherId);

        groups.stream()
                .filter(group -> group.getTeachers().contains(teacherToRemove))
                .forEach(group -> group.getTeachers().remove(teacherToRemove));
    }

    public Set<Teacher> getTeachersByGroup(String group) {
        Group foundGroup = getGroupByName(group);
        return foundGroup.getTeachers();
    }

    public boolean isTeacherAssignedToGroup(String teacherId) {
        return groups.stream().anyMatch(group -> group.getTeachers().stream().anyMatch(teacher
                -> teacher.getUuid() == UUID.fromString(teacherId)));
    }

    public Group getStudentGroup(Student student) {
        return groups.stream()
                .filter(group -> group.getName().equalsIgnoreCase(student.getGroup().getName()))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Group for student not found"));
    }

    public Set<Group> getTeacherGroups(Teacher teacher) {
        return groups.stream().filter(group -> group.getTeachers().contains(teacher))
                .collect(Collectors.toSet());
    }

    private Group getGroupByName(String name) {
        return groups.stream().filter(
                        group -> group.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Group with name " + name + " not found"));
    }
}
