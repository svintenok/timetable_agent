package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auditory_timeslot_resource")
public class AuditoryResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "timeslot")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "auditory_id")
    private Auditory auditory;
}
