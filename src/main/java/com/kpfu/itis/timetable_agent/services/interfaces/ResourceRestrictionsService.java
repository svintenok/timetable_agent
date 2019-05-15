package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.util.models.RestrictionViolation;

import java.util.List;

public interface ResourceRestrictionsService {
    List<RestrictionViolation> checkResourceRestrictions();
}
