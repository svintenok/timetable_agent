package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.StudyCourse;
import com.kpfu.itis.timetable_agent.models.SubjectCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectCourseRepository extends JpaRepository<SubjectCourse, Integer> {
    List<SubjectCourse> findAllByStudyCourseAndOptionalCourseBlock(StudyCourse studyCourse, boolean optCourseBlock);
    SubjectCourse findByName(String name);
}
