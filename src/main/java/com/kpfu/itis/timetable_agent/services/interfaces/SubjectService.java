package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.OptionalCourseSubject;
import com.kpfu.itis.timetable_agent.models.SubjectCourse;

public interface SubjectService {
    SubjectCourse saveSubjectCourse(SubjectCourse subjectCourse);
    SubjectCourse getSubjectCourseByName(String subjectName);

    OptionalCourseSubject saveOptSubjectCourse(OptionalCourseSubject optionalCourseSubject);
    OptionalCourseSubject getOptSubjectCourseByName(String subjectName);
}
