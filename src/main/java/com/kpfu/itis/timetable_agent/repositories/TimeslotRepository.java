package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeslotRepository extends JpaRepository<Timeslot, Integer> {
    Timeslot getOneByTimeslotDayAndTimeslotTime(TimeslotDay day, TimeslotTime time);


    @Query("SELECT t FROM Timeslot t WHERE " +
            "NOT EXISTS (SELECT pair FROM AssignedPair pair where pair.timeslot = t AND " +
            "pair.group = :g)")
    List<Timeslot> getAllFreeByGroup(@Param("g") Group g);

    @Query("SELECT t FROM Timeslot t WHERE " +
            "NOT EXISTS (SELECT pair FROM AssignedPair pair where pair.timeslot = t AND " +
            "pair.groupSet = :gs)")
    List<Timeslot> getAllFreeByGroupSet(@Param("gs") GroupSet groupSet);
}
