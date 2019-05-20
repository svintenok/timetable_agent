package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.Factor;
import com.kpfu.itis.timetable_agent.models.Restriction;
import com.kpfu.itis.timetable_agent.repositories.FactorRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.FactorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactorServiceImpl implements FactorService {

    @Autowired
    private FactorRepository factorRepository;

    @Override
    public Factor getFactor(int id) {
        return factorRepository.getOne(id);
    }

    @Override
    public List<Factor> getAllFactors() {
        return factorRepository.findAll();
    }

    @Override
    public void saveFactor(Factor factor) {
        factorRepository.save(factor);
    }
}
