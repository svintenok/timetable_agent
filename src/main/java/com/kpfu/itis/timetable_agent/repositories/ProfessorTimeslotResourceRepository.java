package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorTimeslotResourceRepository extends JpaRepository<ProfessorTimeslotResource, Integer> {

    ProfessorTimeslotResource findByProfessorAndTimeslot(Professor professor, Timeslot timeslot);
}
