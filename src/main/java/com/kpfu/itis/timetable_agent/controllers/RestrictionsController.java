package com.kpfu.itis.timetable_agent.controllers;

import com.kpfu.itis.timetable_agent.models.Restriction;
import com.kpfu.itis.timetable_agent.services.interfaces.FactorService;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(path = "/restrictions")
public class RestrictionsController {

    @Autowired
    private RestrictionsService restrictionsService;

    @Autowired
    private FactorService factorService;

    @GetMapping("")
    public String getRestrictionsPage(ModelMap modelMap) {
        modelMap.addAttribute("restrictions", restrictionsService.getAllRestrictions());
        modelMap.addAttribute("factors", factorService.getAllFactors());
        return "factors_and_restrictions";
    }

    @GetMapping("/config")
    public String getRestrictionsConfigPage(ModelMap modelMap) {
        modelMap.addAttribute("restrictions", restrictionsService.getAllRestrictions());
        return "restrictions_config";
    }

    @PostMapping("/config")
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

        return "redirect:/restrictions/config";
    }

    @GetMapping("/{restriction}")
    public String getRestrictionPage(ModelMap modelMap, @PathVariable("restriction") int restrictionId) {
        modelMap.addAttribute("restriction", restrictionsService.getRestriction(restrictionId));
        modelMap.addAttribute("factors", factorService.getAllFactors());
        modelMap.addAttribute("operations", restrictionsService.getAllOperations());

        return "restriction_change";
    }

    @PostMapping("/{restriction}")
    public String saveRestriction(HttpServletRequest request,
                                    @PathVariable("restriction") int restrictionId,
                                    @RequestParam("restriction_name") String name,
                                    @RequestParam("restriction_factor") int factorId,
                                    @RequestParam("restriction_operation") int operationId,
                                    @RequestParam("restriction_value") int restrictionValue,
                                    @RequestParam("priority") int priority,
                                    @RequestParam(value = "isHard", required = false) String isHard) {

        Restriction restriction = restrictionsService.getRestriction(restrictionId);

        restriction.setName(name);
        restriction.setFactor(factorService.getFactor(factorId));
        restriction.setOperation(restrictionsService.getOperation(operationId));
        restriction.setRestrictionValue(restrictionValue);
        restriction.setHard(isHard != null);
        restriction.setPriority(Integer.valueOf(priority));
        restrictionsService.saveRestriction(restriction);


        return "redirect:/restrictions/" + restrictionId;
    }

    @GetMapping("/add")
    public String getRestrictionCreatingPage(ModelMap modelMap) {

        modelMap.addAttribute("factors", factorService.getAllFactors());
        modelMap.addAttribute("operations", restrictionsService.getAllOperations());

        return "restriction_create";
    }

    @PostMapping("/add")
    public String saveRestriction(HttpServletRequest request,
                                  @RequestParam("restriction_name") String name,
                                  @RequestParam("restriction_factor") int factorId,
                                  @RequestParam("restriction_operation") int operationId,
                                  @RequestParam("restriction_value") int restrictionValue,
                                  @RequestParam("priority") int priority,
                                  @RequestParam(value = "isHard", required = false) String isHard) {

        Restriction restriction = new Restriction();

        restriction.setName(name);
        restriction.setFactor(factorService.getFactor(factorId));
        restriction.setOperation(restrictionsService.getOperation(operationId));
        restriction.setRestrictionValue(restrictionValue);
        restriction.setHard(isHard != null);
        restriction.setPriority(Integer.valueOf(priority));
        restriction = restrictionsService.saveRestriction(restriction);


        return "redirect:/restrictions/" + restriction.getId();
    }
}
