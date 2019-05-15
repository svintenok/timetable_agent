package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.*;

import java.util.List;

public interface TimeslotService {

    List<Timeslot> getAllTimeslots();
    List<TimeslotDay> getTimeslotDays();
    List<TimeslotTime> getTimeslotTimes();

    TimeslotDay getTimeslotDay(int dayNum);
    TimeslotTime getTimeslotTime(int pairNum);
    Timeslot getTimeslot(TimeslotDay day, TimeslotTime time);

    List<Timeslot> getAllFreeByPairGroups(AssignedPair assignedPair);
}
