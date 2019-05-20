package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.AssignedPair;
import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;

import java.util.List;

public interface CurrentTimetableService {

    List<AssignedPair> getGroupTimetable(Group group);
    void replacePair(AssignedPair replacementPair, AssignedPair offerPair);
    AssignedPair replacePairHard(AssignedPair replacementPair);
    void cancelReplacePair(AssignedPair replacementPair);

    AssignedPair assignReplacePairOffer(AssignedPair replacementPair);

    void applyOffers();
    void cancelOffers();
}
