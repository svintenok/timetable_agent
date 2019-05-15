package com.kpfu.itis.timetable_agent.controllers;

import com.kpfu.itis.timetable_agent.models.AssignedPair;
import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.optimizer.Optimizer;
import com.kpfu.itis.timetable_agent.services.interfaces.*;
import com.kpfu.itis.timetable_agent.util.models.RestrictionViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(path = "/current_timetable")
public class TimetableController {

    @Autowired
    private CurrentTimetableService currentTimetableService;

    @Autowired
    private AssignedPairService assignedPairService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    private AuditoryService auditoryService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private Optimizer optimizer;

    ////////////////////////////////////////////////
    int hardCount;
    int softCount;

    @GetMapping("/groups")
    public String groupList(ModelMap modelMap) {

        modelMap.addAttribute("groups", groupService.getAllGroups());
        addViolationsInfo(modelMap);

        return "timetable_groups_list";
    }

    @PostMapping("/optimize")
    public String pairChangeCancel() {

        optimizer.optimizeTimetable();
        //redirectAttributes.addFlashAttribute("cancel", "cancel");

        return "redirect:/current_timetable/groups/";
    }

    @GetMapping("/{group}")
    public String groupTimetable(ModelMap modelMap, @PathVariable("group") String groupNum) {

        Group group = groupService.getGroupByNum(groupNum);
        modelMap.addAttribute("group", group);
        modelMap.addAttribute("group_timetable", currentTimetableService.getGroupTimetable(group));
        addViolationsInfo(modelMap);

        return "group_timetable";
    }

    @GetMapping("/change/{pair}")
    public String pairChange(ModelMap modelMap, @PathVariable("pair") int pairId) {

        int prevHardCount = hardCount;
        int prevSoftCount = softCount;

        modelMap.addAttribute("pair", assignedPairService.getPairById(pairId));
        modelMap.addAttribute("day_timeslots", timeslotService.getTimeslotDays());
        modelMap.addAttribute("time_timeslots", timeslotService.getTimeslotTimes());
        modelMap.addAttribute("professor_list", professorService.getProfessorList());
        modelMap.addAttribute("auditory_list", auditoryService.getAuditoryList());

        addViolationsInfo(modelMap);

        modelMap.addAttribute("hardCountChange", hardCount-prevHardCount);
        modelMap.addAttribute("softCountChange", softCount-prevSoftCount);

        return "pair_change";
    }

    @PostMapping("/change/{pair}")
    public String pairChangePost(RedirectAttributes redirectAttributes,
                                 @PathVariable("pair") int pairId,
                                 @RequestParam("timeslot_day") int timeslotDay,
                                 @RequestParam("timeslot_time") int timeslotTime,
                                 @RequestParam("professor") int professorId,
                                 @RequestParam("auditory") int auditoryId) {

        System.out.println("change?");
        AssignedPair replacementPair = assignedPairService.getPairById(pairId);
        replacementPair.setReplacement(true);


        AssignedPair offerPair = (AssignedPair) replacementPair.clone();

        offerPair.setTimeslotDay(timeslotService.getTimeslotDay(timeslotDay));
        offerPair.setTimeslotTime(timeslotService.getTimeslotTime(timeslotTime));
        offerPair.setProfessor(professorService.getProfessor(professorId));
        offerPair.setAuditory(auditoryService.getAuditory(auditoryId));
        offerPair.setTimeslot(timeslotService.getTimeslot(offerPair.getTimeslotDay(), offerPair.getTimeslotTime()));
        offerPair.setOffer(true);

        currentTimetableService.replacePair(replacementPair, offerPair);
        redirectAttributes.addFlashAttribute("action", "offer");

        return "redirect:/current_timetable/change/" + pairId;
    }


    @PostMapping("/change/{pair}/save")
    public String pairChangeSave(RedirectAttributes redirectAttributes,
                                 @PathVariable("pair") int pairId) {

        AssignedPair replacementPair = assignedPairService.getPairById(pairId);

        replacementPair = currentTimetableService.replacePairHard(replacementPair);
        redirectAttributes.addFlashAttribute("action", "save");

        return "redirect:/current_timetable/change/" + replacementPair.getId();
    }

    @PostMapping("/change/{pair}/cancel")
    public String pairChangeCancel(RedirectAttributes redirectAttributes,
                                 @PathVariable("pair") int pairId) {

        AssignedPair replacementPair = assignedPairService.getPairById(pairId);
        currentTimetableService.cancelReplacePair(replacementPair);
        redirectAttributes.addFlashAttribute("action", "cancel");

        return "redirect:/current_timetable/change/" + pairId;
    }

    private void addViolationsInfo(ModelMap modelMap) {
        List<RestrictionViolation> violations = currentTimetableService.getRestrictionViolation();
        hardCount = 0;
        softCount = 0;
        for (RestrictionViolation violation: violations){
            if (violation.isHard())
                hardCount += 1;
            else
                softCount += 1;
        }

        modelMap.addAttribute("violations", violations);
        modelMap.addAttribute("hardCount", hardCount);
        modelMap.addAttribute("softCount", softCount);
    }

}
