package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.TimeslotTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeslotTimeRepository extends JpaRepository<TimeslotTime, Integer> {

    List<TimeslotTime> findAllByOrderByPairNumAsc();
    TimeslotTime getOneByPairNum(int pairNum);

}
