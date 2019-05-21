package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.Auditory;
import com.kpfu.itis.timetable_agent.models.AuditoryResource;
import com.kpfu.itis.timetable_agent.models.Timeslot;

import java.util.List;

public interface AuditoryService {

    List<Auditory> getAuditoryList();
    Auditory getAuditory(int id);
    Auditory getAuditoryByNumber(String number);

    List<Auditory> getAllFreeByTimeslot(Timeslot timeslot);

    Auditory saveAuditory(Auditory auditory);
    void saveResource(AuditoryResource resource);

    List<Auditory> getAllFreeByTimeslotAndType(Timeslot timeslot, boolean lectureRoom);
}
