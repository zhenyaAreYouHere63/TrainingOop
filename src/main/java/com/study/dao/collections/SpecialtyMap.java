package com.study.dao.collections;

import com.study.dao.program.Specialty;
import com.study.dao.program.SubjectName;
import java.util.HashMap;
import java.util.Map;

public class SpecialtyMap {

    public Map<Specialty, SubjectName> compulsorySubject;

    public SpecialtyMap() {
        compulsorySubject = new HashMap<>(Map.of
                (Specialty.RADIO_ELECTRONICS, SubjectName.MATH,
                        Specialty.RADIO_ENGINEERING, SubjectName.PHYSICS,
                        Specialty.ECONOMIC_CYBERNETICS, SubjectName.MATH,
                        Specialty.MANAGEMENT_OF_ENTERPRISES, SubjectName.ENGLISH));
    }
}
