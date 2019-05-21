package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.AssignedPair;
import com.kpfu.itis.timetable_agent.models.GroupSet;
import com.kpfu.itis.timetable_agent.models.PairType;
import com.kpfu.itis.timetable_agent.repositories.AssignedPairRepository;
import com.kpfu.itis.timetable_agent.repositories.PairTypeRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.AssignedPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedPairServiceImpl implements AssignedPairService {

    @Autowired
    private AssignedPairRepository assignedPairRepository;

    @Autowired
    private PairTypeRepository pairTypeRepository;

    @Override
    public AssignedPair getPairById(int id) {
        return assignedPairRepository.getOne(id);
    }

    @Override
    public List<AssignedPair> getCurrentTimetableWithOffers() {
        return assignedPairRepository.findAllByReplacement(false);
    }

    @Override
    public List<AssignedPair> getCurrentFreePairsWithOffers() {
        return assignedPairRepository.getAllFreePairsWithOffers();
    }

    @Override
    public List<AssignedPair> getTimetableReplacements() {
        return assignedPairRepository.findAllByReplacement(true);
    }

    @Override
    public AssignedPair save(AssignedPair assignedPair) {
        return assignedPairRepository.save(assignedPair);
    }

    @Override
    public int getPairStudentCount(AssignedPair assignedPair) {
        return 0;
        /*
        if (assignedPair.getGroup() != null)
            return assignedPair.getGroup().getStudentCount();
        if (assignedPair.getType().getType().equals("курс по выбору"))
            return assignedPair.getOptionalCourseSubject().getStudentCount();
        return assignedPair.getGroupSet().getStudentsCount();
        */
    }

    @Override
    public PairType getPairType(String type) {
        return pairTypeRepository.findByType(type);
    }
}
