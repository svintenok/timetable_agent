package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.models.GroupSet;

import java.util.List;

public interface GroupService {
    Group getGroupByNum(String groupNum);
    List<Group> getAllGroups();
    Group saveGroup(Group group);

    GroupSet saveGroupSet(GroupSet groupSet);
    GroupSet getGroupSetByName(String name);
}
