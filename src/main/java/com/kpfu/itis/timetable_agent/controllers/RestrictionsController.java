package com.kpfu.itis.timetable_agent.controllers;

import com.kpfu.itis.timetable_agent.services.interfaces.FactorService;
import com.kpfu.itis.timetable_agent.services.interfaces.RestrictionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "factor_and_restrictions_config";
    }
}
