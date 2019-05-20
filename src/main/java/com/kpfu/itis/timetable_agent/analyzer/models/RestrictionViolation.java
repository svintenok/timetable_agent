package com.kpfu.itis.timetable_agent.analyzer.models;

public abstract class RestrictionViolation {

    protected boolean hard;
    protected double  priority;

    public abstract String warningString();

    public boolean isHard() {
        return hard;
    }

    public double getWeight() {
        return priority;
    }
}
