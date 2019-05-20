package com.kpfu.itis.timetable_agent.controllers;

import com.kpfu.itis.timetable_agent.parser.TimetableParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping(path = "/upload_timetable")
public class UploaderController {

    @Autowired
    private TimetableParser timetableParser;

    @GetMapping("")
    public String getUploadPage(ModelMap modelMap) {
        return "upload_timetable";
    }

    @PostMapping("")
    public String uploadFile(@RequestParam("timetable_data") MultipartFile timetableDataFile) {

        try {
            timetableParser.parseTimetableDataJson(new String(timetableDataFile.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/current_timetable/groups";
    }
}
