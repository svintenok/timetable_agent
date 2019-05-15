package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auditory")
public class Auditory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "auditory_number", unique = true, nullable = false)
    private String number;

    @Column
    private int capacity;

    @Column(name = "lecture_room")
    private boolean lectureRoom;

    @OneToMany(mappedBy = "auditory")
    private Set<AuditoryResource> AuditoryResource;
}
