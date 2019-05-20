package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.Operation;
import com.kpfu.itis.timetable_agent.models.Restriction;
import com.kpfu.itis.timetable_agent.models.StudyCourse;
import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;

import java.util.List;

public interface RestrictionsService {

    Restriction getRestriction(int id);
    Restriction saveRestriction(Restriction restriction);

    List<Operation> getAllOperations();
    Operation getOperation(int id);

    List<Restriction> getAllRestrictions();
    List<RestrictionViolation> checkRestrictions();
    int[][] getOptionalCoursesChoices(StudyCourse course);
}
