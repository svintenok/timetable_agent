package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.Auditory;
import com.kpfu.itis.timetable_agent.models.Timeslot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditoryRepository extends JpaRepository<Auditory, Integer> {

    @Query("SELECT a FROM Auditory a WHERE " +
            "NOT EXISTS (SELECT pair FROM AssignedPair pair where pair.auditory = a AND " +
            "pair.timeslot = :t) AND " +
            "EXISTS (SELECT ar FROM AuditoryResource ar where ar.auditory = a AND " +
            "ar.timeslot = :t)")
    List<Auditory> getAllFreeByTimeslot(@Param("t") Timeslot timeslot);

    Auditory findByNumber(String number);
}
