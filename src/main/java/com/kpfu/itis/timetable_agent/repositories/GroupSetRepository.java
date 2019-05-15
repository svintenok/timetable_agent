package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.GroupSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupSetRepository extends JpaRepository<GroupSet, Integer> {

    GroupSet findByName(String name);
}
