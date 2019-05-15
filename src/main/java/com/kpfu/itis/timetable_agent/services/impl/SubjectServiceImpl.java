package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.OptionalCourseSubject;
import com.kpfu.itis.timetable_agent.models.SubjectCourse;
import com.kpfu.itis.timetable_agent.repositories.OptionalCourseSubjectRepository;
import com.kpfu.itis.timetable_agent.repositories.SubjectCourseRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectCourseRepository subjectCourseRepository;

    @Autowired
    private OptionalCourseSubjectRepository optionalCourseSubjectRepository;

    @Override
    public SubjectCourse saveSubjectCourse(SubjectCourse subjectCourse) {
        return subjectCourseRepository.save(subjectCourse);
    }

    @Override
    public SubjectCourse getSubjectCourseByName(String subjectName) {
        return subjectCourseRepository.findByName(subjectName);
    }

    @Override
    public OptionalCourseSubject saveOptSubjectCourse(OptionalCourseSubject optionalCourseSubject) {
        return optionalCourseSubjectRepository.save(optionalCourseSubject);
    }

    @Override
    public OptionalCourseSubject getOptSubjectCourseByName(String subjectName) {
        return optionalCourseSubjectRepository.findByName(subjectName);
    }
}
