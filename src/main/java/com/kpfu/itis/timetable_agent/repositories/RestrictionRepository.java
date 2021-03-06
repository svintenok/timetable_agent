package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.Restriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Integer> {
	List<Restriction> findAllByEnabled(boolean enabled);
	List<Restriction> findAllByEnabledAndHard(boolean enabled, boolean hard);
}
