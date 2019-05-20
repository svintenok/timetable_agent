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

	public List<RestrictionViolation> getRestrictionsViolations() {

		List<RestrictionViolation> dissatisfiedRestrictions = new ArrayList<>();
		dissatisfiedRestrictions.addAll(resourcesRestrictionsAnalyzer.checkResourceRestrictions());
		dissatisfiedRestrictions.addAll(factorRestrictionsAnalyzer.checkRestrictions());
		return dissatisfiedRestrictions;
	}
}
