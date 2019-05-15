package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.TimeslotDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeslotDayRepository extends JpaRepository<TimeslotDay, Integer> {

    List<TimeslotDay> findAllByOrderByDayNumAsc();
    TimeslotDay getOneByDayNum(int dayNum);

}
