package com.study.dao;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class DataProvider {

    public static List<String> getLearningsPrograms(){
        return List.of("Software Engineering", "History", "Physics");
    }
}
