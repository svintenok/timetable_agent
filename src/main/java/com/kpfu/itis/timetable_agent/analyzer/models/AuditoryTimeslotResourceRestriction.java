package com.kpfu.itis.timetable_agent.analyzer.models;

import com.kpfu.itis.timetable_agent.models.Auditory;
import com.kpfu.itis.timetable_agent.models.Timeslot;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class AuditoryTimeslotResourceRestriction extends RestrictionViolation {
    private Auditory auditory;
    private Timeslot timeslot;
    private ResourceRestrictionIssue issue;

    public AuditoryTimeslotResourceRestriction(Auditory auditory, Timeslot timeslot, ResourceRestrictionIssue issue) {
        super();
        this.auditory = auditory;
        this.timeslot = timeslot;
        this.issue = issue;

        this.hard = true;
        this.priority = 5;
    }

    @Override
    public String warningString() {
        String warning;

        if (issue == ResourceRestrictionIssue.EXISTING_VIOLATION){
            warning = "Для аудитории " + auditory.getNumber() + " и таймслота " +
                    timeslot.toString() + " нет ресурса";
        }
        else if (issue == ResourceRestrictionIssue.CAPACITY_VIOLATION){
            warning = "Для аудитории " + auditory.getNumber() + " и таймслота " +
                    timeslot.toString() + " превышена вместимость студентов";
        }
        else {
            warning = "Для аудитории " + auditory.getNumber() + " и таймслота " +
                    timeslot.toString() + " присвоено больше одной пары";
        }
        return warning;
    }
}
