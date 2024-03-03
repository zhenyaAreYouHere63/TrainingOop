package com.study.dao.program;

public enum Group {

    REE_11(Specialty.RADIO_ENGINEERING),
    REL_11(Specialty.RADIO_ELECTRONICS),
    SP_11(Specialty.PHILOSOPHY),
    SS_11(Specialty.SOCIOLOGY),
    MIE_11(Specialty.INTERNATIONAL_ECONOMICS),
    MME_11(Specialty.MANAGEMENT_OF_ENTERPRISES),
    MEC_11(Specialty.ECONOMIC_CYBERNETICS);
    private final Specialty specialty;

    Group(Specialty specialty) {
        this.specialty = specialty;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public boolean isMatchingGroup(Specialty specialtyToCheck) {
        return this.specialty == specialtyToCheck;
    }
}
