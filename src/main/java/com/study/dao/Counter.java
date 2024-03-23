package com.study.dao;

import lombok.Getter;

@Getter
public class Counter {

    private static Counter studentInstance;
    private static Counter teacherInstance;

    private int lastStudentId = 0;
    private int lastTeacherId = 0;
    private Counter() {
    }

    public static Counter getStudentInstance() {
        if (studentInstance == null) {
            studentInstance = new Counter();
        }
        return studentInstance;
    }

    public static Counter getTeacherInstance() {
        if (teacherInstance == null) {
            teacherInstance = new Counter();
        }
        return teacherInstance;
    }

    public int generateStudentId() {
        return ++lastStudentId;
    }

    public int generateTeacherId() {
        return ++lastTeacherId;
    }
}
