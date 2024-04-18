package com.study.dto;

import com.study.dao.store.DataProvider;
import com.study.service.exception.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public record GroupDto(
        String name
) {

    public static List<Exception> validateGroupDtoToGroup(String group) {

        List<Exception> errors = new ArrayList<>();

        if (group.isBlank()) {
            errors.add(new IllegalArgumentException("The field name cannot be blank"));
        } if (DataProvider.getGroups().stream().noneMatch(existGroup -> existGroup.getName().equals(group))) {
            errors.add(new NotFoundException("This name " + group + " not exist"));
        }
        return errors;
    }
}
