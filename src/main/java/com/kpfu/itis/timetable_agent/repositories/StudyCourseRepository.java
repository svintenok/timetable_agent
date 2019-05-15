package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.StudyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudyCourseRepository extends JpaRepository<StudyCourse, Integer> {
    StudyCourse findByCourseNum(int courseNum);
}
