package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.Group;
import com.kpfu.itis.timetable_agent.models.GroupSet;
import com.kpfu.itis.timetable_agent.repositories.GroupRepository;
import com.kpfu.itis.timetable_agent.repositories.GroupSetRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private GroupSetRepository groupSetRepository;


    @Override
    public Group getGroupByNum(String groupNum) {
        return groupRepository.getByGroupNum(groupNum);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public GroupSet saveGroupSet(GroupSet groupSet) {
        return groupSetRepository.save(groupSet);
    }

    @Override
    public GroupSet getGroupSetByName(String name) {
        return groupSetRepository.findByName(name);
    }
}
