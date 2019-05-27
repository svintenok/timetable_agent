package com.kpfu.itis.timetable_agent.optimizer;

import com.kpfu.itis.timetable_agent.models.AssignedPair;
import com.kpfu.itis.timetable_agent.models.Auditory;
import com.kpfu.itis.timetable_agent.models.Timeslot;
import com.kpfu.itis.timetable_agent.repositories.AssignedPairRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class Optimizer {

    @Autowired
    private CostFunction costFunction;

    ////////////////////

    @Autowired
    private CurrentTimetableService currentTimetableService;

    @Autowired
    private AssignedPairService assignedPairService;

    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    private AuditoryService auditoryService;

    ////////////////////

    private Random random = new Random();

    ////////////////////

    private List<AssignedPair> currentPairs;
    private int pairCount;

    /////////////////////

    private double currentCost;
    private double currenHardViolations;
    private List<AssignedPair> moveReplacements = new ArrayList<>();

    /////////////////////

    private int changesCost = 0;
    private int maxChangesCount = 8;
    private int maxIterationsCount = 1;
    //private int currentTimetableOffersCount;

    public void optimizeTimetable() {

        int iterationsCounter = 0;

        currentPairs = assignedPairService.getCurrentFreePairsWithOffers();
        pairCount = currentPairs.size();

        currentCost = costFunction.calculateTimetableCost(changesCost);
        currenHardViolations = costFunction.getHardViolationCount();

        while (iterationsCounter < maxIterationsCount  || currentCost == 0) {
            move(NeighborhoodStructure.get(random.nextInt(3) + 1));

            double newCost = costFunction.calculateTimetableCost(changesCost);
            double newHardViolations = costFunction.getHardViolationCount();
            int changesCount = currentTimetableService.getCurrentTimetableOffersCount();

            System.out.println(currentCost + "," + currenHardViolations + " ||| " + newCost + ", " + newHardViolations +
                               ", iteration " + iterationsCounter);

            if ((currenHardViolations == 0 || newHardViolations < currenHardViolations) &&
                    newCost < currentCost && changesCount <= maxChangesCount) {

                acceptMove();
                currentCost = newCost;
                currenHardViolations = newHardViolations;

            } else {
                rejectMove();
            }

            iterationsCounter += 1;
            currentPairs = assignedPairService.getCurrentFreePairsWithOffers();
        }

    }

    void move(NeighborhoodStructure neighborhoodStructure){
        switch (neighborhoodStructure) {
            case SWAP_MOVE:
                //to do free room choosing?
                AssignedPair replacementPair1 = currentPairs.get(random.nextInt(pairCount));
                replacementPair1.setReplacement(true);

                AssignedPair replacementPair2 = currentPairs.get(random.nextInt(pairCount));
                while (replacementPair2.getId() == replacementPair1.getId()){
                    replacementPair2 = currentPairs.get(random.nextInt(pairCount));
                }
                replacementPair2.setReplacement(true);


                AssignedPair offerPair1 = (AssignedPair) replacementPair1.clone();

                offerPair1.setTimeslot(replacementPair2.getTimeslot());
                offerPair1.setTimeslotDay(replacementPair2.getTimeslotDay());
                offerPair1.setTimeslotTime(replacementPair2.getTimeslotTime());
                offerPair1.setAuditory(replacementPair2.getAuditory());//?
                offerPair1.setOffer(true);
                offerPair1 = assignedPairService.save(offerPair1);

                replacementPair1.setAssignedPairOffer(offerPair1);
                replacementPair1 = assignedPairService.save(replacementPair1);

                AssignedPair offerPair2 = (AssignedPair) replacementPair2.clone();

                offerPair2.setTimeslot(replacementPair1.getTimeslot());
                offerPair2.setTimeslotDay(replacementPair1.getTimeslotDay());
                offerPair2.setTimeslotTime(replacementPair1.getTimeslotTime());
                offerPair2.setAuditory(replacementPair1.getAuditory());//?
                offerPair2.setOffer(true);
                offerPair2 = assignedPairService.save(offerPair2);

                replacementPair2.setAssignedPairOffer(offerPair2);
                replacementPair2 = assignedPairService.save(replacementPair2);

                moveReplacements.add(replacementPair1);
                moveReplacements.add(replacementPair2);
                break;

            case SHIFT_MOVE:
                AssignedPair replacementPair = currentPairs.get(random.nextInt(pairCount));
                replacementPair.setReplacement(true);

                //free timeslot choosing
                List<Timeslot> timeslots = timeslotService.getAllFreeByPairGroups(replacementPair);
                int timeslotCount = timeslots.size();
                Timeslot timeslot = timeslots.get(random.nextInt(timeslotCount));

                //free auditory choosing
                List<Auditory> auditories = auditoryService.getAllFreeByTimeslotAndType(timeslot, replacementPair.getType().getType().equals("лекция"));
                if (auditories.size() < 1) {
                    auditories = auditoryService.getAuditoryList();
                }
                int auditoriesCount = auditories.size();

                Auditory auditory = auditories.get(random.nextInt(auditoriesCount));

                AssignedPair offerPair = (AssignedPair) replacementPair.clone();

                offerPair.setTimeslot(timeslot);
                offerPair.setTimeslotDay(timeslot.getTimeslotDay());
                offerPair.setTimeslotTime(timeslot.getTimeslotTime());
                offerPair.setAuditory(auditory);
                offerPair.setOffer(true);
                offerPair = assignedPairService.save(offerPair);

                replacementPair.setAssignedPairOffer(offerPair);
                replacementPair = assignedPairService.save(replacementPair);

                moveReplacements.add(replacementPair);
                break;
            case ROOM_MOVE:
                AssignedPair replacementPair3 = currentPairs.get(random.nextInt(pairCount));
                replacementPair3.setReplacement(true);

                //to do free auditory choosing
                List<Auditory> auditories3 = auditoryService.getAllFreeByTimeslotAndType(replacementPair3.getTimeslot(), replacementPair3.getType().getType().equals("лекция"));
                if (auditories3.size() < 1) {
                    auditories3 = auditoryService.getAuditoryList();
                }
                int auditoriesCount3 = auditories3.size();
                Auditory auditory3 = auditories3.get(random.nextInt(auditoriesCount3));

                AssignedPair offerPair3 = (AssignedPair) replacementPair3.clone();

                offerPair3.setAuditory(auditory3);
                offerPair3.setOffer(true);
                offerPair3 = assignedPairService.save(offerPair3);

                replacementPair3.setAssignedPairOffer(offerPair3);
                replacementPair3 = assignedPairService.save(replacementPair3);

                moveReplacements.add(replacementPair3);
                break;
        }
    }

    private void rejectMove() {
        for (int i = 0; i < moveReplacements.size(); i++){
            AssignedPair replacementPair = assignedPairService.getPairById(moveReplacements.get(i).getId());

            currentTimetableService.cancelReplacePair(replacementPair);
        }
        moveReplacements.clear();
    }

    private void acceptMove() {
        changesCost += moveReplacements.size();

        for (int i = 0; i < moveReplacements.size(); i++){
            AssignedPair replacementPair = assignedPairService.getPairById(moveReplacements.get(i).getId());
            AssignedPair offerPair = currentTimetableService.assignReplacePairOffer(replacementPair);

            //currentPairs.remove(replacementPair);
            //currentPairs.add(replacementPair.getAssignedPairOffer());
        }

        moveReplacements.clear();
    }
}
