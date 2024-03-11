package com.study.service.validation;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ValidationResult {
    private final Map<String, String> errors = new HashMap<>();

    public void addErrors(String field, String error) {
        errors.put(field, error);
    }

    public Map<String, String> getErrors() {
        return Collections.unmodifiableMap(errors);
    }
}
