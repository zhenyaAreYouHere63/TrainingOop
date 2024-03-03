package com.study.dao.program;

public enum Specialty {
    RADIO_ELECTRONICS(Faculty.RADIOENGINEERING, SubjectName.PHYSICS),
    RADIO_ENGINEERING(Faculty.RADIOENGINEERING, SubjectName.MATH),
    PHILOSOPHY(Faculty.SOCIOLOGY, SubjectName.PHYSICAL_CULTURE),
    SOCIOLOGY(Faculty.SOCIOLOGY, SubjectName.SCIENCE_OF_LAW),
    INTERNATIONAL_ECONOMICS(Faculty.MANAGEMENT, SubjectName.INTERNATIONAL_COMMUNICATIONS),
    MANAGEMENT_OF_ENTERPRISES(Faculty.MANAGEMENT, SubjectName.PRODUCTION_EQUIPMENT),
    ECONOMIC_CYBERNETICS (Faculty.MANAGEMENT, SubjectName.CHEMISTRY);

    private final Faculty faculty;
    private final SubjectName compulsorySubject;

    Specialty(Faculty faculty, SubjectName compulsorySubject) {
        this.faculty = faculty;
        this.compulsorySubject = compulsorySubject;
    }

    public SubjectName getCompulsorySubject() {
        return compulsorySubject;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public boolean isMatchingFaculty(Faculty facultyToCheck) {
        return this.faculty == facultyToCheck;
    }
}
