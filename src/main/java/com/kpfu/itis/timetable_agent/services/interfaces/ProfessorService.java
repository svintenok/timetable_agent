package com.kpfu.itis.timetable_agent.services.interfaces;

import com.kpfu.itis.timetable_agent.models.Professor;
import com.kpfu.itis.timetable_agent.models.ProfessorTimeslotResource;

import java.util.List;

public interface ProfessorService {
    List<Professor> getProfessorList();
    Professor getProfessor(int id);
    Professor getProfessor(String name);
    Professor saveProfessor(Professor professor);

    void saveResource(ProfessorTimeslotResource resource);
}
