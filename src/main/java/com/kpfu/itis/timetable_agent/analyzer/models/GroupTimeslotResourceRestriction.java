package com.kpfu.itis.timetable_agent.analyzer.models;

import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.models.Timeslot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class GroupTimeslotResourceRestriction extends RestrictionViolation {

    private Group group;
    private Timeslot timeslot;

    public GroupTimeslotResourceRestriction(Group group, Timeslot timeslot) {
        super();
        this.group = group;
        this.timeslot = timeslot;

        this.priority = 5;
        this.hard = true;
    }

    @Override
    public String warningString() {

        String warning = "Для группы " + group.getGroupNum() + " и таймслота " +
                             timeslot.toString() + " присвоено больше одной пары";
        return warning;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupTimeslotResourceRestriction that = (GroupTimeslotResourceRestriction) o;
        return Objects.equals(group.getId(), that.group.getId()) &&
                Objects.equals(timeslot.getId(), that.timeslot.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(group.getId(), timeslot.getId());
    }
}
