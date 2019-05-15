package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.PairType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairTypeRepository extends JpaRepository<PairType, Integer> {
    PairType findByType(String type);
}
