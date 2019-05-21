package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.AssignedPair;
import com.kpfu.itis.timetable_agent.models.PairType;

import java.util.List;

public interface AssignedPairService {
    AssignedPair getPairById(int id);

    List<AssignedPair> getCurrentTimetableWithOffers();
    List<AssignedPair> getCurrentFreePairsWithOffers();
    List<AssignedPair> getTimetableReplacements();

    AssignedPair save(AssignedPair assignedPair);

    int getPairStudentCount(AssignedPair assignedPair);

    PairType getPairType(String type);
}
