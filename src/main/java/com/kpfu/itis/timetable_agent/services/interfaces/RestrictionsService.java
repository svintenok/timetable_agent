package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.Restriction;
import com.kpfu.itis.timetable_agent.models.StudyCourse;
import com.kpfu.itis.timetable_agent.util.exeptions.TimetableRestrictionsException;
import com.kpfu.itis.timetable_agent.util.models.RestrictionViolation;

import java.util.List;

public interface RestrictionsService {

    void saveRestriction(Restriction restriction);

    List<Restriction> getAllRestrictions();
    List<RestrictionViolation> checkRestrictions();
    int[][] getOptionalCoursesChoices(StudyCourse course);
}
