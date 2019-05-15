package com.kpfu.itis.timetable_agent.util.parser;


import com.kpfu.itis.timetable_agent.models.*;
import com.kpfu.itis.timetable_agent.services.interfaces.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TimetableParser {

    @Autowired
    private AssignedPairService assignedPairService;

    @Autowired
    private StudyCourseService studyCourseService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private TimeslotService timeslotService;

    @Autowired
    private AuditoryService auditoryService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private SubjectService subjectService;


    public void parseTimetableDataJson(String timetableDataJson) {

        JSONObject timetableData = new JSONObject(timetableDataJson);

        JSONObject groupsInfo = timetableData.getJSONObject("groupsInfo");
        JSONArray groups = groupsInfo.getJSONArray("groups");
        for (int i = 0; i < groups.length(); i++) {
            JSONObject groupJSON = groups.getJSONObject(i);

            Group group = new Group();
            group.setGroupNum(groupJSON.getString("group"));
            group.setStudyCourse(studyCourseService.getCourse(groupJSON.getInt("course")));
            if (groupJSON.has("studentCount")) {
                group.setStudentCount(groupJSON.getInt("studentCount"));
            }
            else {
                group.setStudentCount(-1);
            }
            groupService.saveGroup(group);
        }

        JSONArray groupSets = groupsInfo.getJSONArray("groupSets");
        for (int i = 0; i < groupSets.length(); i++) {
            JSONObject groupSetJSON = groupSets.getJSONObject(i);

            GroupSet groupSet = new GroupSet();
            groupSet.setName(groupSetJSON.getString("name"));
            groupService.saveGroupSet(groupSet);

            JSONArray groupSetGroups = groupSetJSON.getJSONArray("groupsList");
            Set<Group> groupsList = new HashSet<>();
            for (int j = 0; j < groupSetGroups.length(); j++) {
                Group group = groupService.getGroupByNum(groupSetGroups.getString(j));
                groupsList.add(group);
            }
            groupSet.setGroups(groupsList);
            groupService.saveGroupSet(groupSet);
        }

        if (timetableData.has("auditoryResourcesData")){
            JSONArray auditoryResourcesData = timetableData.getJSONArray("auditoryResourcesData");
            for (int i = 0; i < auditoryResourcesData.length(); i++){
                JSONObject auditoryData = auditoryResourcesData.getJSONObject(i);

                Auditory auditory = new Auditory();
                auditory.setNumber(auditoryData.getString("auditory"));
                if (auditoryData.has("capacity")) {
                    auditory.setCapacity(auditoryData.getInt("capacity"));
                }
                auditory = auditoryService.saveAuditory(auditory);

                if (auditoryData.has("resourceList")){
                    JSONArray resourceList = auditoryData.getJSONArray("resourceList");
                    for (int j = 0; j < resourceList.length(); j++){
                        JSONObject resourceTimeslot = resourceList.getJSONObject(j);
                        TimeslotDay day = timeslotService.getTimeslotDay(resourceTimeslot.getInt("day"));
                        TimeslotTime time = timeslotService.getTimeslotTime(resourceTimeslot.getInt("time"));
                        Timeslot timeslot = timeslotService.getTimeslot(day, time);

                        AuditoryResource auditoryResource = new AuditoryResource();
                        auditoryResource.setAuditory(auditory);
                        auditoryResource.setTimeslot(timeslot);
                        auditoryService.saveResource(auditoryResource);
                    }
                }

                //set resources
                List<Timeslot> timeslotList = timeslotService.getAllTimeslots();
                for (Timeslot timeslot: timeslotList){
                    AuditoryResource auditoryResource = new AuditoryResource();
                    auditoryResource.setAuditory(auditory);
                    auditoryResource.setTimeslot(timeslot);
                    auditoryService.saveResource(auditoryResource);
                }
            }
        }

        if (timetableData.has("professorResourcesData")){
            JSONArray professorResourcesData = timetableData.getJSONArray("professorResourcesData");
            for (int i = 0; i < professorResourcesData.length(); i++){
                JSONObject professorData = professorResourcesData.getJSONObject(i);

                Professor professor = new Professor();
                professor.setName(professorData.getString("professor"));

                professor = professorService.saveProfessor(professor);

                if (professorData.has("resourceList")){
                    JSONArray resourceList = professorData.getJSONArray("resourceList");
                    for (int j = 0; j < resourceList.length(); j++){
                        JSONObject resourceTimeslot = resourceList.getJSONObject(j);
                        TimeslotDay day = timeslotService.getTimeslotDay(resourceTimeslot.getInt("day"));
                        TimeslotTime time = timeslotService.getTimeslotTime(resourceTimeslot.getInt("time"));
                        Timeslot timeslot = timeslotService.getTimeslot(day, time);

                        ProfessorTimeslotResource professorTimeslotResource = new ProfessorTimeslotResource();
                        professorTimeslotResource.setProfessor(professor);
                        professorTimeslotResource.setTimeslot(timeslot);
                        professorService.saveResource(professorTimeslotResource);
                    }
                }

                //set resources
                List<Timeslot> timeslotList = timeslotService.getAllTimeslots();
                for (Timeslot timeslot: timeslotList){
                    ProfessorTimeslotResource professorTimeslotResource = new ProfessorTimeslotResource();
                    professorTimeslotResource.setProfessor(professor);
                    professorTimeslotResource.setTimeslot(timeslot);
                    professorService.saveResource(professorTimeslotResource);
                }
            }
        }

        JSONArray timetable = timetableData.getJSONArray("timetableInfo");
        for (int i = 0; i < timetable.length(); i++) {
            JSONObject pairJSON = timetable.getJSONObject(i);

            AssignedPair pair = new AssignedPair();
            pair.setTimeslotDay(timeslotService.getTimeslotDay(pairJSON.getInt("day")));
            pair.setTimeslotTime(timeslotService.getTimeslotTime(pairJSON.getInt("time")));
            pair.setTimeslot(timeslotService.getTimeslot(pair.getTimeslotDay(), pair.getTimeslotTime()));

            StudyCourse studyCourse;
            if (pairJSON.has("group")){
                Group group = groupService.getGroupByNum(pairJSON.getString("group"));
                pair.setGroup(group);
                studyCourse = group.getStudyCourse();
            }
            else {
                GroupSet groupSet = groupService.getGroupSetByName(pairJSON.getString("groupSet"));
                pair.setGroupSet(groupSet);
                studyCourse = groupSet.getGroups().iterator().next().getStudyCourse();
            }

            String subjectCourseName = pairJSON.getString("subjectCourse");
            SubjectCourse subjectCourse = subjectService.getSubjectCourseByName(subjectCourseName);
            if (subjectCourse == null){
                subjectCourse = new SubjectCourse();
                subjectCourse.setName(subjectCourseName);
                subjectCourse.setOptionalCourseBlock(pairJSON.has("optSubject"));
                subjectCourse.setStudyCourse(studyCourse);
                subjectCourse = subjectService.saveSubjectCourse(subjectCourse);
            }
            pair.setSubjectCourse(subjectCourse);

            if (subjectCourse.isOptionalCourseBlock()) {
                String optSubjectName = pairJSON.getString("optSubject");
                OptionalCourseSubject optSubject = subjectService.getOptSubjectCourseByName(optSubjectName);
                if (optSubject == null) {
                    optSubject = new OptionalCourseSubject();
                    optSubject.setName(optSubjectName);
                    optSubject.setSubjectCourse(subjectCourse);
                    optSubject.setStudyCourse(studyCourse);
                    optSubject.setStudentCount(-1);
                    optSubject = subjectService.saveOptSubjectCourse(optSubject);
                }
                pair.setOptionalCourseSubject(optSubject);
            }

            pair.setType(assignedPairService.getPairType(pairJSON.getString("type")));

            String auditoryNum = pairJSON.getString("auditory");
            Auditory auditory = auditoryService.getAuditoryByNumber(auditoryNum);
            if (auditory == null){
                auditory = new Auditory();
                auditory.setNumber(auditoryNum);
                auditory.setCapacity(-1);
                if (pair.getType().getType().equals("лекция"))
                    auditory.setLectureRoom(true);
                auditory = auditoryService.saveAuditory(auditory);

                //set resources
                List<Timeslot> timeslotList = timeslotService.getAllTimeslots();
                for (Timeslot timeslot: timeslotList){
                    AuditoryResource auditoryResource = new AuditoryResource();
                    auditoryResource.setAuditory(auditory);
                    auditoryResource.setTimeslot(timeslot);
                    auditoryService.saveResource(auditoryResource);
                }
            }
            pair.setAuditory(auditory);

            if (pairJSON.has("professor")) {

                String professorName = pairJSON.getString("professor");
                Professor professor = professorService.getProfessor(professorName);
                if (professor == null) {
                    professor = new Professor();
                    professor.setName(professorName);
                    professor = professorService.saveProfessor(professor);

                    //set resources
                    List<Timeslot> timeslotList = timeslotService.getAllTimeslots();
                    for (Timeslot timeslot : timeslotList) {
                        ProfessorTimeslotResource professorTimeslotResource = new ProfessorTimeslotResource();
                        professorTimeslotResource.setProfessor(professor);
                        professorTimeslotResource.setTimeslot(timeslot);
                        professorService.saveResource(professorTimeslotResource);
                    }
                }
                pair.setProfessor(professor);
            }

            if (pairJSON.has("in_two_weeks")){
                pair.setInTwoWeeks(true);
                if (pairJSON.getString("in_two_weeks").equals("н.н."))
                    pair.setEvenWeek(false);
                else
                    pair.setEvenWeek(true);
            }

            assignedPairService.save(pair);
        }
    }
}
