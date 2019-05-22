package com.kpfu.itis.timetable_agent.analyzer;

import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestrictionsAnalyzer {

	@Autowired
	private FactorRestrictionsAnalyzer factorRestrictionsAnalyzer;

	@Autowired
	private ResourcesRestrictionsAnalyzer resourcesRestrictionsAnalyzer;

	//////////////////////////////////////

	private List<RestrictionViolation> restrictionsViolations;

	public List<RestrictionViolation> checkRestrictionsViolations() {

		restrictionsViolations = new ArrayList<>();
		restrictionsViolations.addAll(resourcesRestrictionsAnalyzer.checkResourceRestrictions());
		restrictionsViolations.addAll(factorRestrictionsAnalyzer.checkRestrictions());

		return restrictionsViolations;
	}

	public List<RestrictionViolation> getRestrictionsViolations() {
		if (restrictionsViolations == null) {
			return checkRestrictionsViolations();
		}
		return restrictionsViolations;
	}
}
