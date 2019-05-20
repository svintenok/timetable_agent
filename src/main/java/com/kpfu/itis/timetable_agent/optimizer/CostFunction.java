package com.kpfu.itis.timetable_agent.optimizer;

import com.kpfu.itis.timetable_agent.analyzer.RestrictionsAnalyzer;
import com.kpfu.itis.timetable_agent.models.Restriction;
import com.kpfu.itis.timetable_agent.services.interfaces.GroupService;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CostFunction {

    //private int C = 10;
    private int hardViolationCount = 0;
    private double cost;

    @Autowired
    private RestrictionsService restrictionsService;

    @Autowired
    private RestrictionsAnalyzer restrictionsAnalyzer;

    @Autowired
    private GroupService groupService;

    public double calculateTimetableCost(){

        int C = 0;
        List<Restriction> enabledSoftRestrictions = restrictionsService.getAllEnabledSoftRestrictions();
        for (Restriction restriction: enabledSoftRestrictions){
            C += restriction.getPriority();
        }
        C = C * groupService.getAllGroups().size();

        hardViolationCount = 0;

        List<RestrictionViolation> dissatisfiedRestrictions = restrictionsAnalyzer.getRestrictionsViolations();

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
