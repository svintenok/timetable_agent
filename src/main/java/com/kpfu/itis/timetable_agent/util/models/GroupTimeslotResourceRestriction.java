package com.kpfu.itis.timetable_agent.util.models;

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

    public GroupTimeslotResourceRestriction(Group group, Timeslot timeslot, double weight) {
        super();
        this.group = group;
        this.timeslot = timeslot;

        this.hard = true;
        this.weight = weight;
    }

    public GroupTimeslotResourceRestriction(Group group, Timeslot timeslot) {
        this(group, timeslot, 5);
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
        return Objects.equals(group, that.group) &&
                Objects.equals(timeslot, that.timeslot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, timeslot);
    }
}
