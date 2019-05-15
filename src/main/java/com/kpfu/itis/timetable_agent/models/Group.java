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
@Table(name = "study_group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String groupNum;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private StudyCourse studyCourse;


    @Column(name = "student_count")
    private int studentCount;
}
