package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Professor findByName(String name);
}
