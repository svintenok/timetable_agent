package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;

import java.util.List;

public interface ResourceRestrictionsService {
    List<RestrictionViolation> checkResourceRestrictions();
}
