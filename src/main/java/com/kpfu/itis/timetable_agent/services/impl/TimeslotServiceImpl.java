package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.*;
import com.kpfu.itis.timetable_agent.repositories.TimeslotDayRepository;
import com.kpfu.itis.timetable_agent.repositories.TimeslotRepository;
import com.kpfu.itis.timetable_agent.repositories.TimeslotTimeRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotServiceImpl implements TimeslotService{

    @Autowired
    private TimeslotDayRepository timeslotDayRepository;

    @Autowired
    private TimeslotTimeRepository timeslotTimeRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;


    @Override
    public List<Timeslot> getAllTimeslots() {
        return timeslotRepository.findAll();
    }

    @Override
    public List<TimeslotDay> getTimeslotDays() {
        return timeslotDayRepository.findAllByOrderByDayNumAsc();
    }

    @Override
    public List<TimeslotTime> getTimeslotTimes() {
        return timeslotTimeRepository.findAllByOrderByPairNumAsc();
    }

    @Override
    public TimeslotDay getTimeslotDay(int dayNum) {
        return timeslotDayRepository.getOneByDayNum(dayNum);
    }

    @Override
    public TimeslotTime getTimeslotTime(int parNum) {
        return timeslotTimeRepository.getOneByPairNum(parNum);
    }

    @Override
    public Timeslot getTimeslot(TimeslotDay day, TimeslotTime time) {
        return timeslotRepository.getOneByTimeslotDayAndTimeslotTime(day, time);
    }

    @Override
    public List<Timeslot> getAllFreeByPairGroups(AssignedPair assignedPair) {
        if (assignedPair.getGroup() != null)
            return timeslotRepository.getAllFreeByGroup(assignedPair.getGroup());
        return timeslotRepository.getAllFreeByGroupSet(assignedPair.getGroupSet());
    }
}
