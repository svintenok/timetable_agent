package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.AssignedPair;
import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.repositories.AssignedPairRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.CurrentTimetableService;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrentTimetableServiceImpl implements CurrentTimetableService{

    @Autowired
    private AssignedPairRepository assignedPairRepository;

    @Autowired
    private RestrictionsService restrictionsService;


    @Override
    public List<AssignedPair> getGroupTimetable(Group group) {
        List<AssignedPair> pairs = assignedPairRepository.getAllPairByGroup(group);
        return pairs;
    }

    @Override
    public void replacePair(AssignedPair replacementPair, AssignedPair offerPair) {
        offerPair = assignedPairRepository.save(offerPair);
        replacementPair.setAssignedPairOffer(offerPair);
        assignedPairRepository.save(replacementPair);

    }

    @Override
    public AssignedPair replacePairHard(AssignedPair replacementPair) {
        AssignedPair offerPair = replacementPair.getAssignedPairOffer();
        offerPair.setOffer(false);


        offerPair = assignedPairRepository.save(offerPair);
        assignedPairRepository.delete(replacementPair);
        return offerPair;
    }

    @Override
    public void cancelReplacePair(AssignedPair replacementPair) {
        AssignedPair offerPair = replacementPair.getAssignedPairOffer();
        if (offerPair == null) {
            System.out.println("What?");
            System.out.println(replacementPair.toString());
        }
        replacementPair.setReplacement(false);
        replacementPair.setAssignedPairOffer(null);
        assignedPairRepository.save(replacementPair);
        if (offerPair != null) {
            assignedPairRepository.delete(offerPair);
        }
    }


    @Override
    public AssignedPair assignReplacePairOffer(AssignedPair replacementPair) {

        AssignedPair offerPair = replacementPair.getAssignedPairOffer();

        if (replacementPair.isOffer()) {

            AssignedPair firstReplacementPair = assignedPairRepository.findByAssignedPairOffer(replacementPair);
            firstReplacementPair.setAssignedPairOffer(offerPair);
            assignedPairRepository.save(firstReplacementPair);

            assignedPairRepository.delete(replacementPair);
        }

        return offerPair;
    }

    @Override
    public void applyOffers() {
        List<AssignedPair> replacementPairs = assignedPairRepository.findAllByReplacement(true);

        for (AssignedPair pair: replacementPairs){
            this.replacePairHard(pair);
        }
    }

    @Override
    public void cancelOffers() {
        List<AssignedPair> replacementPairs = assignedPairRepository.findAllByReplacement(true);

        for (AssignedPair pair: replacementPairs){
            this.cancelReplacePair(pair);
        }
    }
}
