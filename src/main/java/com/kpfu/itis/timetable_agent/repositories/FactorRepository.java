package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.Factor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorRepository extends JpaRepository<Factor, Integer> {
}
