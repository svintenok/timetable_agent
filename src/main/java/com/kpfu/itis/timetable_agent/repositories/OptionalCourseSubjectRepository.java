package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.OptionalCourseSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionalCourseSubjectRepository extends JpaRepository<OptionalCourseSubject, Integer> {
    OptionalCourseSubject findByName(String name);
}
