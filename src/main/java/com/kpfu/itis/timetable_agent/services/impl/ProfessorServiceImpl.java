package com.kpfu.itis.timetable_agent.services.impl;

import com.kpfu.itis.timetable_agent.models.Professor;
import com.kpfu.itis.timetable_agent.models.ProfessorTimeslotResource;
import com.kpfu.itis.timetable_agent.repositories.ProfessorRepository;
import com.kpfu.itis.timetable_agent.repositories.ProfessorTimeslotResourceRepository;
import com.kpfu.itis.timetable_agent.services.interfaces.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private ProfessorTimeslotResourceRepository professorTimeslotResourceRepository;

    @Override
    public List<Professor> getProfessorList() {
        return professorRepository.findAll();
    }

    @Override
    public Professor getProfessor(int id) {
        return professorRepository.getOne(id);
    }

    @Override
    public Professor getProfessor(String name) {
        return professorRepository.findByName(name);
    }

    @Override
    public Professor saveProfessor(Professor professor) {
        return professorRepository.save(professor);
    }

    @Override
    public void saveResource(ProfessorTimeslotResource resource) {
        professorTimeslotResourceRepository.save(resource);
    }
}
