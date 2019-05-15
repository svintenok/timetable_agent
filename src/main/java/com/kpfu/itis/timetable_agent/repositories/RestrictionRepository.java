package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Integer> {
}
