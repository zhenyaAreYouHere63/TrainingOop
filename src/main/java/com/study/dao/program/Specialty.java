package com.study.dao.program;

public enum Specialty {
    RADIO_ELECTRONICS(Faculty.RADIOENGINEERING),
    RADIO_ENGINEERING(Faculty.RADIOENGINEERING),
    PHILOSOPHY(Faculty.SOCIOLOGY),
    SOCIOLOGY(Faculty.SOCIOLOGY),
    INTERNATIONAL_ECONOMICS(Faculty.MANAGEMENT),
    MANAGEMENT_OF_ENTERPRISES(Faculty.MANAGEMENT),
    ECONOMIC_CYBERNETICS (Faculty.MANAGEMENT);

    private final Faculty faculty;

    Specialty(Faculty faculty) {
        this.faculty = faculty;
    }
}
