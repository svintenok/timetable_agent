package com.kpfu.itis.timetable_agent.controllers;

import com.kpfu.itis.timetable_agent.analyzer.RestrictionsAnalyzer;
import com.kpfu.itis.timetable_agent.models.Restriction;
import com.kpfu.itis.timetable_agent.optimizer.Optimizer;
import com.kpfu.itis.timetable_agent.services.interfaces.AssignedPairService;
import com.kpfu.itis.timetable_agent.services.interfaces.CurrentTimetableService;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import com.kpfu.itis.timetable_agent.analyzer.models.RestrictionViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/optimizer")
public class OptimizerController {

    @Autowired
    private RestrictionsAnalyzer restrictionsAnalyzer;

    @Autowired
    private CurrentTimetableService currentTimetableService;

    @Autowired
    private RestrictionsService restrictionsService;

    @Autowired
    private AssignedPairService assignedPairService;

    @Autowired
    private Optimizer optimizer;

    ////////////////////////////////////////////////
    int hardCount;
    int softCount;
    int prevHardCount;
    int prevSoftCount;

    List<RestrictionViolation> violations;
    List<RestrictionViolation> prevViolations;

    @GetMapping("")
    public String getOptimizerPage(ModelMap modelMap) {
        modelMap.addAttribute("restrictions", restrictionsService.getAllRestrictions());
        addViolationsInfo(modelMap);
        return "optimizer";
    }

    @GetMapping("/result")
    public String getOptimizeResult(ModelMap modelMap) {

        addViolationsInfo(modelMap);

        modelMap.addAttribute("old_violations", prevViolations);
        modelMap.addAttribute("hardCountChange", hardCount-prevHardCount);
        modelMap.addAttribute("softCountChange", softCount-prevSoftCount);

        modelMap.addAttribute("reassigned_pairs", assignedPairService.getTimetableReplacements());
        return "optimizer_result";
    }

    @PostMapping("/optimize")
    public String OptimizeTimetable() {

        prevHardCount = hardCount;
        prevSoftCount = softCount;
        prevViolations = violations;

        System.out.println("optimize...");
        optimizer.optimizeTimetable();
        System.out.println("success!");
        return "redirect:/optimizer/result";
    }

    @PostMapping("/configure")
    public String OptimizeTimetable(HttpServletRequest request,
                                    @RequestParam("restriction_check") List<Integer> restrictionCheck,
                                    @RequestParam("hardCheck") List<Integer> hardCheck) {

        List<Restriction> restrictionList = restrictionsService.getAllRestrictions();
        for (Restriction restriction: restrictionList){
            restriction.setEnabled(restrictionCheck.contains(restriction.getId()));
            restriction.setHard(hardCheck.contains(restriction.getId()));

            restriction.setPriority(Integer.valueOf(request.getParameter("priority" + restriction.getId())));
            restrictionsService.saveRestriction(restriction);
        }

        //optimizer.optimizeTimetable();
        //redirectAttributes.addFlashAttribute("cancel", "cancel");

        return "redirect:/optimizer";
    }


    @PostMapping("/save_results")
    public String saveOptimizeResult() {
        currentTimetableService.applyOffers();
        return "redirect:/current_timetable/groups";
    }

    @PostMapping("/cancel_results")
    public String cancelOptimizeResult() {
        currentTimetableService.cancelOffers();
        return "redirect:/current_timetable/groups";
    }


    private void addViolationsInfo(ModelMap modelMap) {
        violations = restrictionsAnalyzer.getRestrictionsViolations();
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
