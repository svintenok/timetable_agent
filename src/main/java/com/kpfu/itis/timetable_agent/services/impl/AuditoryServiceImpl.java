package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.Auditory;
import com.kpfu.itis.timetable_agent.models.AuditoryResource;
import com.kpfu.itis.timetable_agent.models.Timeslot;
import com.kpfu.itis.timetable_agent.repositories.AuditoryRepository;
import com.kpfu.itis.timetable_agent.repositories.AuditoryResourceRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.AuditoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditoryServiceImpl implements AuditoryService{

    @Autowired
    private AuditoryRepository auditoryRepository;

    @Autowired
    private AuditoryResourceRepository auditoryResourceRepository;


    @Override
    public List<Auditory> getAuditoryList() {
        return auditoryRepository.findAll();
    }

    @Override
    public Auditory getAuditory(int id) {
        return auditoryRepository.getOne(id);
    }

    @Override
    public Auditory getAuditoryByNumber(String number) {
        return auditoryRepository.findByNumber(number);
    }

    @Override
    public List<Auditory> getAllFreeByTimeslot(Timeslot timeslot) {
        return auditoryRepository.getAllFreeByTimeslot(timeslot);
    }

    @Override
    public Auditory saveAuditory(Auditory auditory) {
        return auditoryRepository.save(auditory);
    }

    @Override
    public void saveResource(AuditoryResource resource) {
        auditoryResourceRepository.save(resource);
    }

    @Override
    public List<Auditory> getAllFreeByTimeslotAndType(Timeslot timeslot, boolean lectureRoom) {
        return auditoryRepository.getAllFreeByTimeslotByType(timeslot, lectureRoom);
    }
}
