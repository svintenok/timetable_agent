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
@Table(name = "opt_subject_course")
public class OptionalCourseSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "subject_course")
    private SubjectCourse subjectCourse;

    @Column(name = "student_count")
    private int studentCount;

    @ManyToOne
    @JoinColumn(name = "study_course_id")
    private StudyCourse studyCourse;
}