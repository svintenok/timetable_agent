package com.kpfu.itis.timetable_agent.util.exeptions;

import com.kpfu.itis.timetable_agent.util.models.RestrictionViolation;

import java.util.ArrayList;
import java.util.List;

public class TimetableRestrictionsException extends Exception {

    private List<RestrictionViolation> dissatisfiedRestrictionsWarnings;

    public TimetableRestrictionsException(List<RestrictionViolation> dissatisfiedRestrictionsWarnings) {
        super("Неудовлетворение ограничениям");
        this.dissatisfiedRestrictionsWarnings = dissatisfiedRestrictionsWarnings;
    }

    public List<RestrictionViolation> getDissatisfiedRestrictions() {
        return dissatisfiedRestrictionsWarnings;
    }

    public List<String> getDissatisfiedRestrictionsWarnings() {
        List<String> dissatisfiedRestrictionsWarningsString = new ArrayList<>();
        for (RestrictionViolation restrictionViolation : dissatisfiedRestrictionsWarnings){
            dissatisfiedRestrictionsWarningsString.add(restrictionViolation.warningString());
        }
        return dissatisfiedRestrictionsWarningsString;
    }
}
