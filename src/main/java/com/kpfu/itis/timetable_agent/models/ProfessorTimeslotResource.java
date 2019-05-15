package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "professor_timeslot_resource")
public class ProfessorTimeslotResource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "timeslot")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;
}
