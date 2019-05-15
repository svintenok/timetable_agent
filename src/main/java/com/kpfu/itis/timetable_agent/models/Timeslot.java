package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "timeslot")
public class Timeslot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "timeslot_day")
    private TimeslotDay timeslotDay;

    @ManyToOne
    @JoinColumn(name = "timeslot_time")
    private TimeslotTime timeslotTime;

    @Override
    public String toString() {
        return "{" +
                " " + timeslotDay.getDayName() +
                ", " + timeslotTime.getInterval() +
                " }";
    }
}
