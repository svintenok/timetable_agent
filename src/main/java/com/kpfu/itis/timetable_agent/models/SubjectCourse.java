package com.kpfu.itis.timetable_agent.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subject_course")
public class SubjectCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "pair_count")
    private int pairCount;

    @ManyToOne
    @JoinColumn(name = "study_course_id")
    private StudyCourse studyCourse;

    @Column(name = "in_two_weeks")
    private boolean inTwoWeeks;

    @Column(name = "opt_course_block")
    private boolean optionalCourseBlock;

    @OneToMany(mappedBy = "subjectCourse")
    private List<OptionalCourseSubject> optionalCourseSubjects;
}
