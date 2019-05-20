package com.kpfu.itis.timetable_agent.analyzer.models;

import com.kpfu.itis.timetable_agent.models.Professor;
import com.kpfu.itis.timetable_agent.models.Timeslot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorTimeslotResourceRestriction extends RestrictionViolation {
    private Professor professor;
    private Timeslot timeslot;
    private ResourceRestrictionIssue issue;

    public ProfessorTimeslotResourceRestriction(Professor professor, Timeslot timeslot, ResourceRestrictionIssue issue) {
        super();
        this.professor = professor;
        this.timeslot = timeslot;
        this.issue = issue;

        this.hard = true;
        this.weight = 5;
    }

    @Override
    public String warningString() {
        String warning;

        if (issue == ResourceRestrictionIssue.EXISTING_VIOLATION){
            warning = "Для профессора " + professor.getName() + " и таймслота " +
                    timeslot.toString() + " нет ресурса";
        }
        else {
            warning = "Для профессора " + professor.getName() + " и таймслота " +
                    timeslot.toString() + " присвоено больше одной пары";
        }
        return warning;
    }
}
