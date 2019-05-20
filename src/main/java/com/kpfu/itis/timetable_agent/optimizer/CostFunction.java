package com.kpfu.itis.timetable_agent.optimizer;

import com.kpfu.itis.timetable_agent.services.interfaces.ResourceRestrictionsService;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CostFunction {

    private int C = 10;
    private int hardViolationCount = 0;
    private double cost;

    @Autowired
    private RestrictionsService restrictionsService;

    @Autowired
    private ResourceRestrictionsService resourceRestrictionsService;

    public double calculateTimetableCost(){

        hardViolationCount = 0;

        List<RestrictionViolation> dissatisfiedRestrictions = new ArrayList<>();

        List<RestrictionViolation> dissatisfiedFactorRestrictions = restrictionsService.checkRestrictions();

        if (dissatisfiedFactorRestrictions != null){
            dissatisfiedRestrictions.addAll(dissatisfiedFactorRestrictions);
        }

        List<RestrictionViolation> dissatisfiedResourseRestrictions = resourceRestrictionsService.checkResourceRestrictions();

        if (dissatisfiedResourseRestrictions != null){
            dissatisfiedRestrictions.addAll(dissatisfiedResourseRestrictions);
        }

        double hardCost = 0;
        double softCost = 0;
        for (RestrictionViolation restrictionViolation: dissatisfiedRestrictions){
            if (restrictionViolation.isHard()) {
                hardCost += restrictionViolation.getWeight();
                hardViolationCount++;
            }
            else {
                softCost += restrictionViolation.getWeight();
            }

        }

        cost = hardCost * C + softCost;
        return cost;
    }

    public int getHardViolationCount() {
        return hardViolationCount;
    }
}
