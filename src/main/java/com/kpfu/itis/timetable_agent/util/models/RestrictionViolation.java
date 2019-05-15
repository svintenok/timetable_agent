package com.kpfu.itis.timetable_agent.util.models;

public abstract class RestrictionViolation {

    protected boolean hard;
    protected double weight;

    public abstract String warningString();

    public boolean isHard() {
        return hard;
    }

    public double getWeight() {
        return weight;
    }
}
