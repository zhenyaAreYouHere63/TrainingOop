package com.study.dao;

import lombok.Getter;

@Getter
public class IdCounter {

    private static IdCounter studentInstance;
    private static IdCounter teacherInstance;

    private int lastStudentId = 0;
    private int lastTeacherId = 0;

    private IdCounter() {
    }

    public static IdCounter getStudentInstance() {
        if (studentInstance == null) {
            synchronized (IdCounter.class) {
                if (studentInstance == null) {
                    studentInstance = new IdCounter();
                }
            }
        }
        return studentInstance;
    }

    public static IdCounter getTeacherInstance() {
        if (teacherInstance == null) {
            synchronized (IdCounter.class) {
                if (teacherInstance == null) {
                    teacherInstance = new IdCounter();
                }
            }
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
