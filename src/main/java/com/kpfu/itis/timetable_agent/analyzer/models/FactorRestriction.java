package com.kpfu.itis.timetable_agent.analyzer.models;

import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.models.Restriction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class FactorRestriction extends RestrictionViolation {

    private Group group;
    private Restriction restriction;
    private int optCoursesChoicesAllCount;
    private int optCoursesChoicesAffectedCount;
    private int weight;

    public FactorRestriction(Group group, Restriction restriction, int optCoursesChoicesAffectedCount,
                             int optCoursesChoicesAllCount) {
        this.group = group;
        this.restriction = restriction;

        this.optCoursesChoicesAllCount = optCoursesChoicesAllCount;
        this.optCoursesChoicesAffectedCount = optCoursesChoicesAffectedCount;

        this.hard = restriction.isHard();
        this.priority = restriction.getPriority();
    }

    public FactorRestriction(Group group, Restriction restriction) {
        this(group, restriction, 0, 0);
    }



    @Override
    public String warningString() {
        String warning;
        if (optCoursesChoicesAllCount == 0 || optCoursesChoicesAffectedCount == optCoursesChoicesAllCount) {
            warning = "Ограничение  <" + restriction.getName() +
                      "> не выполняется для группы " + group.getGroupNum();
        }
        else {
            warning = "Ограничение  <" + restriction.getName() +
                      "> не выполняется для группы " + group.getGroupNum() +
                      " для " + optCoursesChoicesAffectedCount +
                      "/" + optCoursesChoicesAllCount + " наборов курсов по выбору";
        }
        return warning;
    }

    public void addAffectedOptCoursesChoice(){
        optCoursesChoicesAffectedCount++;
    }

    @Override
    public double getWeight() {
        if (optCoursesChoicesAllCount == 0 || optCoursesChoicesAffectedCount == optCoursesChoicesAllCount){
            return priority;
        }
        return priority * ((double)optCoursesChoicesAffectedCount / optCoursesChoicesAllCount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactorRestriction that = (FactorRestriction) o;
        return Objects.equals(group.getId(), that.group.getId()) &&
               Objects.equals(restriction.getId(), that.restriction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(group.getId(), restriction.getId());
    }
}
