package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.StudyCourse;
import com.kpfu.itis.timetable_agent.repositories.StudyCourseRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.StudyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudyCourseServiceImpl implements StudyCourseService {
    @Autowired
    private StudyCourseRepository studyCourseRepository;


    @Override
    public StudyCourse getCourse(int courseNum) {
        return studyCourseRepository.findByCourseNum(courseNum);
    }
}
