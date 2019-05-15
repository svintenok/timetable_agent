package com.kpfu.itis.timetable_agent.controllers;

import com.kpfu.itis.timetable_agent.services.interfaces.AuditoryService;
import com.kpfu.itis.timetable_agent.services.interfaces.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/data")
public class DataAndResourcesController {

    @Autowired
    private AuditoryService auditoryService;

    @Autowired
    private TimeslotService timeslotService;

    @GetMapping("/auditories")
    public String pairChange(ModelMap modelMap) {

        modelMap.addAttribute("auditory_list", auditoryService.getAuditoryList());

        return "data_and_resources_page";
    }

    @GetMapping("/auditories/{auditory_id}")
    public String pairChange(ModelMap modelMap, @PathVariable("auditory_id") int auditoryId) {

        modelMap.addAttribute("day_timeslots", timeslotService.getTimeslotDays());
        modelMap.addAttribute("time_timeslots", timeslotService.getTimeslotTimes());
        modelMap.addAttribute("auditory", auditoryService.getAuditory(auditoryId));

        return "auditory_config";
    }
}
