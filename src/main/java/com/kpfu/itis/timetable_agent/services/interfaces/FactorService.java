package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.Factor;
import com.kpfu.itis.timetable_agent.models.Restriction;

import java.util.List;

public interface FactorService {
    Factor getFactor(int id);
    List<Factor> getAllFactors();
    void saveFactor(Factor factor);
}
