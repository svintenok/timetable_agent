package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.ResourceChecking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceCheckingRepository extends JpaRepository<ResourceChecking, Integer> {
    ResourceChecking getByName(String name);
}
