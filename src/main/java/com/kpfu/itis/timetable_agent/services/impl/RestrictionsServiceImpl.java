package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.*;
import com.kpfu.itis.timetable_agent.repositories.*;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import com.kpfu.itis.timetable_agent.analyzer.models.FactorRestriction;
import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RestrictionsServiceImpl implements RestrictionsService {

    @Autowired
    private RestrictionRepository restrictionRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Restriction getRestriction(int id) {
        return restrictionRepository.getOne(id);
    }

    @Override
    public Restriction saveRestriction(Restriction restriction) {
        return restrictionRepository.save(restriction);
    }

    @Override
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    @Override
    public Operation getOperation(int id) {
        return operationRepository.getOne(id);
    }

    @Override
    public List<Restriction> getAllRestrictions() {
        return restrictionRepository.findAll();
    }

    @Override
    public List<Restriction> getAllEnabledRestrictions() {
        return restrictionRepository.findAllByEnabled(true);
    }

    @Override
    public List<Restriction> getAllEnabledSoftRestrictions() {
        return restrictionRepository.findAllByEnabledAndHard(true, false);
    }

}
