package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.*;
import com.kpfu.itis.timetable_agent.repositories.GroupRepository;
import com.kpfu.itis.timetable_agent.repositories.RestrictionRepository;
import com.kpfu.itis.timetable_agent.repositories.StudyCourseRepository;
import com.kpfu.itis.timetable_agent.repositories.SubjectCourseRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import com.kpfu.itis.timetable_agent.util.models.FactorRestriction;
import com.kpfu.itis.timetable_agent.util.models.RestrictionViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RestrictionsServiceImpl implements RestrictionsService {

    @Autowired
    private RestrictionRepository restrictionRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private StudyCourseRepository courseRepository;

    @Autowired
    private SubjectCourseRepository subjectCourseRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void saveRestriction(Restriction restriction) {
        restrictionRepository.save(restriction);
    }

    @Override
    public List<Restriction> getAllRestrictions() {
        return restrictionRepository.findAll();
    }

    @Override
    public List<RestrictionViolation> checkRestrictions() {
        List<RestrictionViolation> retrictionsViolations = new ArrayList<>();

        List<Restriction> restrictionList = restrictionRepository.findAll();
        List<StudyCourse> studyCourses = courseRepository.findAll();

        for (StudyCourse course: studyCourses) {

            List<Group> groupList = groupRepository.findAllByStudyCourse(course);

            List<SubjectCourse> optionalCourseBlocks =
                    subjectCourseRepository.findAllByStudyCourseAndOptionalCourseBlock(course, true);
            if (optionalCourseBlocks.size() > 0) {

                int[][] optionalCoursesChoices = getOptionalCoursesChoices(course);

                for (Restriction restriction : restrictionList) {

                    Factor factor = restriction.getFactor();
                    Operation operation = restriction.getOperation();
                    double restrictionValue = restriction.getRestrictionValue();

                    for (Group group : groupList) {

                        List<RestrictionViolation> groupDissatisfiedRetrictions = new ArrayList<>();

                        for (int i = 0; i < optionalCoursesChoices.length; i++) {
                            String optCourseChoiceParam = Arrays.toString(optionalCoursesChoices[i]).replace("[", "{").replace("]", "}");
                            Double factorValue = jdbcTemplate.queryForObject(factor.getSqlExpression(), new Object[]{group.getId(), optCourseChoiceParam}, Double.class);
                            if (!checkRestriction(operation, factorValue, restrictionValue)) {
                                groupDissatisfiedRetrictions.add(new FactorRestriction(group, restriction));
                            }
                        }


                        if (groupDissatisfiedRetrictions.size() > 0) {
                            if (groupDissatisfiedRetrictions.size() == optionalCoursesChoices.length) {
                                retrictionsViolations.add(new FactorRestriction(group, restriction));
                            } else {
                                double weight = restriction.getPriority() * ((double) groupDissatisfiedRetrictions.size() / optionalCoursesChoices.length);
                                retrictionsViolations.add(new FactorRestriction(group, restriction, weight));
                            }
                        }

                    }
                }
            }

            else {

                for (Restriction restriction : restrictionList) {

                    Factor factor = restriction.getFactor();
                    Operation operation = restriction.getOperation();
                    double restrictionValue = restriction.getRestrictionValue();

                    for (Group group : groupList) {
                        String optCourseChoiceParam = "{}";
                        Double factorValue = jdbcTemplate.queryForObject(factor.getSqlExpression(), new Object[]{group.getId(), optCourseChoiceParam}, Double.class);
                        if (!checkRestriction(operation, factorValue, restrictionValue)) {
                            retrictionsViolations.add(new FactorRestriction(group, restriction));
                        }
                    }
                }
            }
        }

        return retrictionsViolations;
    }

    public int[][] getOptionalCoursesChoices(StudyCourse course) {

        String choicesCountSql = "SELECT MULT(opt_counts.opt_count) FROM " +
                "(SELECT COUNT(id) opt_count FROM opt_subject_course " +
                "WHERE study_course_id=? " +
                "GROUP BY subject_course) opt_counts;";

        Integer choicesCount = jdbcTemplate.queryForObject(choicesCountSql, new Object[]{course.getId()}, Integer.class);

        List<SubjectCourse> optionalCourseBlocks =
                subjectCourseRepository.findAllByStudyCourseAndOptionalCourseBlock(course, true);
        int optCourseBlocksCount = optionalCourseBlocks.size();

        int[][] optionalCoursesChoices = new int[choicesCount][optCourseBlocksCount];

        int counter = 0;
        for (int i = 0; i < optCourseBlocksCount; i++){
            SubjectCourse optBlock = optionalCourseBlocks.get(i);
            List<OptionalCourseSubject> optSubjects = optBlock.getOptionalCourseSubjects();
            for (int j = 0; j < optSubjects.size(); j++) {
                optionalCoursesChoices[counter+j][i] = optSubjects.get(j).getId();
            }
            counter += optSubjects.size();
        }

        return optionalCoursesChoices;
    }


    private boolean checkRestriction(Operation operation, double factorValue, double restrictionValue) {
        switch(operation.getOperation()) {
            case ">" :
                return factorValue > restrictionValue;

            case "<" :
                return factorValue < restrictionValue;

            case "=" :
                return factorValue == restrictionValue;

            case ">=" :
                return factorValue >= restrictionValue;

            case "<=" :
                return factorValue <= restrictionValue;
            default :
                return true;
        }
    }
}
