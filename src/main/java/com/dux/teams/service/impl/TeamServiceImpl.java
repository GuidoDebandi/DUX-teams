package com.dux.teams.service.impl;

import com.dux.teams.dto.TeamDto;
import com.dux.teams.model.Team;
import com.dux.teams.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Override public List<Team> findAllTeams() {
        return null;
    }

    @Override public Team createTeam(TeamDto teamDto) {
        return null;
    }

    @Override public List<Team> findTeamsByName(String name) {
        return null;
    }

    @Override public Team findTeamById(Integer id) {
        return null;
    }

    @Override public Team deleteTeam(Integer id) {
        return null;
    }

    @Override public Team updateTeam(Integer id, TeamDto teamDto) {
        return null;
    }
}
