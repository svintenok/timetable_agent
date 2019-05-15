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
@Table(name = "assigned_pair")
public class AssignedPair implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "subject_course_id")
    private SubjectCourse subjectCourse;

    @ManyToOne
    @JoinColumn(name = "study_group_id", nullable = true)
    private Group group;

    @ManyToOne
    @JoinColumn(name = "group_set_id")
    private GroupSet groupSet;

    @ManyToOne
    @JoinColumn(name = "timeslot_day")
    private TimeslotDay timeslotDay;

    @ManyToOne
    @JoinColumn(name = "timeslot_time")
    private TimeslotTime timeslotTime;

    @ManyToOne
    @JoinColumn(name = "timeslot")
    private Timeslot timeslot;

    @ManyToOne
    @JoinColumn(name = "auditory_id")
    private Auditory auditory;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "type")
    private PairType type;

    /*
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PairType type;
    */
    @Column(name = "in_two_weeks")
    private boolean inTwoWeeks;

    @Column(name = "even_week")
    private boolean evenWeek;

    @ManyToOne
    @JoinColumn(name = "opt_subject_course_id")
    private OptionalCourseSubject optionalCourseSubject;

    @Column
    private boolean replacement;

    @Column
    private boolean offer;

    @OneToOne
    @JoinColumn(name = "offer_pair_id")
    private AssignedPair assignedPairOffer;

    /*
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "group_set_group",
            joinColumns = @JoinColumn(name = "group_set_id"),
            inverseJoinColumns = @JoinColumn(name = "study_group_id"))
    private Set<Group> groups;
    */

    @Override
    public Object clone() {
        AssignedPair clone = new AssignedPair();
        clone.setGroup(group);
        clone.setGroupSet(groupSet);
        clone.setSubjectCourse(subjectCourse);
        clone.setType(type);
        clone.setOptionalCourseSubject(optionalCourseSubject);
        clone.setProfessor(professor);
        clone.setAuditory(auditory);
        clone.setTimeslot(timeslot);
        clone.setTimeslotDay(timeslotDay);
        clone.setTimeslotTime(timeslotTime);
        clone.setInTwoWeeks(inTwoWeeks);
        clone.setEvenWeek(evenWeek);

        clone.setOffer(false);
        clone.setReplacement(false);
        return clone;
    }

    @Override
    public String toString() {
        return "AssignedPair{\n" +
                "\n\tid=" + id +
                ",\n\tsubjectCourse=" + subjectCourse.getName() +
                ",\n\tgroup=" + (group == null ? "N/A" : group.getGroupNum()) +
                ",\n\tgroupSet=" + (groupSet == null ? "N/A" : groupSet.getName()) +
                ",\n\ttimeslotDay=" + timeslotDay.getDayName() +
                ",\n\ttimeslotTime=" + timeslotTime.getInterval() +
                ",\n\tauditory=" + auditory.getNumber() +
                ",\n\tprofessor=" + professor.getName() +
                ",\n\ttype=" + type.getType() +
                ",\n\toptionalCourseSubject=" + (optionalCourseSubject == null ? "N/A" : optionalCourseSubject.getName()) +
                ",\n\treplacement=" + replacement +
                ",\n\toffer=" + offer +
                ",\n\tassignedPairOffer=" + (assignedPairOffer == null ? "N/A" : assignedPairOffer.getId()) +
                '}';
    }

    @Transient
    public int getStudentsCount(){
        if (group != null)
            return group.getStudentCount();
        if (type.getType().equals("курс по выбору"))
            return optionalCourseSubject.getStudentCount();
        return groupSet.getStudentsCount();
    }
}
