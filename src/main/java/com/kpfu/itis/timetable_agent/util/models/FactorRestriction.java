package com.kpfu.itis.timetable_agent.util.models;

import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.models.Restriction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FactorRestriction extends RestrictionViolation {

    private Group group;
    private Restriction restriction;

    public FactorRestriction(Group group, Restriction restriction) {
        this(group, restriction, restriction.getPriority());

    }

    public FactorRestriction(Group group, Restriction restriction, double weight) {
        this.group = group;
        this.restriction = restriction;

        this.hard = restriction.isHard();
        this.weight = weight;
    }

    @Override
    public String warningString() {
        return "Ограничение  <" + restriction.getName() +
                "> не выполняется для группы " + group.getGroupNum();
    }
}
