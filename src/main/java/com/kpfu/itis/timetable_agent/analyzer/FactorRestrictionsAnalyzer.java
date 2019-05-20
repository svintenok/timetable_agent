package com.kpfu.itis.timetable_agent.analyzer;

import com.kpfu.itis.timetable_agent.analyzer.models.FactorRestriction;
import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;
import com.kpfu.itis.timetable_agent.models.*;
import com.kpfu.itis.timetable_agent.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FactorRestrictionsAnalyzer{

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

	public List<RestrictionViolation> checkRestrictions() {
		List<RestrictionViolation> retrictionsViolations = new ArrayList<>();

		List<Restriction> restrictionList = restrictionRepository.findAllByEnabled(true);
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

						for (int[] optionalCoursesChoice : optionalCoursesChoices) {
							String optCourseChoiceParam = Arrays.toString(optionalCoursesChoice)
																.replace("[", "{")
																.replace("]", "}");
							Double factorValue = jdbcTemplate.queryForObject(factor.getSqlExpression(),
																			 new Object[]{group.getId(), optCourseChoiceParam},
																			 Double.class);

							if (!checkRestriction(operation, factorValue, restrictionValue)) {
								groupDissatisfiedRetrictions.add(new FactorRestriction(group, restriction));
							}
						}


						if (groupDissatisfiedRetrictions.size() > 0) {
							retrictionsViolations.add(new FactorRestriction(group, restriction,
																			groupDissatisfiedRetrictions.size(),
																			optionalCoursesChoices.length));
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
}
