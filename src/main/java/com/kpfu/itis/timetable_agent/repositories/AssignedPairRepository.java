package com.kpfu.itis.timetable_agent.repositories;

import com.kpfu.itis.timetable_agent.models.AssignedPair;
import com.kpfu.itis.timetable_agent.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignedPairRepository extends JpaRepository<AssignedPair, Integer> {

    @Query("SELECT pair FROM AssignedPair pair WHERE pair.group = :g " +
            "OR :g IN (SELECT gsg.group FROM GroupSetGroup gsg where gsg.groupSet=pair.groupSet) AND pair.replacement=false ORDER BY pair.timeslot")
    List<AssignedPair> getAllPairByGroup(@Param("g") Group g);

    List<AssignedPair> findAllByReplacement(boolean replacement);

    AssignedPair findByAssignedPairOffer(AssignedPair pairOffer);

    int countByOffer(boolean offer);

    @Query("SELECT pair FROM AssignedPair pair WHERE " +
            "pair.professor is not null AND pair.replacement=false")
    List<AssignedPair> getAllFreePairsWithOffers();
}
