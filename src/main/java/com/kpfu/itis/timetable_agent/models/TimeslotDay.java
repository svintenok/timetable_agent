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
@Table(name = "timeslot_day")
public class TimeslotDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private int dayNum;

    @Column(unique = true, nullable = false)
    private String dayName;
}
