package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.*;
import com.kpfu.itis.timetable_agent.repositories.*;
import com.kpfu.itis.timetable_agent.services.interfaces.AssignedPairService;
import com.kpfu.itis.timetable_agent.services.interfaces.ResourceRestrictionsService;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import com.kpfu.itis.timetable_agent.util.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResourceRestrictionsServiceImpl implements ResourceRestrictionsService {

    @Autowired
    private RestrictionsService restrictionsService;

    @Autowired
    private AssignedPairRepository assignedPairRepository;

    /*
    @Autowired
    private AssignedPairService assignedPairService;

    @Autowired
    private AuditoryResourceRepository auditoryResourceRepository;

    @Autowired
    private ProfessorTimeslotResourceRepository professorTimeslotResourceRepository;
    */

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudyCourseRepository courseRepository;

    @Autowired
    private SubjectCourseRepository subjectCourseRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private AuditoryRepository auditoryRepository;

    @Autowired
    private TimeslotRepository timeslotRepository;

    @Autowired
    private ResourceCheckingRepository resourceCheckingRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RestrictionViolation> checkResourceRestrictions() {
        List<RestrictionViolation> restrictionViolations = new ArrayList<>();

        List<AssignedPair> assignedPairs = assignedPairRepository.findAllByReplacement(false);

        //<auditory-timeslot> resource existing
        List<AuditoryTimeslotResourceRestriction> auditoryTimeslotResourceExistingViolations = jdbcTemplate.query(
                resourceCheckingRepository.getByName("auditory_timeslot_existing").getExpression(),
                (rs, rownumber) -> {
                    AuditoryTimeslotResourceRestriction r =
                            new AuditoryTimeslotResourceRestriction(
                                    auditoryRepository.getOne(rs.getInt("auditory_id")),
                                    timeslotRepository.getOne(rs.getInt("timeslot")),
                                    ResourceRestrictionIssue.EXISTING_VIOLATION
                            );
                    return r;
                });
        restrictionViolations.addAll(auditoryTimeslotResourceExistingViolations);


        //<professor-timeslot> resource existing
        List<ProfessorTimeslotResourceRestriction> professorTimeslotResourceExistingViolations = jdbcTemplate.query(
                resourceCheckingRepository.getByName("professor_timeslot_existing").getExpression(),
                (rs, rownumber) -> {
                    ProfessorTimeslotResourceRestriction r =
                            new ProfessorTimeslotResourceRestriction(
                                    professorRepository.getOne(rs.getInt("professor_id")),
                                    timeslotRepository.getOne(rs.getInt("timeslot")),
                                    ResourceRestrictionIssue.EXISTING_VIOLATION
                            );
                    return r;
                });
        restrictionViolations.addAll(professorTimeslotResourceExistingViolations);


        //auditory capacity resource
        //todo
        for (AssignedPair pair: assignedPairs){
            if (!checkAuditoryCapacityRestriction(pair)){
                restrictionViolations.add(new AuditoryTimeslotResourceRestriction(
                        pair.getAuditory(),
                        pair.getTimeslot(),
                        ResourceRestrictionIssue.CAPACITY_VIOLATION));
            }
        }

        /*
        for (AssignedPair pair: assignedPairs){
            //<auditory-timeslot> resource existing
            AuditoryResource auditoryResource =
                    auditoryResourceRepository.findByAuditoryAndTimeslot(
                            pair.getAuditory(),
                            pair.getTimeslot());

            if (auditoryResource == null){
                restrictionViolations.add(new AuditoryTimeslotResourceRestriction(
                        pair.getAuditory(),
                        pair.getTimeslot(),
                        ResourceRestrictionIssue.EXISTING_VIOLATION));
            }

            //<auditory-pair> capacity
            //todo
            if (pair.getAuditory().getCapacity() < assignedPairService.getPairStudentCount(pair)){
                restrictionViolations.add(new AuditoryTimeslotResourceRestriction(
                        pair.getAuditory(),
                        pair.getTimeslot(),
                        ResourceRestrictionIssue.CAPACITY_VIOLATION));
            }

            //<professor-timeslot> resource existing
            if (pair.getProfessor() != null) {
                ProfessorTimeslotResource professorTimeslotResource =
                        professorTimeslotResourceRepository.findByProfessorAndTimeslot(
                                pair.getProfessor(),
                                pair.getTimeslot());

                if (professorTimeslotResource == null) {
                    restrictionViolations.add(new ProfessorTimeslotResourceRestriction(
                            pair.getProfessor(),
                            pair.getTimeslot(),
                            ResourceRestrictionIssue.EXISTING_VIOLATION));
                }
            }
        }
        */

        //todo
        //<group-timeslot> resource using
        Set<GroupTimeslotResourceRestriction> groupTimeslotResourceViolations = new HashSet<>();
        List<StudyCourse> studyCourses = courseRepository.findAll();

        for (StudyCourse course: studyCourses) {

            List<Group> groupList = groupRepository.findAllByStudyCourse(course);

            List<SubjectCourse> optionalCourseBlocks =
                    subjectCourseRepository.findAllByStudyCourseAndOptionalCourseBlock(course, true);
            if (optionalCourseBlocks.size() > 0) {

                int[][] optionalCoursesChoices = restrictionsService.getOptionalCoursesChoices(course);

                for (Group group : groupList) {

                    for (int i = 0; i < optionalCoursesChoices.length; i++) {
                        String optCourseChoiceParam = Arrays.toString(optionalCoursesChoices[i]).replace("[", "{").replace("]", "}");
                        groupTimeslotResourceViolations.addAll(jdbcTemplate.query(
                                resourceCheckingRepository.getByName("group_timeslot").getExpression(),
                                new Object[]{group.getId(), optCourseChoiceParam},
                                (rs, rownumber) -> {
                                    GroupTimeslotResourceRestriction r =
                                            new GroupTimeslotResourceRestriction(
                                                                group,
                                                                timeslotRepository.getOne(rs.getInt("timeslot")));
                                    return r;
                                })
                        );

                    }
                }
            }
            else {
                String optCourseChoiceParam = "{}";
                for (Group group : groupList) {
                    groupTimeslotResourceViolations.addAll(jdbcTemplate.query(
                            resourceCheckingRepository.getByName("group_timeslot").getExpression(),
                            new Object[]{group.getId(), optCourseChoiceParam},
                            (rs, rownumber) -> {
                                GroupTimeslotResourceRestriction r =
                                        new GroupTimeslotResourceRestriction(
                                                group,
                                                timeslotRepository.getOne(rs.getInt("timeslot")));
                                return r;
                            })
                    );
                }
            }
        }
        restrictionViolations.addAll(groupTimeslotResourceViolations);


        //<auditory-timeslot> resource using
        List<AuditoryTimeslotResourceRestriction> auditoryTimeslotResourceUsingViolations = jdbcTemplate.query(
                resourceCheckingRepository.getByName("auditory_timeslot_using").getExpression(),
                (rs, rownumber) -> {
                    AuditoryTimeslotResourceRestriction r =
                            new AuditoryTimeslotResourceRestriction(
                                    auditoryRepository.getOne(rs.getInt("auditory_id")),
                                    timeslotRepository.getOne(rs.getInt("timeslot")),
                                    ResourceRestrictionIssue.USING_VIOLATION
                            );
                    return r;
                });
        restrictionViolations.addAll(auditoryTimeslotResourceUsingViolations);


        //<professor-timeslot> resource using
        List<ProfessorTimeslotResourceRestriction> professorTimeslotResourceUsingViolations = jdbcTemplate.query(
                resourceCheckingRepository.getByName("professor_timeslot_using").getExpression(),
                (rs, rownumber) -> {
                    ProfessorTimeslotResourceRestriction r =
                            new ProfessorTimeslotResourceRestriction(
                                    professorRepository.getOne(rs.getInt("professor_id")),
                                    timeslotRepository.getOne(rs.getInt("timeslot")),
                                    ResourceRestrictionIssue.USING_VIOLATION
                            );
                    return r;
                });
        restrictionViolations.addAll(professorTimeslotResourceUsingViolations);


        return restrictionViolations;
    }

    private boolean checkAuditoryCapacityRestriction(AssignedPair pair){
        if (pair.getType().getType().equals("физкультура"))
            return true;
        if (pair.getAuditory().getCapacity() != -1 && pair.getStudentsCount() != -1)
            return pair.getAuditory().getCapacity() < pair.getStudentsCount();
        if (pair.getType().getType().equals("лекция"))
            return pair.getAuditory().isLectureRoom();
        return true;
    }
}
