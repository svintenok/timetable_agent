package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(mappedBy = "auditory", fetch=FetchType.EAGER)
    private Set<AuditoryResource> auditoryResourcesList;

    @Transient
    public boolean hasResource(int day, int time){
        for (AuditoryResource resource: auditoryResourcesList){
            if (resource.getTimeslot().getTimeslotDay().getDayNum() == day &&
                    resource.getTimeslot().getTimeslotTime().getPairNum() == time)
                return true;
        }
        return false;
    }
}
