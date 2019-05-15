package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.models.StudyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    List<Group> findAll();
    List<Group> findAllByStudyCourse(StudyCourse studyCourse);
    Group getByGroupNum(String groupNum);
}
